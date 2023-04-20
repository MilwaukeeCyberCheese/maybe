package frc.robot.commands.auto;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.AutoSubsystem;

public class RecordAuto extends CommandBase {
    private final AutoSubsystem m_autoSubsystem;
    private final BooleanSupplier m_recording;
    private final BooleanSupplier m_stopping;

    /**
     * Creates a new recording Command.
     *
     * @param autoSubsystem subsystem used for recording autonomous
     * @param recording     returns true if recording should start
     * @param stopping      returns true when recording ends
     * 
     */
    public RecordAuto(AutoSubsystem autoSubsystem, BooleanSupplier recording, BooleanSupplier stopping) {
        this.m_autoSubsystem = autoSubsystem;
        this.m_recording = recording;
        this.m_stopping = stopping;
        addRequirements(m_autoSubsystem);
    }

    @Override
    public void execute() {

        // clear prior values and set readAuto to true
        if (m_recording.getAsBoolean()) {
            m_autoSubsystem.clearShit();
            RobotContainer.readAuto = true;
            System.out.println("Started - Begin Tracking Autonomous");

        } // set readAuto to false and print out values
        else if (m_stopping.getAsBoolean()) {
            RobotContainer.readAuto = false;
            System.out.println("Ended - Finished Tracking Autonomous");
            m_autoSubsystem.printShit();

        }

    }
}
