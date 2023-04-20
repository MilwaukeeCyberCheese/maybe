package frc.robot.commands.auto;

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
    private final IntSupplier m_delay;
    private final IntSupplier m_actuateDelay;

    /**
     * Creates a new automatic intake Command.
     *
     * @param intake       subsystem controlling the intake
     * @param position     position for the intake to be set to
     * @param speed        speed to run the intake at
     * @param runtime      time to run intake for
     * @param delay        time to wait for the intake to start running
     * @param actuateDelay time to wait for the intake to change position
     * 
     */
    public IntakeAuto(Intake intake, Value position, DoubleSupplier speed, IntSupplier runtime, IntSupplier delay,
            IntSupplier actuateDelay) {
        this.m_intake = intake;
        this.m_position = position;
        this.m_speed = speed;
        this.m_runtime = runtime;
        this.m_delay = delay;
        this.m_actuateDelay = actuateDelay;
        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        // reset and restart timer
        timer.stop();
        timer.reset();
        timer.start();

    }

    // run whenever command is called
    @Override
    public void execute() {
        // set position after delay
        if (timer.getTime() > m_actuateDelay.getAsInt()) {
            m_intake.setPosition(m_position);
        }

        // run intake at speed
        if (timer.getTime() > m_delay.getAsInt()) {
            m_intake.drive(m_speed.getAsDouble());
        }

    }

    @Override
    public boolean isFinished() {
        // returns whether the time exceeds the time allotted to run
        return timer.getTime() > m_runtime.getAsInt();

    }

    // stop motor when finished
    @Override
    public void end(boolean interrupted) {
        // stops intake when finished
        m_intake.drive(0);
    }
}