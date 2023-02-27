// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The wrist subsystem is like the elevator, but with a rotational joint instead of a linear joint.
 */
public class Shifter extends SubsystemBase {
 

  

  /** Create a new shifter subsystem. */
  public Shifter() {
    
   

    

    // Let's name everything on the LiveWindow
    addChild("Left Solenoid", Constants.pneumatics.lSolenoid);
    addChild("Right Solenoid", Constants.pneumatics.rSolenoid);
  }

public void setGear(boolean position){
Constants.pneumatics.lSolenoid.set(position);
Constants.pneumatics.rSolenoid.set(position);

}


  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {
    SmartDashboard.putBoolean("Left Solenoid", Constants.pneumatics.lSolenoid.get());
    SmartDashboard.putBoolean("Right Shifter", Constants.pneumatics.rSolenoid.get());
    SmartDashboard.putBoolean("Compressor Active", Constants.pneumatics.PCM.getCompressor());
    SmartDashboard.putBoolean("Compressor Current Too High", Constants.pneumatics.PCM.getCompressorCurrentTooHighFault());
    SmartDashboard.putBoolean("Compressor Disconnected", Constants.pneumatics.PCM.getCompressorNotConnectedFault());
    SmartDashboard.putBoolean("Compressor Shorted", Constants.pneumatics.PCM.getCompressorShortedFault());
    SmartDashboard.putBoolean("Pressure Switch", Constants.pneumatics.compressor.getPressureSwitchValue());
    SmartDashboard.putNumber("Compressor Current", Constants.pneumatics.compressor.getCurrent());
    SmartDashboard.putBoolean("Compressor Enabled", Constants.pneumatics.compressor.isEnabled());
  }





  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
    Constants.pneumatics.compressor.enableDigital();
  }
}
