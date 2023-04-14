// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LeftElevator;
import frc.robot.subsystems.RightElevator;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/* actuates the pistons to flop the intake down */
public class ElevatorPID extends CommandBase {
  private final ElevatorSubsystem m_elevatorSubsystem;
  private final DoubleSupplier m_positionChange;
  private Double m_speed;
  private Double m_position;
  private final BooleanSupplier m_PIDActive;

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

    if (m_PIDActive.getAsBoolean()) {
      m_elevatorSubsystem.setPosition(m_elevatorSubsystem.position + m_positionChange.getAsDouble() * Constants.lift.LIFT_SPEED,
          m_PIDActive.getAsBoolean());
    } else{
      m_elevatorSubsystem.setSpeed(m_positionChange.getAsDouble() * Constants.lift.LIFT_SPEED, m_PIDActive.getAsBoolean());
    }
  }

}