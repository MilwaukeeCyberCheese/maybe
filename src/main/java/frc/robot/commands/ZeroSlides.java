// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/* Shifts the drivetrain into first gear */
public class ZeroSlides extends CommandBase {
    private final ElevatorSubsystem m_elevatorSubsystem;
    
      public ZeroSlides(ElevatorSubsystem elevatorSubsystem) {
        this.m_elevatorSubsystem = elevatorSubsystem;
        addRequirements(m_elevatorSubsystem);
      }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    m_elevatorSubsystem.zero();
  }

  
}
