package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.AutoBalanceDrive;
import frc.robot.commands.CubeIntakePosition;
import frc.robot.commands.CubePlacePosition;
import frc.robot.commands.ProtectIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class BalanceMiddleCube extends SequentialCommandGroup {

        // constructor
        public BalanceMiddleCube(Intake intake, Drivetrain drivetrain,
                        ElevatorSubsystem elevatorSubsystem,
                        Shifter shifter) {

                addCommands(
                                new IntakeAuto(intake, Constants.intake.INTAKE_DOWN, () -> 0, () -> 300, () -> 0,
                                                () -> 0),

                                Commands.parallel(new CubePlacePosition(elevatorSubsystem),
                                                new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> 0, () -> 500,
                                                                () -> 0, () -> 500)),

                                new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> Constants.intake.CONE_SPEED,
                                                () -> 500, () -> 0, () -> 0),

                                Commands.race(new CubeIntakePosition(elevatorSubsystem), new ProtectIntake(intake)),

                                new AutoBalanceDrive(drivetrain, shifter, intake));

        }

}
