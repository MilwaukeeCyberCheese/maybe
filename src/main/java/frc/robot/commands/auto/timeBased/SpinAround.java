// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto.timeBased;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;
/** Have the robot drive arcade style. */
public class SpinAround extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_angle;
  private PIDController m_spinny = new PIDController(Constants.balance.SpinP, Constants.balance.SpinI, Constants.balance.SpinD);
  private double rotation;

  /**
   * Creates a new ArcadeDrive command.
   *
   * @param throttle   The control input for the left side of the drive
   * @param angle   The control input for the right sight of the drive
   * @param slow  whether or not to enable slow mode
   * @param turbo whether or not to enable turbo mode
   * @param drivetrain The drivetrain subsystem to drive
   * @param runtime how long to drive for
   */
  public SpinAround(DoubleSupplier angle,
      Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    m_angle = angle;
    addRequirements(m_drivetrain);
  }

@Override
public void initialize(){

    m_spinny.setSetpoint((Constants.balance.gyro.getAngle() % 360) - m_angle.getAsDouble());
    m_spinny.setTolerance(Constants.balance.SPIN_TOLERANCE);
}

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

    //set to slow, turbo, or regular drive speed
    rotation = MathUtil.clamp(m_spinny.calculate(Constants.balance.gyro.getAngle()), -1, 1);

    // set speeds of drivetrain relative to regular, turbo, or slow
   m_drivetrain.drive(0, rotation);

   SmartDashboard.putData("PID Spin", m_spinny);
   SmartDashboard.putNumber("Pid Setpoint", m_spinny.getSetpoint());
   SmartDashboard.putNumber("Spin Error", m_spinny.getPositionError());
   SmartDashboard.putNumber("Rotation Spinny", rotation);
   SmartDashboard.putBoolean("Spin At Position", m_spinny.atSetpoint());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return m_spinny.atSetpoint(); // Runs until interrupted since drivetrain should be always on
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    // sets motors to 0 so they don't keep moving
    m_drivetrain.drive(0, 0);
  }
}
