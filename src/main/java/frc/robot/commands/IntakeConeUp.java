package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeConeUp extends CommandBase {
    private final Intake m_intake;

      /**
   * Creates a new intake Command.
   *
   * @param intake subsystem controlling the intake
   * 
   */
    public IntakeConeUp(Intake intake) {
        this.m_intake = intake;

        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        //sets position of intake to up
        m_intake.setPosition(Constants.intake.intakeUp);
    }

    // run whenever command is called
    @Override
    public void execute() {
        //sets intake speed
        m_intake.drive(Constants.intake.CONE_SPEED);
    }

    // stop motor when finished
    @Override
    public void end(boolean interrupted) {
        //stops intake
        m_intake.drive(0);
    }
}