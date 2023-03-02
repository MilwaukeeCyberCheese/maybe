// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The intakeShifter controls the positioning of the intake
 */
public class IntakeShifter extends SubsystemBase {

  /** Create a new shifter subsystem. */
  public IntakeShifter() {
    /* empty because there's nothing to put into it */

  }

  public void setPosition(Value position) {
    Constants.pneumatics.intakeSolenoid.set(position);
  }

  /** The log method puts the status of each solenoid to SmartDashboard */
  public void log() {
    SmartDashboard.putBoolean("Left Solenoid", Constants.pneumatics.lSolenoid.get());
  }

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
