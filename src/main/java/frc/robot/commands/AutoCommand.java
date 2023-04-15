package frc.robot.commands;

import java.util.function.IntSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.other.Stopwatch;
import frc.robot.subsystems.AutoSubsystemValues;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class AutoCommand extends CommandBase {
    private final Intake m_intake;
    private final Drivetrain m_drivetrain;
    private final ElevatorSubsystem m_elevatorSubsystem;
    private final Shifter m_shifter;
    private final IntSupplier m_autoMode;
    private final Stopwatch timer = new Stopwatch();
    private boolean firstEdge = false;
    private boolean top = false;
    private boolean secondEdge = false;
    private boolean bottom = false;
    private boolean balanceStarted = false;
    private int stopwatchCounter = -1;
    private PIDController balancePid = new PIDController(Constants.balance.P, Constants.balance.I, Constants.balance.D);

    // constructor
    public AutoCommand(Intake intake, Drivetrain drivetrain,
            ElevatorSubsystem elevatorSubsystem,
            Shifter shifter, IntSupplier autoMode) {
        this.m_intake = intake;
        this.m_drivetrain = drivetrain;
        this.m_elevatorSubsystem = elevatorSubsystem;
        this.m_shifter = shifter;
        this.m_autoMode = autoMode;
        addRequirements(m_drivetrain, m_intake, m_elevatorSubsystem, m_shifter);
    }

    @Override
    public void initialize() {
        balancePid.setSetpoint(0);
        balancePid.setTolerance(Constants.balance.BALANCED_THRESHOLD_DEGREES);

        timer.stop();
        timer.reset();
        timer.start();
        stopwatchCounter = -1;
        firstEdge = false;
        top = false;
        secondEdge = false;
        bottom = false;
        balanceStarted = false;
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
                System.out.println("Out");
                m_intake.drive(Constants.intake.CONE_SPEED);
            } else {
                balance();
            }
        }
    }

    public void balance() {
        m_intake.setPosition(Constants.intake.intakeUp);
        m_intake.drive(0);
        double pitchAngleDegrees = MathUtil.clamp(Constants.balance.gyro.getRoll(), -14.6, 14.6);

        if (!!firstEdge || !top || !secondEdge || !bottom) {
            m_drivetrain.drive(Constants.balance.DRIVE_SPEED, 0);
            if (Math.abs(pitchAngleDegrees) >= Constants.balance.START_BALANCE_ANGLE) {
                firstEdge = true;
            }
            if (Math.abs(pitchAngleDegrees) <= 0.5 && firstEdge) {
                top = true;
            }
            if (Math.abs(pitchAngleDegrees) <= Constants.balance.START_BALANCE_ANGLE &&
                    top) {
                secondEdge = true;
            }
            if (Math.abs(pitchAngleDegrees) <= 0.5 && secondEdge) {
                bottom = true;
            }

        } else if (!balanceStarted) {
            m_drivetrain.drive(-Constants.balance.DRIVE_SPEED, 0);
            if (Math.abs(pitchAngleDegrees) >= Constants.balance.START_BALANCE_ANGLE) {
                balanceStarted = true;
            }
            
        } else {

            // double pitchAngleDegrees = -1 *
            // RobotContainer.m_filteredControllerTwo.getYLeft(0.1) * 10;

            double throttle = balancePid.calculate(pitchAngleDegrees);

            m_drivetrain.drive(throttle * Constants.balance.BALANCE_SPEED_MOD, 0);
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
        m_drivetrain.drive(0, 0);
        m_elevatorSubsystem.setPosition(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
