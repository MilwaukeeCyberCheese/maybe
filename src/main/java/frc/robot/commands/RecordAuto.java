package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.AutoSubsystem;

public class RecordAuto extends CommandBase {
    private final AutoSubsystem m_autoSubsystem;

    // constructor
    public RecordAuto(AutoSubsystem autoSubsystem) {
        this.m_autoSubsystem = autoSubsystem;
    }

    @Override
    public void execute() {

       

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
