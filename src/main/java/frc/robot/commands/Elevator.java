// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import frc.robot.Constants;
import frc.robot.subsystems.LeftElevator;
import frc.robot.subsystems.RightElevator;

import java.util.function.DoubleSupplier;


import edu.wpi.first.wpilibj2.command.CommandBase;

/* actuates the pistons to flop the intake down */
public class Elevator extends CommandBase {
  private final LeftElevator m_leftElevator;
private final RightElevator m_rightElevator;
private final DoubleSupplier m_position;

  public Elevator(DoubleSupplier position, LeftElevator lElevator, RightElevator rElevator) {
    m_leftElevator = lElevator;
    m_rightElevator = rElevator;
    m_position = position;
    addRequirements(m_leftElevator, m_rightElevator);
  }

  // Called just before this Command runs the first time
  @Override
  public void execute() {
    m_leftElevator.setSpeed( m_position.getAsDouble() * Constants.lift.LIFT_SPEED);
    m_rightElevator.setSpeed( m_position.getAsDouble() * Constants.lift.LIFT_SPEED);
  }

  
}
