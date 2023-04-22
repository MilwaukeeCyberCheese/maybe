package frc.robot.commands.intake;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeConeCommand extends CommandBase {
    private final Intake m_intake;

    /**
     * Creates a new intake Command.
     *
     * @param intake subsystem controlling the intake
     * 
     */
    public IntakeConeCommand(Intake intake) {
        this.m_intake = intake;

        addRequirements(m_intake);
    }

    // run whenever command is called
    @Override
    public void execute() {
        // runs intake at given speed
        m_intake.drive(Constants.intake.CONE_SPEED);
    }

    // stop motor when finished
    @Override
    public void end(boolean interrupted) {
        // stops intake when finished
        m_intake.drive(0);
    }
}