package frc.robot.commands;

import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class AutoCommand extends SequentialCommandGroup {

    // constructor
    public AutoCommand(Intake intake, Drivetrain drivetrain,
            ElevatorSubsystem elevatorSubsystem,
            Shifter shifter, IntSupplier autoMode) {
        if (autoMode.getAsInt() == 1) {
            addCommands(
                    new Playback(intake, drivetrain, elevatorSubsystem, shifter, () -> 1));
        }
        if (autoMode.getAsInt() == 2) {
            addCommands(
                    Commands.race(new ConePlacePosition(elevatorSubsystem), new ProtectIntake(intake)),

                    new IntakeAuto(intake, Constants.intake.intakeUp, () -> Constants.intake.CUBE_SPEED, () -> 1500),

                    Commands.race(new CubeIntakePosition(elevatorSubsystem), new ProtectIntake(intake)),

                    new AutoBalanceDrive(drivetrain, shifter, intake));
        }
    }

}
