// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/* sets lift to intake cube/at bottom */
public class CubeIntakePosition extends CommandBase {
  private final ElevatorSubsystem m_elevatorSubsystem;

  /**
   * Creates a new lift position Command.
   *
   * @param elevatorSubsystem subsystem controlling the elevators
   */
  public CubeIntakePosition(ElevatorSubsystem elevatorSubsystem) {
    m_elevatorSubsystem = elevatorSubsystem;
    addRequirements(m_elevatorSubsystem);
  }

  @Override
  public void initialize() {

  }

  // Called just before this Command runs the first time
  @Override
  public void execute() {

    // sets lift to postion
    m_elevatorSubsystem
        .setPosition(Constants.lift.CUBE_INTAKE_POSITION);
  }

  @Override
  public boolean isFinished() {
    // returns true when at position
    if (Math.abs(m_elevatorSubsystem.position - Constants.lift.CONE_INTAKE_POSITION) < Constants.lift.TOLERANCE) {
      return true;
    } else {
      return false;
    }
  }
}
