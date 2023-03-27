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

import frc.robot.Constants;

/** Have the robot drive arcade style. */
public class AutoBalancer extends CommandBase {
  private final Drivetrain m_drivetrain;
  private Boolean autoBalanceMode;
  private double throttle;

  /**
   * Creates a new AutoBalance Command.
   *
   * @param drivetrain The drivetrain subsystem to drive
   */
  public AutoBalancer(Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    double pitchAngleDegrees = Constants.balance.gyro.getPitch();

    if (!autoBalanceMode &&
        (Math.abs(pitchAngleDegrees) >= Math.abs(Constants.balance.IMBALANCED_THRESHOLD_DEGREES))) {
      autoBalanceMode = true;
    } else if (autoBalanceMode &&
        (Math.abs(pitchAngleDegrees) <= Math.abs(Constants.balance.BALANCED_THRESHOLD_DEGREES))) {
      autoBalanceMode = false;
    }

    if (autoBalanceMode) {
      double pitchAngleRadians = pitchAngleDegrees * (Math.PI / 180.0);
      throttle = Math.sin(pitchAngleRadians) * -1;
    }

    m_drivetrain.drive(throttle * Constants.balance.BALANCE_SPEED_MOD, 0);

  }

  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false; // Runs until interrupted since should run till auto is over
  }

 

}