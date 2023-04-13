package frc.robot.commands;

import java.lang.constant.Constable;
import java.util.function.IntSupplier;

import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.other.Stopwatch;
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
    private final IntSupplier m_autoMode;
    private final Stopwatch timer = new Stopwatch();
    private boolean balanceStarted = false;
    private PIDController balancePid = new PIDController(Constants.balance.P, Constants.balance.I, Constants.balance.D);

    // constructor
    public AutoCommand(AutoSubsystem autoSubsystem, Intake intake, Drivetrain drivetrain, LeftElevator leftElevator,
            RightElevator rightElevator, Shifter shifter, IntSupplier autoMode) {
        this.m_autoSubsystem = autoSubsystem;
        this.m_intake = intake;
        this.m_drivetrain = drivetrain;
        this.m_leftElevator = leftElevator;
        this.m_rightElevator = rightElevator;
        this.m_shifter = shifter;
        this.m_autoMode = autoMode;
        addRequirements(m_drivetrain, m_intake, m_leftElevator, m_rightElevator, m_shifter);
    }

    public void setAuto(int auto) {

        m_autoSubsystem.setAuto(auto);

        // this.auto = auto;
    }

    @Override
    public void initialize() {
        timer.stop();
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {

        if (m_autoMode.getAsInt() == 2) {

            Constants.pneumatics.shifterSolenoid.set(Constants.drive.FIRST_GEAR);
            if (timer.getTime() <= 2000) {
                balanceStarted = false;
                System.out.println("Out");
                m_intake.drive(Constants.intake.CONE_SPEED);
            } else {
                
                m_intake.drive(0);
                double pitchAngleDegrees = Constants.balance.gyro.getRoll();

                if (!balanceStarted) {
                    m_drivetrain.drive(-0.45, 0, true);
                    if (pitchAngleDegrees >= 13) {
                        balanceStarted = true;
                    }
                } else {

                    // double pitchAngleDegrees = -1 *
                    // RobotContainer.m_filteredControllerTwo.getYLeft(0.1) * 10;

                    double throttle = balancePid.calculate(pitchAngleDegrees);

                    m_drivetrain.drive(throttle * Constants.balance.BALANCE_SPEED_MOD, 0, true);
                }
            }
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
    public boolean isFinished() {
        return false;
    }
}
