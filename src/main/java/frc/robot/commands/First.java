// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shifter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/* Shifts the drivetrain into first gear */
public class First extends CommandBase {
  private final Shifter m_shifter;

  public First(Shifter shifter) {
    m_shifter = shifter;
    addRequirements(m_shifter);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    m_shifter.setGear(Constants.drive.FIRST_GEAR);
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    
   
  }
}
