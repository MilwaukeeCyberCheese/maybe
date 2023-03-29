// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  private double throttle;
  private double rotation;
  
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
    SmartDashboard.putNumber("FrontLeft Temp", Constants.controllers.leftFrontSpark.getMotorTemperature());
    SmartDashboard.putNumber("BackLeft Temp", Constants.controllers.leftRearSpark.getMotorTemperature());
    SmartDashboard.putNumber("FrontRight Temp", Constants.controllers.rightFrontSpark.getMotorTemperature());
    SmartDashboard.putNumber("BackRight Temp", Constants.controllers.rightRearSpark.getMotorTemperature());
    SmartDashboard.putNumber("FrontLeft Current", Constants.controllers.leftFrontSpark.getOutputCurrent());
    SmartDashboard.putNumber("BackLeft Current", Constants.controllers.leftRearSpark.getOutputCurrent());
    SmartDashboard.putNumber("FrontRight Current", Constants.controllers.rightFrontSpark.getOutputCurrent());
    SmartDashboard.putNumber("BackRight Current", Constants.controllers.rightRearSpark.getOutputCurrent());
  }

  /**
   * Arcade style driving for the Drivetrain.
   *
   * @param throttle  Speed in range [-1,1]
   * @param rotation Speed in range [-1,1]
   */
  public void drive(double throttle, double rotation) {
    this.throttle = throttle;
    this.rotation = rotation;
    Constants.drive.m_drive.arcadeDrive(throttle, rotation);
  }


 

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();

    if(RobotContainer.readAuto){
      RobotContainer.m_autoSubsystem.addDriveSpeeds(Constants.controllers.leftFrontSpark.get(), Constants.controllers.rightFrontSpark.get(), Constants.controllers.leftRearSpark.get(), Constants.controllers.rightRearSpark.get());
    }
  }
}
