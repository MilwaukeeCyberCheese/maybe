// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /**
   * The Drivetrain subsystem incorporates the sensors and actuators attached to the robots chassis.
   * These include four drive motors, a left and right encoder and a gyro.
   */
  
  private final AnalogInput m_rangefinder = new AnalogInput(6);
  private final AnalogGyro m_gyro = new AnalogGyro(1);

  /** Create a new drivetrain subsystem. */
  public Drivetrain() {
    super();

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    Constants.controllers.leftFrontSpark.setInverted(Constants.drive.LEFT_FRONT_INVERTED);
    Constants.controllers.leftRearSpark.setInverted(Constants.drive.LEFT_REAR_INVERTED);
    Constants.controllers.rightFrontSpark.setInverted(Constants.drive.RIGHT_FRONT_INVERTED);
    Constants.controllers.rightRearSpark.setInverted(Constants.drive.RIGHT_REAR_INVERTED);
    // Encoders may measure differently in the real world and in
    // simulation. In this example the robot moves 0.042 barleycorns
    // per tick in the real world, but the simulated encoders
    // simulate 360 tick encoders. This if statement allows for the
    // real robot to handle this difference in devices.
    if (Robot.isReal()) {
      Constants.sensors.m_leftEncoder.setDistancePerPulse(Constants.sensors.LEFT_DRIVE_ENCODER_DISTANCE_PER_PULSE);
      Constants.sensors.m_rightEncoder.setDistancePerPulse(Constants.sensors.RIGHT_DRIVE_ENCODER_DISTANCE_PER_PULSE);
    } else {
      // Circumference = diameter in feet * pi. 360 tick simulated encoders.
      Constants.sensors.m_leftEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
      Constants.sensors.m_rightEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
    }

    // Let's name the sensors on the LiveWindow
    addChild("Drive", Constants.drive.m_drive);
    addChild("Left Encoder", Constants.sensors.m_leftEncoder);
    addChild("Right Encoder", Constants.sensors.m_rightEncoder);
    addChild("Rangefinder", m_rangefinder);
    addChild("Gyro", m_gyro);
  }

  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {
    SmartDashboard.putNumber("Left Distance", Constants.sensors.m_leftEncoder.getDistance());
    SmartDashboard.putNumber("Right Distance", Constants.sensors.m_rightEncoder.getDistance());
    SmartDashboard.putNumber("Left Speed", Constants.sensors.m_leftEncoder.getRate());
    SmartDashboard.putNumber("Right Speed", Constants.sensors.m_rightEncoder.getRate());
    SmartDashboard.putNumber("Gyro", m_gyro.getAngle());
  }

  /**
   * Tank style driving for the Drivetrain.
   *
   * @param left Speed in range [-1,1]
   * @param right Speed in range [-1,1]
   */
  public void drive(double left, double right) {
    Constants.drive.m_drive.arcadeDrive(left, right);
  }

  /**
   * Get the robot's heading.
   *
   * @return The robots heading in degrees.
   */
  public double getHeading() {
    return m_gyro.getAngle();
  }

  /** Reset the robots sensors to the zero states. */
  public void reset() {
    m_gyro.reset();
    Constants.sensors.m_leftEncoder.reset();
    Constants.sensors.m_rightEncoder.reset();
  }

  /**
   * Get the average distance of the encoders since the last reset.
   *
   * @return The distance driven (average of left and right encoders).
   */
  public double getDistance() {
    return (Constants.sensors.m_leftEncoder.getDistance() + Constants.sensors.m_rightEncoder.getDistance()) / 2;
  }

  /**
   * Get the distance to the obstacle.
   *
   * @return The distance to the obstacle detected by the rangefinder.
   */
  public double getDistanceToObstacle() {
    // Really meters in simulation since it's a rangefinder...
    return m_rangefinder.getAverageVoltage();
  }

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
