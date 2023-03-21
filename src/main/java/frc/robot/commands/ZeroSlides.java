// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.LeftElevator;
import frc.robot.subsystems.RightElevator;

import edu.wpi.first.wpilibj2.command.CommandBase;

/* Shifts the drivetrain into first gear */
public class ZeroSlides extends CommandBase {
    private final LeftElevator m_leftElevator;
    private final RightElevator m_rightElevator;
    
      public ZeroSlides(LeftElevator lElevator, RightElevator rElevator) {
        m_leftElevator = lElevator;
        m_rightElevator = rElevator;
        addRequirements(m_leftElevator, m_rightElevator);
      }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    m_leftElevator.zero();
    m_rightElevator.zero();
  }

  
}
