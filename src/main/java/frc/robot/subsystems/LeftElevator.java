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
public class LeftElevator extends SubsystemBase{
  

  

  /** Create a new elevator subsystem. */
  public LeftElevator() {
    Constants.controllers.leftLiftSpark.restoreFactoryDefaults();

    Constants.lift.left_PID.setP(Constants.lift.left_kP);
    Constants.lift.left_PID.setI(Constants.lift.left_kI);
    Constants.lift.left_PID.setD(Constants.lift.left_kD);
    Constants.lift.left_PID.setIZone(Constants.lift.left_kIz);
    Constants.lift.left_PID.setFF(Constants.lift.left_kFF);
    Constants.lift.left_PID.setOutputRange(Constants.lift.left_kMinOutput, Constants.lift.left_kMaxOutput);
  }



  public void setTargetPosition(double rotations) {
    Constants.lift.left_PID.setReference(rotations, CANSparkMax.ControlType.kPosition);
  }



  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {
    SmartDashboard.putData("Elevator Encoder", Constants.sensors.leftLiftEncoder);

    SmartDashboard.putNumber("P Gain", Constants.lift.left_kP);
    SmartDashboard.putNumber("I Gain", Constants.lift.left_kI);
    SmartDashboard.putNumber("D Gain", Constants.lift.left_kD);
    SmartDashboard.putNumber("I Zone", Constants.lift.left_kIz);
    SmartDashboard.putNumber("Feed Forward", Constants.lift.left_kFF);
    SmartDashboard.putNumber("Max Output", Constants.lift.left_kMaxOutput);
    SmartDashboard.putNumber("Min Output", Constants.lift.left_kMinOutput);
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
    if((p != Constants.lift.left_kP)) { Constants.lift.left_PID.setP(p); Constants.lift.left_kP = p; }
    if((i != Constants.lift.left_kI)) { Constants.lift.left_PID.setI(i); Constants.lift.left_kI = i; }
    if((d != Constants.lift.left_kD)) { Constants.lift.left_PID.setD(d); Constants.lift.left_kD = d; }
    if((iz != Constants.lift.left_kIz)) { Constants.lift.left_PID.setIZone(iz); Constants.lift.left_kIz = iz; }
    if((ff != Constants.lift.left_kFF)) { Constants.lift.left_PID.setFF(ff); Constants.lift.left_kFF = ff; }
    if((max != Constants.lift.left_kMaxOutput) || (min != Constants.lift.left_kMinOutput)) { 
      Constants.lift.left_PID.setOutputRange(min, max); 
      Constants.lift.left_kMinOutput = min; Constants.lift.left_kMaxOutput = max; 
    }

    //TODO  THIS IS FOR TESTING REMOVE FOR COMPETITION
    Constants.lift.left_PID.setReference(rotations, CANSparkMax.ControlType.kPosition);
  }

  

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
