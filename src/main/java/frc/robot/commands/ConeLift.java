// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/* actuates the pistons to flop the intake down */
public class ConeLift extends CommandBase {
  private final ElevatorSubsystem m_elevatorSubsystem;
  private Double m_speed;
  private Double m_position;

  public ConeLift(ElevatorSubsystem elevatorSubsystem) {
    m_elevatorSubsystem = elevatorSubsystem;
    addRequirements(m_elevatorSubsystem);
  }

  @Override
  public void initialize() {

  }

  // Called just before this Command runs the first time
  @Override
  public void execute() {
    if (RobotContainer.m_intake.position == Constants.intake.intakeDown
        || (Constants.lift.MAX_INTAKE < m_elevatorSubsystem.position && m_elevatorSubsystem.PIDenabled)) {
      if (m_elevatorSubsystem.PIDenabled) {
        m_elevatorSubsystem
            .setPosition(Constants.lift.POSITION_TWO);
      }
    }
  }

}