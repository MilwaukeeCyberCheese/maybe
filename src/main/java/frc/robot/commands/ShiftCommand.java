package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShiftSubsystem;

public class ShiftCommand extends CommandBase {
    private final ShiftSubsystem m_servoSubsystem;

    //constructor
    public ShiftCommand(ShiftSubsystem m_servoSubsystem) {
        this.m_servoSubsystem = m_servoSubsystem;

        addRequirements(m_servoSubsystem);
    }

    @Override
    public void execute() {
        m_servoSubsystem.drive();
    }

    /**
     * This function ensures when the action is complete, the robot stops moving
     * 
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        
    }
}