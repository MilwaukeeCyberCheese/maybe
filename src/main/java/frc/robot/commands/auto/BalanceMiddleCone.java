package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.AutoBalanceDriveOut;
import frc.robot.commands.ConePlacePosition;
import frc.robot.commands.CubeIntakePosition;
import frc.robot.commands.IntakeAuto;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.ProtectIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class BalanceMiddleCone extends SequentialCommandGroup {

        // constructor
        public BalanceMiddleCone(Intake intake, Drivetrain drivetrain,
                        ElevatorSubsystem elevatorSubsystem,
                        Shifter shifter) {

                addCommands(
                                new IntakeDown(intake),

                                new WaitCommand(0.5),

                                new ConePlacePosition(elevatorSubsystem),

                                new IntakeUp(intake),

                                new WaitCommand(0.3),

                                new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> Constants.intake.CUBE_SPEED,
                                                () -> 500),

                                Commands.race(new CubeIntakePosition(elevatorSubsystem), new ProtectIntake(intake)),

                                new AutoBalanceDriveOut(drivetrain, shifter, intake));

        }

}
