// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

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
    m_liftMoving = Math.abs(RobotContainer.m_elevatorSubsystem.speed) > Constants.intake.PROTECT_INTAKE_THRESHOLD;

    if (Math.abs(RobotContainer.m_drivetrain.throttleActual) > 0.4 && RobotContainer.m_elevatorSubsystem.position < 5){
      m_intake.setPosition(Constants.intake.intakeUp);
    }
    if ((m_liftMoving && Constants.lift.MAX_INTAKE > RobotContainer.m_elevatorSubsystem.position)
        || !RobotContainer.m_elevatorSubsystem.PIDenabled) {
          m_intake.setPosition(Constants.intake.intakeDown);
          System.out.println(RobotContainer.m_elevatorSubsystem.speed);
    }
  }

  @Override
  public boolean isFinished(){
    return false;
  }

}
