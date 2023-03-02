// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

/* Shifts the drivetrain into first gear */
public class IntakeUp extends CommandBase {
  private final Intake m_intake;

  public IntakeUp(Intake intake) {
    m_intake = intake;
    addRequirements(m_intake);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    m_intake.setPosition(Constants.intake.intakeUp);
  }

  
}
