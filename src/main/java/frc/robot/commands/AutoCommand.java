package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.other.Stopwatch;
import frc.robot.subsystems.AutoSubsystem;
import frc.robot.subsystems.AutoSubsystemValues;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class AutoCommand extends CommandBase {
    private final AutoSubsystem m_autoSubsystem;
    private Stopwatch timer = new Stopwatch();
    private Intake m_intake = new Intake();

    private Drivetrain m_drivetrain = new Drivetrain();

    // constructor
    public AutoCommand(AutoSubsystem autoSubsystem) {
        this.m_autoSubsystem = autoSubsystem;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        System.out.println("timer started");

    }

    @Override
    public void execute() {

        if (timer.getTime() < 3000) {
            m_intake.drive(0.7);
        } else {
            m_intake.drive(0);
        }
        if (timer.getTime() > 3500 && timer.getTime() < 6500) {
            m_drivetrain.drive(0.5, 0);
        } else {
            m_drivetrain.drive(0, 0);
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
