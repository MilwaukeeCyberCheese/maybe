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

  /**
   * Creates a new protect intake Command.
   *
   * @param intake subsystem controlling the intake
   */
  public ProtectIntake(Intake intake) {
    m_intake = intake;
    addRequirements(m_intake);
  }

  @Override
  public void execute() {
    // checks to see if the lift is moving within a certain tolerance
    m_liftMoving = Math.abs(RobotContainer.m_elevatorSubsystem.speed) > Constants.intake.LIFT_SPEED_THRESHOLD;

    // puts the intake up if the robot is driving above 0.4
    if ((Math.abs(RobotContainer.m_drivetrain.rotationActual) > Constants.intake.DRIVE_SPEED_THRESHOLD
        || Math.abs(RobotContainer.m_drivetrain.throttleActual) > Constants.intake.DRIVE_SPEED_THRESHOLD)
        && RobotContainer.m_elevatorSubsystem.position < Constants.lift.BELOW_UP_INTAKE) {
      m_intake.setPosition(Constants.intake.INTAKE_UP);
    }

    //puts the intake down if the elevator is moving within a certain range
    if ((m_liftMoving && Constants.lift.MAX_INTAKE > RobotContainer.m_elevatorSubsystem.position)
        || !RobotContainer.m_elevatorSubsystem.PIDenabled || (RobotContainer.m_elevatorSubsystem.protectIntake && Constants.lift.MAX_INTAKE > RobotContainer.m_elevatorSubsystem.position)) {
      m_intake.setPosition(Constants.intake.INTAKE_DOWN);
      System.out.println(RobotContainer.m_elevatorSubsystem.speed);
    }
  }

  @Override
  public boolean isFinished() {
    return false; //return false since it should be constantly running
  }



}
