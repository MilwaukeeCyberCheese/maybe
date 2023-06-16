package frc.robot.commands.auto.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Constants;
import frc.robot.commands.First;
import frc.robot.commands.auto.timeBased.DriveToTime;
import frc.robot.commands.auto.timeBased.IntakeAuto;
import frc.robot.commands.balancing.AutoBalanceDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class LowCubeBalanceDriveOutII extends SequentialCommandGroup {

    // constructor
    public LowCubeBalanceDriveOutII(Intake intake, Drivetrain drivetrain,
            ElevatorSubsystem elevatorSubsystem,
            Shifter shifter) {

        addCommands(
                new First(shifter),
                new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> Constants.intake.CONE_SPEED, () -> 500,
                        () -> 0, () -> 0),
                new AutoBalanceDriveOut(drivetrain, shifter, intake),
                new DriveToTime(() -> -0.4, () -> 0, () -> false, () -> false, () -> 1000, drivetrain),
                new DriveToTime(() -> 0, () -> 0.5, () -> false, () -> false, () -> 2800, drivetrain),
                new AutoBalanceDrive(drivetrain, shifter, intake));

    }

}
