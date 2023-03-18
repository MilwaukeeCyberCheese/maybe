// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The shifter subsystem includes both of the cylinders on the gearboxes
 */
public class Shifter extends SubsystemBase {

private Boolean position;

  /** Create a new shifter subsystem. */
  public Shifter() {
    /* empty because there's nothing to put into it */

  }

  public void setGear(boolean position) {
    this.position = position;
    Constants.pneumatics.shifterSolenoid.set(position);
   

  }

  /** The log method puts the status of each solenoid to SmartDashboard */
  public void log() {
    SmartDashboard.putData("Shifter", Constants.pneumatics.shifterSolenoid);
    SmartDashboard.putNumber("Compressor Current", Constants.pneumatics.PCM.getCompressorCurrent());
  
  }

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
    if(RobotContainer.readAuto){
      RobotContainer.m_autoSubsystem.addShifter(position);
    }
  }
}
