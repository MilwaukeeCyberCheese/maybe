package frc.robot.commands;

import java.util.function.IntSupplier;

import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.lift;
import frc.robot.other.Stopwatch;
import frc.robot.subsystems.AutoSubsystem;
import frc.robot.subsystems.AutoSubsystemValues;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class AutoCommand extends CommandBase {
    private final AutoSubsystem m_autoSubsystem;
    private final Intake m_intake;
    private final Drivetrain m_drivetrain;
    private final ElevatorSubsystem m_elevatorSubsystem;
    private final Shifter m_shifter;
    private final IntSupplier m_autoMode;
    private final Stopwatch timer = new Stopwatch();
    private boolean balanceStarted = false;
    private int stopwatchCounter = -1;
    private PIDController balancePid = new PIDController(Constants.balance.P, Constants.balance.I, Constants.balance.D);

    // constructor
    public AutoCommand(AutoSubsystem autoSubsystem, Intake intake, Drivetrain drivetrain,
            ElevatorSubsystem elevatorSubsystem,
            Shifter shifter, IntSupplier autoMode) {
        this.m_autoSubsystem = autoSubsystem;
        this.m_intake = intake;
        this.m_drivetrain = drivetrain;
        this.m_elevatorSubsystem = elevatorSubsystem;
        this.m_shifter = shifter;
        this.m_autoMode = autoMode;
        addRequirements(m_drivetrain, m_intake, m_elevatorSubsystem, m_shifter);
    }

    @Override
    public void initialize() {
        timer.stop();
        timer.reset();
        timer.start();
        stopwatchCounter = -1;
    }

    @Override
    public void execute() {
        if (m_autoMode.getAsInt() == 1) {
            if (stopwatchCounter < AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.size() - 1) {
                stopwatchCounter++;

                double frontLeft = AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(stopwatchCounter);
                double frontRight = AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(stopwatchCounter);
                double backLeft = AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(stopwatchCounter);
                double backRight = AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(stopwatchCounter);

                m_drivetrain.setWheelSpeeds(frontLeft, frontRight, backLeft, backRight);

                double intakeSpeed = AutoSubsystemValues.intaking.intaking.get(stopwatchCounter);

                m_intake.drive(intakeSpeed);

                double liftPos = AutoSubsystemValues.liftPos.liftPos.get(stopwatchCounter);

                m_elevatorSubsystem.setPosition(liftPos);

                Value intakePos = AutoSubsystemValues.intakePos.intakePos.get(stopwatchCounter);

                m_intake.setPosition(intakePos);

                boolean gear = AutoSubsystemValues.gear.gear.get(stopwatchCounter);

                m_shifter.setGear(gear);
            } else {
                end(false);
            }
        } else if (m_autoMode.getAsInt() == 2) {

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
        m_intake.drive(0);
        m_drivetrain.drive(0, 0, true);
        m_elevatorSubsystem.setPosition(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
