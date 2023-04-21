package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.LiftPos.CubeIntakePosition;
import frc.robot.commands.LiftPos.CubePlacePosition;
import frc.robot.commands.auto.DriveToTime;
import frc.robot.commands.auto.IntakeAuto;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class MidCubeScore extends SequentialCommandGroup {

    // constructor
    public MidCubeScore(Intake intake,
            ElevatorSubsystem elevatorSubsystem) {

        addCommands(
                new IntakeAuto(intake, Constants.intake.INTAKE_DOWN, () -> 0, () -> 300, () -> 0,
                        () -> 0),

                Commands.parallel(new CubePlacePosition(elevatorSubsystem, () -> 0),
                        new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> 0, () -> 500,
                                () -> 0, () -> 500)),

                new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> Constants.intake.CONE_SPEED,
                        () -> 500, () -> 0, () -> 0),

                Commands.parallel(new CubeIntakePosition(elevatorSubsystem, () -> 0),
                        new IntakeAuto(intake, Constants.intake.INTAKE_DOWN, () -> 0, () -> 500, () -> 0, () -> 300)),

                        new IntakeUp(intake)

                

        );

    }

}
