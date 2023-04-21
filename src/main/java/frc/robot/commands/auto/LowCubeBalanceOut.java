package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.First;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class LowCubeBalanceOut extends SequentialCommandGroup {

    // constructor
    public LowCubeBalanceOut(Intake intake, Drivetrain drivetrain,
            ElevatorSubsystem elevatorSubsystem,
            Shifter shifter) {

        addCommands(
            new First(shifter),
                // new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> Constants.intake.CONE_SPEED, () -> 500, () -> 0, () -> 0),
                // new DriveToTime(() -> -0.6, () -> 0, () -> false, () -> false, () -> 3500, drivetrain),
                new SpinAround(() -> 180, drivetrain)
                // new AutoBalanceDrive(drivetrain, shifter, intake)

                );

    }

}
