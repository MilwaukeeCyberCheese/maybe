package frc.robot.commands.auto.autos;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.auto.timeBased.DriveToTime;
import frc.robot.commands.auto.timeBased.IntakeAuto;
import frc.robot.commands.elevator.CubeIntakePosition;
import frc.robot.commands.elevator.CubePlacePosition;
import frc.robot.commands.intake.IntakeUp;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class MidCubeDriveOut extends SequentialCommandGroup {

    // constructor
    public MidCubeDriveOut(Intake intake, Drivetrain drivetrain,
            ElevatorSubsystem elevatorSubsystem,
            Shifter shifter) {

        addCommands(
                new IntakeAuto(intake, Constants.intake.INTAKE_DOWN, () -> 0, () -> 300, () -> 0,
                        () -> 0),

                Commands.parallel(new CubePlacePosition(elevatorSubsystem, () -> 0),
                        new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> 0, () -> 500,
                                () -> 0, () -> 400)),

                new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> Constants.intake.CONE_SPEED,
                        () -> 500, () -> 0, () -> 0),

                Commands.parallel(new CubeIntakePosition(elevatorSubsystem, () -> 0),
                        new IntakeAuto(intake, Constants.intake.INTAKE_DOWN, () -> 0, () -> 500, () -> 0, () -> 300)),

                        new IntakeUp(intake),

                new DriveToTime(() -> 0.5, () -> 0, () -> false, () -> false, () -> 3500, drivetrain)

        );

    }

}
