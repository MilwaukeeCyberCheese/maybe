// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

/** Have the robot drive arcade style. */
public class AutoBalancer extends CommandBase {
  private final Drivetrain m_drivetrain;

  /**
   * Creates a new ArcadeDrive command.
   *
   * @param throttle   The control input for the left side of the drive
   * @param rotation   The control input for the right sight of the drive
   * @param slow  whether or not to enable slow mode
   * @param turbo whether or not to enable turbo mode
   * @param drivetrain The drivetrain subsystem to drive
   */
  public AutoBalancer(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    

    // set speeds of drivetrain relative to limits
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false; // Runs until interrupted since should run till auto is over
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    // sets motors to 0 so they don't keep moving
    m_drivetrain.drive(0, 0);
  }
}
