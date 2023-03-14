// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import frc.robot.Constants;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The elevator subsystem uses PID to go to a given height. Unfortunately, in it's current state PID
 * values for simulation are different than in the real world do to minor differences.
 */
public class RightElevator extends SubsystemBase{
  public double speed;

  

  /** Create a new elevator subsystem. */
  public RightElevator() {
    Constants.controllers.rightLiftSpark.setInverted(Constants.lift.RIGHT_INVERTED);
  }



  public void setTargetPosition(double speed) {
    this.speed = speed;
   Constants.controllers.rightLiftSpark.set(speed);
  }



  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {
    SmartDashboard.putData("Elevator Encoder", Constants.sensors.rightLiftEncoder);

    SmartDashboard.putNumber("Speed: ", speed);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
   

    
  }

  

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
