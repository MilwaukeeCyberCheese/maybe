// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto.timeBased;

import frc.robot.Constants;
import frc.robot.other.Stopwatch;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

/** Have the robot drive arcade style. */
public class DriveToTime extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_throttle;
  private final DoubleSupplier m_rotation;
  private final BooleanSupplier m_slow;
  private final BooleanSupplier m_turbo;
  private final IntSupplier m_runtime;
  private final Stopwatch timer = new Stopwatch();
  private double m_turnSpeedMod = 1;
  private double m_driveSpeedMod = 1;

  /**
   * Creates a new ArcadeDrive command.
   *
   * @param throttle   The control input for speed
   * @param rotation   The control input for rotation
   * @param slow  whether or not to enable slow mode
   * @param turbo whether or not to enable turbo mode
   * @param runtime how long to drive for
   * @param drivetrain The drivetrain subsystem to drive
   * 
   */
  public DriveToTime(DoubleSupplier throttle, DoubleSupplier rotation, BooleanSupplier slow, BooleanSupplier turbo, IntSupplier runtime,
      Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    m_throttle = throttle;
    m_rotation = rotation;
    m_slow = slow;
    m_turbo = turbo;
    m_runtime = runtime;
    addRequirements(m_drivetrain);
  }

@Override
public void initialize(){
    timer.stop();
    timer.reset();
    timer.start();
}

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

    //set to slow, turbo, or regular drive speed
    if (m_slow.getAsBoolean()) {
      m_turnSpeedMod = Constants.drive.SLOW_TURN_SPEED;
      m_driveSpeedMod = Constants.drive.SLOW_DRIVE_SPEED;
    } else if (m_turbo.getAsBoolean()) {
      m_turnSpeedMod = Constants.drive.TURBO_TURN_SPEED;
      m_driveSpeedMod = Constants.drive.TURBO_DRIVE_SPEED;
    } else {
      m_turnSpeedMod = Constants.drive.DEFAULT_TURN_SPEED;
      m_driveSpeedMod = Constants.drive.DEFAULT_DRIVE_SPEED;
    }

    // set speeds of drivetrain relative to regular, turbo, or slow
    m_drivetrain.drive(m_throttle.getAsDouble() * m_driveSpeedMod,
        m_rotation.getAsDouble() * m_turnSpeedMod);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return timer.getTime() > m_runtime.getAsInt(); // Runs until interrupted since drivetrain should be always on
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    // sets motors to 0 so they don't keep moving
    m_drivetrain.drive(0, 0);
  }
}
