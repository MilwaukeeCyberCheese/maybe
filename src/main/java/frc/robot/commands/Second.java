// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shifter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** Closes the claw until the limit switch is tripped. */
public class Second extends CommandBase {
  private final Shifter m_shifter;

  public Second(Shifter shifter) {
    m_shifter = shifter;
    addRequirements(m_shifter);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    m_shifter.setGear(Constants.drive.SECOND_GEAR);
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    // NOTE: Doesn't stop in simulation due to lower friction causing the
    // can to fall out
    // + there is no need to worry about stalling the motor or crushing the
    // can.
   
  }
}
