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

    /**
     * Creates a new intake Command.
     *
     * @param intake           subsystem controlling the intake
     * @param drivetrain       system to control the drivetrain
     * @param elevatorSubsytem system to control the lift
     * @param shifter          system to control the gearboxes
     * @param whichOne         determines which recorded autonomous is played back,
     *                         recordings can be found in
     *                         {@link AutoSubsystemValues}
     * 
     */
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
        // check for time running to exceed size
        if (stopwatchCounter < AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(m_whichOne.getAsInt()).size()
                - 1) {
            // increment counter
            stopwatchCounter++;

            // get speeds for drivetrain wheels
            double frontLeft = AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(m_whichOne.getAsInt())
                    .get(stopwatchCounter);
            double frontRight = AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(m_whichOne.getAsInt())
                    .get(stopwatchCounter);
            double backLeft = AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(m_whichOne.getAsInt())
                    .get(stopwatchCounter);
            double backRight = AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(m_whichOne.getAsInt())
                    .get(stopwatchCounter);

            // set speeds of wheels
            m_drivetrain.setWheelSpeeds(frontLeft, frontRight, backLeft, backRight);

            //get speed of intake
            double intakeSpeed = AutoSubsystemValues.intaking.intaking.get(m_whichOne.getAsInt()).get(stopwatchCounter);

            //set speed of intake
            m_intake.drive(intakeSpeed);

            //get position of lift
            double liftPos = AutoSubsystemValues.liftPos.liftPos.get(m_whichOne.getAsInt()).get(stopwatchCounter);

            //set position of lift
            m_elevatorSubsystem.setPosition(liftPos);

            //get position of intake
            Value intakePos = AutoSubsystemValues.intakePos.intakePos.get(m_whichOne.getAsInt()).get(stopwatchCounter);

            //set postiion of intake
            m_intake.setPosition(intakePos);

            //get gear of gearboxes
            boolean gear = AutoSubsystemValues.gear.gear.get(m_whichOne.getAsInt()).get(stopwatchCounter);

            //set gear of gearboxes
            m_shifter.setGear(gear);
        }
    }
}