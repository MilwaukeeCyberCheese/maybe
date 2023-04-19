// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The elevator subsystem uses PID to go to a given height, or runs the lift
 * motor motors at a given speed
 */
public class ElevatorSubsystem extends SubsystemBase {
    public double speed;
    public double position;
    public double previousPosition;
    public double setPosition = 0;
    public boolean PIDenabled;
    private PIDController slidePid = new PIDController(Constants.lift.P, Constants.lift.I, Constants.lift.D);

    /** Create a new elevator subsystem. */
    public ElevatorSubsystem() {

        // set current limit for elevator motors
        Constants.controllers.leftLiftSpark.setSmartCurrentLimit(Constants.lift.MAX_STALL_AMPS,
                Constants.lift.MAX_FREE_AMPS, Constants.lift.STALL_RPM);
        Constants.controllers.rightLiftSpark.setSmartCurrentLimit(Constants.lift.MAX_STALL_AMPS,
                Constants.lift.MAX_FREE_AMPS, Constants.lift.STALL_RPM);

        // sets inversion of lift motors, inversion should always be different
        Constants.controllers.leftLiftSpark.setInverted(Constants.lift.LEFT_INVERTED);
        Constants.controllers.rightLiftSpark.setInverted(Constants.lift.RIGHT_INVERTED);

        // set tolerance of PID
        slidePid.setTolerance(Constants.lift.TOLERANCE);

    }

    /**
     * Set speed for lift
     *
     * @param speed Speed in range [-1,1]
     */
    public void setSpeed(double speed) {
        this.speed = speed;
        PIDenabled = false;
        Constants.controllers.leftLiftSpark.set(speed);
        Constants.controllers.rightLiftSpark.set(speed);
    }

    /**
     * Reset encoder positions of the lift to zero
     */
    public void zero() {
        Constants.sensors.leftLift.setPosition(0);
        Constants.sensors.rightLift.setPosition(0);
    }

    /**
     * Set a position for the lift
     *
     * @param setPosition position for the lift to run to
     */
    public void setPosition(double setPosition) {
        this.setPosition = setPosition;
        PIDenabled = true;
    }

    /** The log method puts interesting information to the SmartDashboard. */
    public void log() {

        // send actual position, and positon to set to
        SmartDashboard.putNumber("Slide Position", position);
        SmartDashboard.putNumber("Slide Set Position", setPosition);

        // send actual speed of motors
        SmartDashboard.putNumber("Left Slide Speed", Constants.controllers.leftLiftSpark.get());
        SmartDashboard.putNumber("Right Slide Speed", Constants.controllers.rightLiftSpark.get());

        // send speed to be set to
        SmartDashboard.putNumber("Elevator Speed: ", speed);

    }

    public boolean abort() {
        if (Constants.controllers.leftLiftSpark.getOutputCurrent() > Constants.lift.ABORT_AMPS
                || Constants.controllers.rightLiftSpark.getOutputCurrent() > Constants.lift.ABORT_AMPS) {
            return true;
        } else if(!slidePid.atSetpoint() && PIDenabled && Math.abs(previousPosition - position) < Constants.lift.ABORT_CHANGE){
        return true;}else {
            return false;
        }
    }

    /** Call log method every loop. */
    @Override
    public void periodic() {
        log();

        // limit set position of lift between predetermined safe positions
        setPosition = MathUtil.clamp(setPosition, Constants.lift.MIN_POSITION, Constants.lift.MAX_POSITION);

        // add lift positions to recording
        if (RobotContainer.readAuto) {
            RobotContainer.m_autoSubsystem.addLiftPos(setPosition);
        }

        // determine position of lift based off average of the position of both motors and set previous position
        previousPosition = position;
        position = (Constants.sensors.leftLift.getPosition() + Constants.sensors.rightLift.getPosition()) / 2;

        // set speed of lift using PID if PIDenabled
        if (PIDenabled) {
            speed = MathUtil.clamp(slidePid.calculate(position, setPosition), Constants.lift.SPEED_LIMITER_LOWER,
                    Constants.lift.SPEED_LIMITER_UPPER);
            Constants.controllers.leftLiftSpark.set(speed);
            Constants.controllers.rightLiftSpark.set(speed);

        } // otherwise the setPosition is to be the current position
        else {
            setPosition = position;
        }
    }
}