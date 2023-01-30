// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
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

public void second(){
Constants.pneumatics.lSolenoid.set(true);
Constants.pneumatics.rSolenoid.set(true);
}

public void first(){
  Constants.pneumatics.lSolenoid.set(false);
  Constants.pneumatics.rSolenoid.set(false);
}

public void toggle(){
  Constants.pneumatics.lSolenoid.toggle();
  Constants.pneumatics.rSolenoid.toggle();
}
  /** The log method puts interesting information to the SmartDashboard. */
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
