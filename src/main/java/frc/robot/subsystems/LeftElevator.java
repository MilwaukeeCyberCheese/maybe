// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import frc.robot.Constants;



import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The elevator subsystem uses PID to go to a given height. Unfortunately, in it's current state PID
 * values for simulation are different than in the real world do to minor differences.
 */
public class LeftElevator extends SubsystemBase{
  public double speed;

  

  /** Create a new elevator subsystem. */
  public LeftElevator() {
    Constants.controllers.leftLiftSpark.setInverted(Constants.lift.LEFT_INVERTED);
  }



  public void setSpeed(double speed) {
    this.speed = speed;
   Constants.controllers.leftLiftSpark.set(speed);
  }



  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {
    SmartDashboard.putData("Elevator Encoder", Constants.sensors.leftLiftEncoder);

    SmartDashboard.putNumber("Speed: ", speed);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
   

    
  }

  

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
