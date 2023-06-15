package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.auto.timeBased.IntakeAuto;
import frc.robot.commands.intake.IntakeUp;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;

public class ConeIntakeSingle extends SequentialCommandGroup {

    // constructor
    public ConeIntakeSingle(Intake intake,
            ElevatorSubsystem elevatorSubsystem) {

        addCommands(
                new IntakeAuto(intake, Constants.intake.INTAKE_DOWN, () -> 0, () -> 300, () -> 0,
                        () -> 0, () -> 0.0),

                Commands.parallel(new ConeIntakePosition(elevatorSubsystem, () -> 0),
                        new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> 0, () -> 500,
                                () -> 0, () -> 500, () -> 0.0)),

                new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> Constants.intake.CONE_SPEED,
                        () -> 2000, () -> 0, () -> 0, () -> Constants.intake.STALL_LIMIT_CONE),

                Commands.parallel(new CubeIntakePosition(elevatorSubsystem, () -> 0),
                        new IntakeAuto(intake, Constants.intake.INTAKE_DOWN, () -> 0, () -> 500, () -> 0, () -> 300, () -> 0.0))

                

        );

    }

}
