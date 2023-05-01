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

  /** Create a new shifter subsystem. */
  public Shifter() {
    /* empty because there's nothing initialize */

  }

  /**
   * Shifting of gears for the drivetrain
   *
   * @param gear gear to set the intake to
   * 
   */
  public void setGear(boolean gear) {
    Constants.pneumatics.shifterSolenoid.set(gear);

  }

  /** The log method puts the status of each solenoid to SmartDashboard */
  public void log() {
    // logs position of solenoid
    SmartDashboard.putBoolean("Gear", Constants.pneumatics.shifterSolenoid.get());

    // logs compressor current
    SmartDashboard.putNumber("Compressor Current", Constants.pneumatics.PCM.getCompressorCurrent());

  }

  /** Call log method every loop. */
  @Override
  public void periodic() {
    // adds gear to recording
    if (RobotContainer.readAuto) {
      RobotContainer.m_autoSubsystem.addShifter(Constants.pneumatics.shifterSolenoid.get());
    }

    log();
  }
}
