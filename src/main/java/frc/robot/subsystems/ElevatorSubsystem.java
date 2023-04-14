// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The elevator subsystem uses PID to go to a given height. Unfortunately, in
 * it's current state PID
 * values for simulation are different than in the real world do to minor
 * differences.
 */
public class ElevatorSubsystem extends SubsystemBase {
    public double speed;
    public double position;
    public double setPosition;
    public boolean PIDenabled;
    private PIDController slidePid = new PIDController(Constants.lift.P, Constants.lift.I, Constants.lift.D);

    /** Create a new elevator subsystem. */
    public ElevatorSubsystem() {

        // Constants.sensors.leftLift.setInverted(Constants.lift.LEFT_INVERTED);
        // Constants.sensors.leftLift.setPositionConversionFactor(1);

        Constants.controllers.leftLiftSpark.restoreFactoryDefaults();

        Constants.controllers.leftLiftSpark.setInverted(Constants.lift.LEFT_INVERTED);
        Constants.controllers.rightLiftSpark.setInverted(Constants.lift.RIGHT_INVERTED);

        slidePid.setTolerance(0.3);

    }

    public void setSpeed(double speed) {
        this.speed = speed;
        PIDenabled = false;
        Constants.controllers.leftLiftSpark.set(speed);
        Constants.controllers.rightLiftSpark.set(speed);
    }

    public void zero() {
        Constants.sensors.leftLift.setPosition(0);
        Constants.sensors.rightLift.setPosition(0);
    }

    public void setPosition(double setPosition) {
        this.setPosition = setPosition;
        PIDenabled = true;
    }

    /** The log method puts interesting information to the SmartDashboard. */
    public void log() {

        // SmartDashboard.putNumber("Left Slide CPR",
        // Constants.sensors.leftLift.getCountsPerRevolution());
        SmartDashboard.putNumber("Slide Position", position);
        SmartDashboard.putNumber("Speed: ", speed);

    }

    /** Call log method every loop. */
    @Override
    public void periodic() {
        log();

        setPosition = MathUtil.clamp(setPosition, Constants.lift.MAX_POSITION, Constants.lift.MIN_POSITION);

        if (PIDenabled) {
            speed = slidePid.calculate(position, setPosition);
            Constants.controllers.leftLiftSpark.set(speed);
            Constants.controllers.rightLiftSpark.set(speed);
        }

        if (RobotContainer.readAuto) {
            RobotContainer.m_autoSubsystem.addLiftPos(setPosition);
        }

        position = (Constants.sensors.leftLift.getPosition() + Constants.sensors.rightLift.getPosition()) / 2;
    }
}