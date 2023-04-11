package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CubeOutSpeedy extends CommandBase {
    private final Intake m_intake;

    // constructor
    public CubeOutSpeedy(Intake intake) {
        this.m_intake = intake;

        addRequirements(m_intake);
    }

    //run whenever command is called
    @Override
    public void execute() {
        m_intake.drive(Constants.intake.CONE_SPEEDY);
    }

    //stop motor when finished
    @Override
    public void end(boolean interrupted) {
        m_intake.drive(0);
    }
}