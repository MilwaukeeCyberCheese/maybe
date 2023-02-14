// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.LeftElevator;
import frc.robot.subsystems.RightElevator;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Move the elevator to a given location. This command finishes when it is within the tolerance, but
 * leaves the PID loop running to maintain the position. Other commands using the elevator should
 * make sure they disable PID!
 */
public class SetElevatorSetpoint extends CommandBase {
  private final LeftElevator m_leftElevator;
  private final RightElevator m_rightElevator;
  private final double m_setpoint;

  /**
   * Create a new SetElevatorSetpoint command.
   *
   * @param setpoint The setpoint to set the elevator to
   * @param elevator The elevator to use
   */
  public SetElevatorSetpoint(double setpoint, LeftElevator leftElevator, RightElevator rightElevator) {
    m_leftElevator = leftElevator;
    m_rightElevator = rightElevator;
    m_setpoint = setpoint; //lobsters4eva
    addRequirements(m_leftElevator, m_rightElevator);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    m_leftElevator.setSetpoint(m_setpoint);
    m_rightElevator.setSetpoint(m_setpoint);
    m_leftElevator.enable();
    m_rightElevator.enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return m_leftElevator.getController().atSetpoint() && m_rightElevator.getController().atSetpoint();
  }
}
