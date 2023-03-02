// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Shifter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** Shifts the drivetrain into second gear */
public class Second extends CommandBase {
  private final Shifter m_shifter;

  public Second(Shifter shifter) {
    m_shifter = shifter;
    addRequirements(m_shifter);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    m_shifter.setGear(Constants.drive.SECOND_GEAR);
  }

 
}
