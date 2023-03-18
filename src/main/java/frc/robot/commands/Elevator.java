// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.LeftElevator;
import frc.robot.subsystems.RightElevator;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/* actuates the pistons to flop the intake down */
public class Elevator extends CommandBase {
  private final LeftElevator m_leftElevator;
  private final RightElevator m_rightElevator;
  private final DoubleSupplier m_positionUp;
  private final DoubleSupplier m_positionDown;
  private final BooleanSupplier m_limited;

  public Elevator(DoubleSupplier positionUp, DoubleSupplier positionDown, LeftElevator lElevator,
      RightElevator rElevator, BooleanSupplier limited) {
    m_leftElevator = lElevator;
    m_rightElevator = rElevator;
    m_positionUp = positionUp;
    m_positionDown = positionDown;
    m_limited = limited;
    addRequirements(m_leftElevator, m_rightElevator);
  }

  // Called just before this Command runs the first time
  @Override
  public void execute() {
    m_leftElevator.setSpeed((m_positionUp.getAsDouble() - m_positionDown.getAsDouble()) * Constants.lift.LIFT_SPEED, m_limited.getAsBoolean());
    m_rightElevator.setSpeed((m_positionUp.getAsDouble() - m_positionDown.getAsDouble()) * Constants.lift.LIFT_SPEED, m_limited.getAsBoolean());
  }

}
