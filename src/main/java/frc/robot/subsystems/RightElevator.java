// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

/**
 * The elevator subsystem uses PID to go to a given height. Unfortunately, in it's current state PID
 * values for simulation are different than in the real world do to minor differences.
 */
public class RightElevator extends PIDSubsystem {
  

  private static final double kP_real = 4;
  private static final double kI_real = 0.07;
  private static final double kP_simulation = 18;
  private static final double kI_simulation = 0.2;

  /** Create a new elevator subsystem. */
  public RightElevator() {
    super(new PIDController(kP_real, kI_real, 0));
    if (Robot.isSimulation()) { // Check for simulation and update PID values
      getController().setPID(kP_simulation, kI_simulation, 0);
    }
    getController().setTolerance(0.005);

    

    // Conversion value of potentiometer varies between the real world and
    // simulation
    if (Robot.isReal()) {
      Constants.sensors.rightLiftEncoder.setDistancePerPulse(-2.0 / 5);
    } else {
     
    }

    // Let's name everything on the LiveWindow
    
  }

  /** The log method puts interesting information to the SmartDashboard. */
  public void log() {
    SmartDashboard.putData("Elevator Encoder", Constants.sensors.rightLiftEncoder);
  }

  /**
   * Use the potentiometer as the PID sensor. This method is automatically called by the subsystem.
   */
  @Override
  public double getMeasurement() {
    return Constants.sensors.rightLiftEncoder.getDistance();
  }

  /** Use the motor as the PID output. This method is automatically called by the subsystem. */
  @Override
  public void useOutput(double output, double setpoint) {
    Constants.lift.m_rightLift.set(output);
  }

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}