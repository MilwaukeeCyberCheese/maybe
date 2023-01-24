package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShiftSubsystem extends SubsystemBase {

    /**
     * This function is called every few milliseconds when the robot is enabled
     * 
     * For this subsystem, this function helps record auto paths
     */
    // public void periodic() {
    //     if (RobotContainer.readAuto)
    //         RobotContainer.m_autoSubsystem.addServo(Constants.controllers.servo.getPosition());
    // }

    /**
     * This function is called when the button on the controller to operate this
     * subsystem is activated
     * 
     * @param speed The speed to move this subsystem at
     */
    public void drive() {
        Constants.pneumatics.leftShifter.toggle();
        Constants.pneumatics.leftShifter.toggle();
    }
}