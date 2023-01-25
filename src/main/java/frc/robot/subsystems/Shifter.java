// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The wrist subsystem is like the elevator, but with a rotational joint instead of a linear joint.
 */
public class Shifter extends SubsystemBase {
  private final Solenoid lSolenoid;
  private final Solenoid rSolenoid;

  

  /** Create a new shifter subsystem. */
  public Shifter() {

    lSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    rSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 1);

    

    // Let's name everything on the LiveWindow
    addChild("Left Solenoid", lSolenoid);
    addChild("Right Solenoid", rSolenoid);
  }

public void second(){
lSolenoid.set(true);
rSolenoid.set(true);
}

public void first(){
  lSolenoid.set(false);
  rSolenoid.set(false);
}

public void toggle(){
  lSolenoid.toggle();
  rSolenoid.toggle();
}
  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {
    SmartDashboard.putBoolean("Left Solenoid", lSolenoid.get());
    SmartDashboard.putBoolean("Right Shifter", rSolenoid.get());
  }





  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
