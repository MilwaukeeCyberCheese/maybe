package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AutoSubsystem;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LeftElevator;
import frc.robot.subsystems.RightElevator;
import frc.robot.subsystems.Shifter;

public class AutoCommand extends CommandBase {
    private final AutoSubsystem m_autoSubsystem;
    private final Intake m_intake;
    private final Drivetrain m_drivetrain;
    private final LeftElevator m_leftElevator;
    private final RightElevator m_rightElevator;
    private final Shifter m_shifter;

    // constructor
    public AutoCommand(AutoSubsystem autoSubsystem, Intake intake, Drivetrain drivetrain, LeftElevator leftElevator,
            RightElevator rightElevator, Shifter shifter) {
        this.m_autoSubsystem = autoSubsystem;
        this.m_intake = intake;
        this.m_drivetrain = drivetrain;
        this.m_leftElevator = leftElevator;
        this.m_rightElevator = rightElevator;
        this.m_shifter = shifter;
        addRequirements(m_drivetrain, m_intake, m_leftElevator, m_rightElevator, m_shifter);
    }

    public void setAuto(int auto) {

        m_autoSubsystem.setAuto(auto);

        // this.auto = auto;
    }

    @Override
    public void execute() {
        if (m_autoSubsystem.balance) {
            new AutoBalancer(m_drivetrain);
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

    @Override
    public boolean isFinished(){
        return false;
    }
}
