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



  public void setTargetPosition(double rotations) {
    Constants.lift.right_PID.setReference(rotations, CANSparkMax.ControlType.kPosition);
  }



  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {
    SmartDashboard.putData("Elevator Encoder", Constants.sensors.rightLiftEncoder);

    SmartDashboard.putNumber("P Gain", Constants.lift.right_kP);
    SmartDashboard.putNumber("I Gain", Constants.lift.right_kI);
    SmartDashboard.putNumber("D Gain", Constants.lift.right_kD);
    SmartDashboard.putNumber("I Zone", Constants.lift.right_kIz);
    SmartDashboard.putNumber("Feed Forward", Constants.lift.right_kFF);
    SmartDashboard.putNumber("Max Output", Constants.lift.right_kMaxOutput);
    SmartDashboard.putNumber("Min Output", Constants.lift.right_kMinOutput);
    SmartDashboard.putNumber("Rotations", 0);

    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double rotations = SmartDashboard.getNumber("Rotations", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != Constants.lift.right_kP)) { Constants.lift.right_PID.setP(p); Constants.lift.right_kP = p; }
    if((i != Constants.lift.right_kI)) { Constants.lift.right_PID.setI(i); Constants.lift.right_kI = i; }
    if((d != Constants.lift.right_kD)) { Constants.lift.right_PID.setD(d); Constants.lift.right_kD = d; }
    if((iz != Constants.lift.right_kIz)) { Constants.lift.right_PID.setIZone(iz); Constants.lift.right_kIz = iz; }
    if((ff != Constants.lift.right_kFF)) { Constants.lift.right_PID.setFF(ff); Constants.lift.right_kFF = ff; }
    if((max != Constants.lift.right_kMaxOutput) || (min != Constants.lift.right_kMinOutput)) { 
      Constants.lift.right_PID.setOutputRange(min, max); 
      Constants.lift.right_kMinOutput = min; Constants.lift.right_kMaxOutput = max; 
    }

    //TODO  THIS IS FOR TESTING REMOVE FOR COMPETITION
    Constants.lift.right_PID.setReference(rotations, CANSparkMax.ControlType.kPosition);
  }

  

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
