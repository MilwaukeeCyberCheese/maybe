package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.other.Stopwatch;
import frc.robot.subsystems.Intake;

import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeAuto extends CommandBase {
    private final Intake m_intake;
    private final Value m_position;
    private final DoubleSupplier m_speed;
    private final Stopwatch timer = new Stopwatch();
    private final IntSupplier m_runtime;

    // constructor
    public IntakeAuto(Intake intake, Value position, DoubleSupplier speed, IntSupplier runtime) {
        this.m_intake = intake;
        this.m_position = position;
        this.m_speed = speed;
        this.m_runtime = runtime;
        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        timer.stop();
        timer.reset();
        timer.start();
        m_intake.setPosition(m_position);
    }

    // run whenever command is called
    @Override
    public void execute() {
        m_intake.drive(m_speed.getAsDouble());

    }

    @Override
    public boolean isFinished() {
        if (timer.getTime() > m_runtime.getAsInt()) {
            return true;
        } else {
            return false;
        }
    }

    // stop motor when finished
    @Override
    public void end(boolean interrupted) {
        m_intake.drive(0);
    }
}