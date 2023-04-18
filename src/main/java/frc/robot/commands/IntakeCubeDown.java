package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCubeDown extends CommandBase {
    private final Intake m_intake;

    /**
     * Creates a new intake Command.
     *
     * @param intake subsystem controlling the intake
     * 
     */
    public IntakeCubeDown(Intake intake) {
        this.m_intake = intake;

        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        // sets intake position to down
        m_intake.setPosition(Constants.intake.intakeDown);
    }

    // run whenever command is called
    @Override
    public void execute() {
        // sets speed of intake
        m_intake.drive(Constants.intake.CUBE_SPEED);
    }

    // stop motor when finished
    @Override
    public void end(boolean interrupted) {
        // stops intake when finisheds
        m_intake.drive(0);
    }
}