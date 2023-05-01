package frc.robot.commands.auto.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.auto.timeBased.DriveToTime;
import frc.robot.commands.auto.timeBased.IntakeAuto;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class LowCubeDriveOut extends SequentialCommandGroup {

        // constructor
        public LowCubeDriveOut(Intake intake, Drivetrain drivetrain,
                        ElevatorSubsystem elevatorSubsystem,
                        Shifter shifter) {

                addCommands(
                                new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> Constants.intake.CONE_SPEED,
                                                () -> 300, () -> 0, () -> 0, () -> 0.0),

                                new DriveToTime(() -> -0.5, () -> 0, () -> false, () -> false, () -> 3500, drivetrain));

        }

}
