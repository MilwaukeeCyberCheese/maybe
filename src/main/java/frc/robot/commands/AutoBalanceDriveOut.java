// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shifter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** Have the robot drive arcade style. */
public class AutoBalanceDriveOut extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final Shifter m_shifter;
  private final Intake m_intake;
  private boolean balanceStarted = false;
  private boolean top = false;
  private boolean firstEdge = false;
  private boolean secondEdge = false;
  private boolean bottom = false;
  private PIDController balancePid = new PIDController(Constants.balance.P, Constants.balance.I, Constants.balance.D);

  /**
   * Creates a new AutoBalance Command.
   *
   * @param drivetrain The drivetrain subsystem to drive
   * @param shifter    Shifter for the gearboxes
   * @param intake     intake for cones and cubes
   */
  public AutoBalanceDriveOut(Drivetrain drivetrain, Shifter shifter, Intake intake) {
    m_drivetrain = drivetrain;
    m_shifter = shifter;
    m_intake = intake;
    addRequirements(m_drivetrain, m_shifter, m_intake);

  }

  @Override
  public void initialize() {
    // set to first for more torque
    new First(m_shifter);

    // reset checker variables
    balanceStarted = false;
    firstEdge = false;
    top = false;
    secondEdge = false;
    bottom = false;

    // initialize PID
    balancePid.setSetpoint(0);
    balancePid.setTolerance(Constants.balance.BALANCED_THRESHOLD_DEGREES);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    // stow intake and stop
    m_intake.setPosition(Constants.intake.INTAKE_UP);
    m_intake.drive(0);

    // clamp gyro angle to minimize impact from bouncing
    double pitchAngleDegrees = MathUtil.clamp(Constants.balance.gyro.getRoll(), -Constants.balance.BOUNCE_THRESHOLD,
        Constants.balance.BOUNCE_THRESHOLD);

    // check for passing over each edge to determine when driven fully out
    if (!firstEdge || !top || !secondEdge || !bottom) {
      m_drivetrain.drive(Constants.balance.DRIVE_SPEED, 0);
      if (Math.abs(pitchAngleDegrees) >= Constants.balance.START_BALANCE_ANGLE) {
        firstEdge = true;
      }
      if (Math.abs(pitchAngleDegrees) <= 0.5 && firstEdge) {
        top = true;
      }
      if (Math.abs(pitchAngleDegrees) <= Constants.balance.START_BALANCE_ANGLE &&
          top) {
        secondEdge = true;
      }
      if (Math.abs(pitchAngleDegrees) <= 0.5 && secondEdge) {
        bottom = true;
      }

    } // check for floor outside community zone
    else if (!balanceStarted) {
      m_drivetrain.drive(-Constants.balance.DRIVE_SPEED, 0);
      if (Math.abs(pitchAngleDegrees) >= Constants.balance.START_BALANCE_ANGLE) {
        balanceStarted = true;
      }

    } else {

      // simulate gyro with controller
      // double pitchAngleDegrees = -1 *
      // RobotContainer.m_filteredControllerTwo.getYLeft(0.1) * 10;

      // set throttle and drive drivetrain
      double throttle = balancePid.calculate(pitchAngleDegrees);
      m_drivetrain.drive(throttle * Constants.balance.BALANCE_SPEED_MOD, 0);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    // Y-button ends command
    if (RobotContainer.m_filteredController.getYButton()) {
      return true;
    } else {
      return false;
    }
  }
}