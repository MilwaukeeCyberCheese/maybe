package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeSpeedy extends CommandBase {
    private final Intake m_intake;
    private final Double forward;
    private final Double reverse;

    // constructor
    public IntakeSpeedy(DoubleSupplier forward, DoubleSupplier reverse, Intake intake) {
        this.m_intake = intake;
        this.forward = forward.getAsDouble();
        this.reverse = reverse.getAsDouble();

        addRequirements(m_intake);
    }

    //run whenever command is called
    @Override
    public void execute() {
        m_intake.drive(forward - reverse);
    }

    //stop motor when finished
    @Override
    public void end(boolean interrupted) {
        m_intake.drive(0);
    }
}