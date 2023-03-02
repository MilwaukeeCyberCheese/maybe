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
 * The shifter subsystem includes both of the cylinders on the gearboxes
 */
public class Shifter extends SubsystemBase {
 

  

  /** Create a new shifter subsystem. */
  public Shifter() {
    
   

    

    
  }

public void setGear(boolean position){
Constants.pneumatics.lSolenoid.set(position);
Constants.pneumatics.rSolenoid.set(position);

}


  /** The log method puts the status of each solenoid to SmartDashboard */
  public void log() {
    SmartDashboard.putBoolean("Left Solenoid", Constants.pneumatics.lSolenoid.get());
    SmartDashboard.putBoolean("Right Shifter", Constants.pneumatics.rSolenoid.get());
  }





  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
