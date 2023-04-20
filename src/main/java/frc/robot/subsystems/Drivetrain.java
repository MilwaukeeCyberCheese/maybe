// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  private double throttle;
  private double rotation;
  public double throttleActual;
  public double rotationActual;
  private double previousThrottle = 0;
  private double previousRotation = 0;
  private SlewRateLimiter throttleLimiter = new SlewRateLimiter(Constants.drive.THROTTLE_LIMITER_RATE);
  private SlewRateLimiter rotationLimiter = new SlewRateLimiter(Constants.drive.ROTATION_LIMITER_RATE);

  /**
   * The Drivetrain subsystem incorporates the sensors and actuators attached to
   * the robots chassis.
   * These include four drive motors, a left and right encoder and a gyro.
   */

  /** Create a new drivetrain subsystem. */
  public Drivetrain() {
    super();

    // invert motors so they drive in same direction
    Constants.controllers.leftFrontSpark.setInverted(Constants.drive.LEFT_FRONT_INVERTED);
    Constants.controllers.leftRearSpark.setInverted(Constants.drive.LEFT_REAR_INVERTED);
    Constants.controllers.rightFrontSpark.setInverted(Constants.drive.RIGHT_FRONT_INVERTED);
    Constants.controllers.rightRearSpark.setInverted(Constants.drive.RIGHT_REAR_INVERTED);

    // sets a current limit to protect motors
    Constants.controllers.leftFrontSpark.setSmartCurrentLimit(Constants.drive.CURRENT_LIMIT);
    Constants.controllers.rightFrontSpark.setSmartCurrentLimit(Constants.drive.CURRENT_LIMIT);
    Constants.controllers.leftRearSpark.setSmartCurrentLimit(Constants.drive.CURRENT_LIMIT);
    Constants.controllers.rightRearSpark.setSmartCurrentLimit(Constants.drive.CURRENT_LIMIT);

    // sets braking or coasting of motors
    Constants.controllers.leftFrontSpark.setIdleMode(Constants.drive.IDLE_MODE);
    Constants.controllers.leftRearSpark.setIdleMode(Constants.drive.IDLE_MODE);
    Constants.controllers.rightFrontSpark.setIdleMode(Constants.drive.IDLE_MODE);
    Constants.controllers.rightRearSpark.setIdleMode(Constants.drive.IDLE_MODE);

  }

  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {

    System.out.println(Robot.autoChooser.getSelected());

    SmartDashboard.putData("Drivetrain", Constants.drive.m_drive);

    // adds speed of each motor
    SmartDashboard.putNumber("FrontLeft Speed", Constants.controllers.leftFrontSpark.get());
    SmartDashboard.putNumber("FrontRight Speed", Constants.controllers.rightFrontSpark.get());
    SmartDashboard.putNumber("BackLeft Speed", Constants.controllers.leftRearSpark.get());
    SmartDashboard.putNumber("BackRight Speed", Constants.controllers.rightRearSpark.get());

    // adds averaged temperature of all drive motors
    SmartDashboard.putNumber("Drive Motor Temp",
        (Constants.controllers.rightRearSpark.getMotorTemperature()
            + Constants.controllers.leftRearSpark.getMotorTemperature()
            + Constants.controllers.leftFrontSpark.getMotorTemperature()
            + Constants.controllers.rightFrontSpark.getMotorTemperature()) / 4);

    // adds current drawn by each drive motor
    SmartDashboard.putNumber("FrontLeft Current", Constants.controllers.leftFrontSpark.getOutputCurrent());
    SmartDashboard.putNumber("BackLeft Current", Constants.controllers.leftRearSpark.getOutputCurrent());
    SmartDashboard.putNumber("FrontRight Current", Constants.controllers.rightFrontSpark.getOutputCurrent());
    SmartDashboard.putNumber("BackRight Current", Constants.controllers.rightRearSpark.getOutputCurrent());

    // adds pitch of the gyro
    SmartDashboard.putNumber("Gyro Pitch", Constants.balance.gyro.getRoll());
  }

  /**
   * Arcade style driving for the Drivetrain.
   *
   * @param throttle Speed in range [-1,1]
   * @param rotation Speed in range [-1,1]
   */
  public void drive(double throttle, double rotation) {
    this.rotation = rotation;
    this.throttle = throttle;

    // apply acceleration limiting to throttle and rotation if increasing, otherwise
    // no limiting is applied
    if (Math.abs(throttle) > Math.abs(previousThrottle)) {
      throttleActual = throttleLimiter.calculate(throttle);
    } else {
      throttleActual = throttle;
    }
    if (Math.abs(rotation) > Math.abs(previousRotation)) {
      rotationActual = rotationLimiter.calculate(rotation);
    } else {
      rotationActual = rotation;
    }

    // run drivetrain at determined speeds
    Constants.drive.m_drive.arcadeDrive(throttleActual, rotationActual);
  }

  /**
   * Allows for driving of each wheel individually
   *
   * @param frontLeft  speed of frontLeft wheel *
   * @param frontRight speed of frontRight wheel
   * @param backLeft   speed of backLeft wheel
   * @param backRight  speed of backRight wheel
   */
  public void setWheelSpeeds(double frontLeft, double frontRight, double backLeft, double backRight) {
    Constants.controllers.leftFrontSpark.set(frontLeft);
    Constants.controllers.rightFrontSpark.set(frontRight);
    Constants.controllers.leftRearSpark.set(backLeft);
    Constants.controllers.rightRearSpark.set(backRight);
  }

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();

    // adds wheel speeds every 20 milliseconds
    if (RobotContainer.readAuto) {
      RobotContainer.m_autoSubsystem.addDriveSpeeds(Constants.controllers.leftFrontSpark.get(),
          Constants.controllers.rightFrontSpark.get(), Constants.controllers.leftRearSpark.get(),
          Constants.controllers.rightRearSpark.get());
    }

    // sets previous throttle and rotation to compare for limiting
    previousThrottle = throttle;
    previousRotation = rotation;
  }
}
