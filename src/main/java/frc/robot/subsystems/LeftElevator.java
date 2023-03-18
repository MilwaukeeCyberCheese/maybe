// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The elevator subsystem uses PID to go to a given height. Unfortunately, in
 * it's current state PID
 * values for simulation are different than in the real world do to minor
 * differences.
 */
public class LeftElevator extends SubsystemBase {
  public double speed;
  public Boolean limited = true;

  /** Create a new elevator subsystem. */
  public LeftElevator() {

    // Constants.sensors.leftLift.setInverted(Constants.lift.LEFT_INVERTED);
    // Constants.sensors.leftLift.setPositionConversionFactor(1);

    Constants.controllers.leftLiftSpark.restoreFactoryDefaults();

    Constants.controllers.leftLiftSpark.setInverted(Constants.lift.LEFT_INVERTED);
  }

  public void setSpeed(double speed, Boolean limited) {

    this.speed = speed;
    this.limited = limited;

    if (limited) {
      if ((Constants.sensors.leftLift.getPosition() >= Constants.lift.LEFT_MAX_POSITION
          || Constants.sensors.rightLift.getPosition() >= Constants.lift.RIGHT_MAX_POSITION)
          && speed > 0) {
        Constants.controllers.leftLiftSpark.set(0);
        this.speed = 0;
      } else if ((Constants.sensors.leftLift.getPosition() <= Constants.lift.LEFT_MIN_POSITION
          || Constants.sensors.rightLift.getPosition() <= Constants.lift.RIGHT_MIN_POSITION)
          && speed < 0) {
        Constants.controllers.leftLiftSpark.set(0);
        this.speed = 0;
      } else {
        Constants.controllers.leftLiftSpark.set(speed);
      }
    } else {
      Constants.controllers.leftLiftSpark.set(speed);
    }

  }

  public void zero() {
    Constants.sensors.leftLift.setPosition(0);
  }

  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {

    // SmartDashboard.putNumber("Left Slide CPR",
    // Constants.sensors.leftLift.getCountsPerRevolution());
    SmartDashboard.putNumber("Left Slide Position", Constants.sensors.leftLift.getPosition());
    SmartDashboard.putBoolean("Left Slide Limited", limited);
    SmartDashboard.putNumber("Speed: ", speed);

  }

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
