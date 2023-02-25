package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCubeCommand extends CommandBase {
    private final Intake m_intake;

    // constructor
    public IntakeCubeCommand(Intake intake) {
        this.m_intake = intake;

        addRequirements(m_intake);
    }

    @Override
    public void execute() {
        m_intake.drive(Constants.intake.CUBE_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.drive(0);
    }
}