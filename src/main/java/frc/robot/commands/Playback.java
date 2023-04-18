package frc.robot.commands;

import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AutoSubsystemValues;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class Playback extends CommandBase {
    private final Intake m_intake;
    private final Drivetrain m_drivetrain;
    private final ElevatorSubsystem m_elevatorSubsystem;
    private final Shifter m_shifter;
    private int stopwatchCounter = -1;
    private final IntSupplier m_whichOne;

    public Playback(Intake intake, Drivetrain drivetrain,
            ElevatorSubsystem elevatorSubsystem,
            Shifter shifter, IntSupplier whichOne) {
        this.m_intake = intake;
        this.m_drivetrain = drivetrain;
        this.m_elevatorSubsystem = elevatorSubsystem;
        this.m_shifter = shifter;
        this.m_whichOne = whichOne;
        addRequirements(m_intake);
    }

    @Override
    public void execute() {
        if (stopwatchCounter < AutoSubsystemValues.frontLeftSpeeds.object[m_whichOne].size() - 1) {
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
        }
    }
}