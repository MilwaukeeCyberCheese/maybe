// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.elevator;

import frc.robot.Constants;

import frc.robot.subsystems.ElevatorSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/* generic elevator command */
public class ElevatorPID extends CommandBase {
  private final ElevatorSubsystem m_elevatorSubsystem;
  private final DoubleSupplier m_positionChange;

  private final BooleanSupplier m_PIDActive;

  /**
   * Creates a new lift position Command.
   *
   * @param positionChange    the amount by which the position is to change
   * @param elevatorSubsystem subsystem controlling the elevators
   * @param PIDactive         determines whether run using the PID or with direct
   *                          speed control
   * 
   */
  public ElevatorPID(DoubleSupplier positionChange, ElevatorSubsystem elevatorSubsystem, BooleanSupplier PIDActive) {
    m_elevatorSubsystem = elevatorSubsystem;
    m_positionChange = positionChange;
    m_PIDActive = PIDActive;
    addRequirements(m_elevatorSubsystem);
  }

  @Override
  public void initialize() {

  }

  // Called just before this Command runs the first time
  @Override
  public void execute() {

    // sets position if PID is active
    if (m_PIDActive.getAsBoolean()) {
      m_elevatorSubsystem
          .setPosition(m_elevatorSubsystem.position + m_positionChange.getAsDouble() * Constants.lift.PID_CHANGE_SPEED);
    } // sets a speed if PID is not active
    else {
      m_elevatorSubsystem.setSpeed(m_positionChange.getAsDouble() * Constants.lift.DIRECT_LIFT_SPEED);
    }

  }

  @Override
  public boolean isFinished(){
    return Constants.lift.TOLERANCE <= Math.abs(ElevatorSubsystem.position - ElevatorSubsystem.setPosition);
  }

}