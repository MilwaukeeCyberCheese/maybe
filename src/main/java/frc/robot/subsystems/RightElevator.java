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
public class RightElevator extends SubsystemBase {
  public double speed;
  public Boolean limited = true;

  /** Create a new elevator subsystem. */
  public RightElevator() {

    // Constants.sensors.rightLift.setInverted(Constants.lift.RIGHT_INVERTED);
    // Constants.sensors.rightLift.setPositionConversionFactor(1);

    Constants.controllers.rightLiftSpark.restoreFactoryDefaults();

    Constants.controllers.rightLiftSpark.setInverted(Constants.lift.RIGHT_INVERTED);

  }

  public void setSpeed(double speed, Boolean limited) {
    this.speed = speed;
    this.limited = limited;

    if (limited) {
      if ((Constants.sensors.leftLift.getPosition() >= Constants.lift.LEFT_MAX_POSITION
          || Constants.sensors.rightLift.getPosition() >= Constants.lift.RIGHT_MAX_POSITION)
          && speed > 0) {
        Constants.controllers.rightLiftSpark.set(0);
        this.speed = 0;
      } else if ((Constants.sensors.leftLift.getPosition() <= Constants.lift.LEFT_MIN_POSITION
          || Constants.sensors.rightLift.getPosition() <= Constants.lift.RIGHT_MIN_POSITION)
          && speed < 0) {
        Constants.controllers.rightLiftSpark.set(0);
        this.speed = 0;
      } else {
        Constants.controllers.rightLiftSpark.set(speed);
      }
    } else {
      Constants.controllers.rightLiftSpark.set(speed);
    }
  }

  public void zero() {
    Constants.sensors.rightLift.setPosition(0);
  }

  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {

    // SmartDashboard.putNumber("Right Slide CPR",
    // Constants.sensors.rightLift.getCountsPerRevolution());
    SmartDashboard.putNumber("Right Slide Position", Constants.sensors.rightLift.getPosition());
    SmartDashboard.putBoolean("Right Slide Limited", limited);
    SmartDashboard.putNumber("Speed: ", speed);

    // if PID coefficients on SmartDashboard have changed, write new values to
    // controller

  }

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
