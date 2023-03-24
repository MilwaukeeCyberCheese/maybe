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
public class ArcadeDrive extends CommandBase {
  private static final Intake m_intake = new Intake();
  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_throttle;
  private final DoubleSupplier m_rotation;
  private final BooleanSupplier m_slow;
  private final BooleanSupplier m_turbo;
  private double m_turnSpeedMod = 1;
  private double m_driveSpeedMod = 1;

  /**
   * Creates a new ArcadeDrive command.
   *
   * @param throttle   The control input for the left side of the drive
   * @param rotation   The control input for the right sight of the drive
   * @param drivetrain The drivetrain subsystem to drive
   */
  public ArcadeDrive(DoubleSupplier throttle, DoubleSupplier rotation, BooleanSupplier slow, BooleanSupplier turbo,
      Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    m_throttle = throttle;
    m_rotation = rotation;
    m_slow = slow;
    m_turbo = turbo;
    addRequirements(m_drivetrain);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    if (m_slow.getAsBoolean()) {
      m_turnSpeedMod = Constants.drive.SLOW_TURN_SPEED;
      m_driveSpeedMod = Constants.drive.SLOW_DRIVE_SPEED;
    } else if (m_turbo.getAsBoolean()) {
      m_turnSpeedMod = Constants.drive.TURBO_TURN_SPEED;
      m_driveSpeedMod = Constants.drive.TURBO_DRIVE_SPEED;
    } else {
      m_turnSpeedMod = 1;
      m_driveSpeedMod = 1;
    }

    // set speeds of drivetrain relative to limits
    m_drivetrain.drive(m_throttle.getAsDouble() * Constants.drive.DRIVE_SPEED * m_driveSpeedMod,
        m_rotation.getAsDouble() * Constants.drive.TURN_SPEED * m_turnSpeedMod);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false; // Runs until interrupted since drivetrain should be always on
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    // sets motors to 0 so they don't keep moving
    m_drivetrain.drive(0, 0);
  }
}
