// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

/** Have the robot drive tank style. */
public class ArcadeDrive extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_left;
  private final DoubleSupplier m_right;

  /**
   * Creates a new ArcadeDrive command.
   *
   * @param left The control input for the left side of the drive
   * @param right The control input for the right sight of the drive
   * @param drivetrain The drivetrain subsystem to drive
   */
  public ArcadeDrive(DoubleSupplier left, DoubleSupplier right, Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    m_left = left;
    m_right = right;
    addRequirements(m_drivetrain);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    m_drivetrain.drive(m_left.getAsDouble() * Constants.drive.DRIVE_SPEED, m_right.getAsDouble() * Constants.drive.TURN_SPEED);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false; // Runs until interrupted
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0, 0);
  }
}
