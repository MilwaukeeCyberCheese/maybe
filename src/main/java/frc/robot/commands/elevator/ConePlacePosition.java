// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import frc.robot.Constants;
import frc.robot.other.Stopwatch;
import frc.robot.subsystems.ElevatorSubsystem;

import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/* Move the lift to the position for placing a cone */
public class ConePlacePosition extends CommandBase {
  private final ElevatorSubsystem m_elevatorSubsystem;
  private final Stopwatch timer = new Stopwatch();
  private final IntSupplier m_delay;

  /**
   * Creates a new lift position Command.
   *
   * @param elevatorSubsystem subsystem controlling the elevators
   */
  public ConePlacePosition(ElevatorSubsystem elevatorSubsystem, IntSupplier delay) {
    m_elevatorSubsystem = elevatorSubsystem;
    m_delay = delay;
    addRequirements(m_elevatorSubsystem);
  }

  @Override
  public void initialize() {
    timer.stop();
    timer.reset();
    timer.start();
    m_elevatorSubsystem.protectIntake(true);
  }

  // Called just before this Command runs the first time
  @Override
  public void execute() {

    // set position of lift
    if (timer.getTime() > m_delay.getAsInt()) {
      m_elevatorSubsystem
          .setPosition(Constants.lift.CONE_PLACE_POSITION);
    }

  }

  @Override
  public boolean isFinished() {
    // return true when lift is at position
    if (Math.abs(m_elevatorSubsystem.position - Constants.lift.CONE_PLACE_POSITION) < Constants.lift.TOLERANCE
        || m_elevatorSubsystem.abort()) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_elevatorSubsystem.protectIntake(false);
  }
}
