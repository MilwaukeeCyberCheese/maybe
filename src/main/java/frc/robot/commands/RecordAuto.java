package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.AutoSubsystem;

public class RecordAuto extends CommandBase {
    private final AutoSubsystem m_autoSubsystem;
    private final BooleanSupplier m_recording;
    private final BooleanSupplier m_stopping;

    // constructor
    public RecordAuto(AutoSubsystem autoSubsystem, BooleanSupplier recording, BooleanSupplier stopping) {
        this.m_autoSubsystem = autoSubsystem;
        this.m_recording = recording;
        this.m_stopping = stopping;
        addRequirements(m_autoSubsystem);
    }

    @Override
    public void execute() {

        if (m_recording.getAsBoolean()) {
            m_autoSubsystem.clearShit();
            RobotContainer.readAuto = true;
            System.out.println("Started - Begin Tracking Autonomous");

        } else if(m_stopping.getAsBoolean()){
            RobotContainer.readAuto = false;
            System.out.println("Ended - Finished Tracking Autonomous");
            m_autoSubsystem.printSpeeds();

        }

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
