// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.RightElevator;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** The main autonomous command to pickup and deliver the soda to the box. */
public class Autonomous extends SequentialCommandGroup {
  /** Create a new autonomous command. */
  public Autonomous(Drivetrain drive, RightElevator elevator) {
    addCommands();
     
        
  }
}
