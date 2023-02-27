// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import frc.robot.Constants;
import frc.robot.Robot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The elevator subsystem uses PID to go to a given height. Unfortunately, in it's current state PID
 * values for simulation are different than in the real world do to minor differences.
 */
public class RightElevator extends SubsystemBase{
  

  

  /** Create a new elevator subsystem. */
  public RightElevator() {
    Constants.controllers.rightLiftSpark.restoreFactoryDefaults();

    Constants.lift.right_PID.setP(Constants.lift.right_kP);
    Constants.lift.right_PID.setI(Constants.lift.right_kI);
    Constants.lift.right_PID.setD(Constants.lift.right_kD);
    Constants.lift.right_PID.setIZone(Constants.lift.right_kIz);
    Constants.lift.right_PID.setFF(Constants.lift.right_kFF);
    Constants.lift.right_PID.setOutputRange(Constants.lift.right_kMinOutput, Constants.lift.right_kMaxOutput);
  }



  public void setTargetPosition(double position) {
    Constants.lift.right_PID.setReference(position, CANSparkMax.ControlType.kPosition);
  }



  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {
    SmartDashboard.putData("Elevator Encoder", Constants.sensors.rightLiftEncoder);
  }


  

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
