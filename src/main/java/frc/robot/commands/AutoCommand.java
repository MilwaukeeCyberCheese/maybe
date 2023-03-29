package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.other.Stopwatch;
import frc.robot.subsystems.AutoSubsystem;
import frc.robot.subsystems.AutoSubsystemValues;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class AutoCommand extends CommandBase {
    private final AutoSubsystem m_autoSubsystem;
    private double auto;
    private final Intake m_intake;
    private final Drivetrain m_drivetrain;
    private final Stopwatch timer = new Stopwatch();
    private boolean balanceStarted = false;

    // constructor
    public AutoCommand(AutoSubsystem autoSubsystem, Intake intake, Drivetrain drivetrain) {
        this.m_autoSubsystem = autoSubsystem;
        this.m_intake = intake;
        this.m_drivetrain = drivetrain;
    }

    public void setAuto(int auto) {

        m_autoSubsystem.setAuto(auto);

        this.auto = auto;
    }

    @Override
    public void initialize() {
        timer.stop();
        timer.reset();
        timer.start();
        balanceStarted = false;
    }

    @Override
    public void execute() {
        if (auto == 3) {
            if (timer.getTime() < 0.7) {
                m_intake.drive(0.7);
            }

            if (timer.getTime() > 0.7
                    && Math.abs(Constants.balance.gyro.getPitch()) < Constants.balance.IMBALANCED_THRESHOLD_DEGREES
                    && !balanceStarted) {
                m_drivetrain.drive(0.1, 0);
            }

            if (timer.getTime() > 0.7
                    && Math.abs(Constants.balance.gyro.getPitch()) > Constants.balance.IMBALANCED_THRESHOLD_DEGREES) {
                balanceStarted = true;
                new AutoBalancer(m_drivetrain);
            }
        }
    }

    /**
     * This function ensures when the action is complete, the robot stops moving
     * 
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        timer.stop();
    }
}
