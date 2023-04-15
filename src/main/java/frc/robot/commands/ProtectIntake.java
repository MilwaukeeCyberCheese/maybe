// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/* actuates the pistons to flop the intake down */
public class ProtectIntake extends CommandBase {
  private final Intake m_intake;
  private boolean m_liftMoving;

  public ProtectIntake(Intake intake) {
    m_intake = intake;
    addRequirements(m_intake);
  }

  @Override
  public void execute() {
    m_liftMoving = RobotContainer.m_elevatorSubsystem.speed != 0.0;

    if (RobotContainer.m_drivetrain.throttleActual > 0.4)
      m_intake.setPosition(Constants.intake.intakeUp);

    if ((m_liftMoving && Constants.lift.MAX_INTAKE < RobotContainer.m_elevatorSubsystem.position)
        || !RobotContainer.m_elevatorSubsystem.PIDenabled) {
          m_intake.setPosition(Constants.intake.intakeDown);
    }
  }

}
