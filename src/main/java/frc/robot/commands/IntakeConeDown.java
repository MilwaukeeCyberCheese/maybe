package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeConeDown extends CommandBase {
    private final Intake m_intake;

    // constructor
    public IntakeConeDown(Intake intake) {
        this.m_intake = intake;

        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        m_intake.setPosition(Constants.intake.intakeDown);
    }

    // run whenever command is called
    @Override
    public void execute() {
        m_intake.drive(Constants.intake.CONE_SPEED);
    }

    // stop motor when finished
    @Override
    public void end(boolean interrupted) {
        m_intake.drive(0);
    }
}