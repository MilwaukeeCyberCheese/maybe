// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.other.Stopwatch;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  private double throttle;
  private double rotation;
  public double throttleActual;
  private double rotationActual;
  private double previousThrottle = 0;
  private double previousRotation = 0;
  private boolean brakeMode = false;
  private Stopwatch brakingTimer = new Stopwatch();
  private SlewRateLimiter throttleLimiter = new SlewRateLimiter(Constants.drive.THROTTLE_LIMITER);
  private SlewRateLimiter rotationLimiter = new SlewRateLimiter(Constants.drive.ROTATION_LIMITER);

  /**
   * The Drivetrain subsystem incorporates the sensors and actuators attached to
   * the robots chassis.
   * These include four drive motors, a left and right encoder and a gyro.
   */

  /** Create a new drivetrain subsystem. */
  public Drivetrain() {
    super();

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    Constants.controllers.leftFrontSpark.setInverted(Constants.drive.LEFT_FRONT_INVERTED);
    Constants.controllers.leftRearSpark.setInverted(Constants.drive.LEFT_REAR_INVERTED);
    Constants.controllers.rightFrontSpark.setInverted(Constants.drive.RIGHT_FRONT_INVERTED);
    Constants.controllers.rightRearSpark.setInverted(Constants.drive.RIGHT_REAR_INVERTED);
    Constants.controllers.leftFrontSpark.setSmartCurrentLimit(Constants.drive.CURRENT_LIMIT);
    Constants.controllers.rightFrontSpark.setSmartCurrentLimit(Constants.drive.CURRENT_LIMIT);
    Constants.controllers.leftRearSpark.setSmartCurrentLimit(Constants.drive.CURRENT_LIMIT);
    Constants.controllers.rightRearSpark.setSmartCurrentLimit(Constants.drive.CURRENT_LIMIT);
    Constants.controllers.leftFrontSpark.setIdleMode(Constants.drive.IDLE_MODE);
    Constants.controllers.leftRearSpark.setIdleMode(Constants.drive.IDLE_MODE);
    Constants.controllers.rightFrontSpark.setIdleMode(Constants.drive.IDLE_MODE);
    Constants.controllers.rightRearSpark.setIdleMode(Constants.drive.IDLE_MODE);

    // Let's name the sensors on the LiveWindow
    addChild("Drive", Constants.drive.m_drive);
  }

  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {

    SmartDashboard.putData("Drivetrain", Constants.drive.m_drive);
    SmartDashboard.putNumber("FrontLeft Speed", Constants.controllers.leftFrontSpark.get());
    SmartDashboard.putNumber("FrontRight Speed", Constants.controllers.rightFrontSpark.get());
    SmartDashboard.putNumber("BackLeft Speed", Constants.controllers.leftRearSpark.get());
    SmartDashboard.putNumber("BackRight Speed", Constants.controllers.rightRearSpark.get());
    SmartDashboard.putNumber("FrontLeft Temp", Constants.controllers.leftFrontSpark.getMotorTemperature());
    SmartDashboard.putNumber("BackLeft Temp", Constants.controllers.leftRearSpark.getMotorTemperature());
    SmartDashboard.putNumber("FrontRight Temp", Constants.controllers.rightFrontSpark.getMotorTemperature());
    SmartDashboard.putNumber("BackRight Temp", Constants.controllers.rightRearSpark.getMotorTemperature());
    SmartDashboard.putNumber("FrontLeft Current", Constants.controllers.leftFrontSpark.getOutputCurrent());
    SmartDashboard.putNumber("BackLeft Current", Constants.controllers.leftRearSpark.getOutputCurrent());
    SmartDashboard.putNumber("FrontRight Current", Constants.controllers.rightFrontSpark.getOutputCurrent());
    SmartDashboard.putNumber("BackRight Current", Constants.controllers.rightRearSpark.getOutputCurrent());
    SmartDashboard.putNumber("Gyro Pitch", Constants.balance.gyro.getRoll());
  }

  /**
   * Arcade style driving for the Drivetrain.
   *
   * @param throttle  Speed in range [-1,1]
   * @param rotation  Speed in range [-1,1]
   * @param brakeMode whether brakes should be engaged or not
   */
  public void drive(double throttle, double rotation, boolean brakeMode) {
    this.rotation = rotation;
    this.throttle = throttle;
    this.brakeMode = brakeMode;
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
    Constants.drive.m_drive.arcadeDrive(throttleActual, rotationActual);
  }

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

    if (RobotContainer.readAuto) {
      RobotContainer.m_autoSubsystem.addDriveSpeeds(Constants.controllers.leftFrontSpark.get(),
          Constants.controllers.rightFrontSpark.get(), Constants.controllers.leftRearSpark.get(),
          Constants.controllers.rightRearSpark.get());
    }

    previousThrottle = throttle;
    previousRotation = rotation;
    // if (throttle == 0 && rotation == 0 && previousRotation != 0 &&
    // previousThrottle != 0) {
    // brakingTimer.stop();
    // brakingTimer.reset();
    // brakingTimer.start();
    // }

    // if((throttle == 0 && rotation == 0 && brakingTimer.getTime() >= 300) ||
    // brakeMode){
    // Constants.controllers.leftFrontSpark.setIdleMode(IdleMode.kBrake);
    // Constants.controllers.leftRearSpark.setIdleMode(IdleMode.kBrake);
    // Constants.controllers.rightFrontSpark.setIdleMode(IdleMode.kBrake);
    // Constants.controllers.rightRearSpark.setIdleMode(IdleMode.kBrake);
    // }else{
    // Constants.controllers.leftFrontSpark.setIdleMode(IdleMode.kCoast);
    // Constants.controllers.leftRearSpark.setIdleMode(IdleMode.kCoast);
    // Constants.controllers.rightFrontSpark.setIdleMode(IdleMode.kCoast);
    // Constants.controllers.rightRearSpark.setIdleMode(IdleMode.kCoast);
    // }
  }
}
