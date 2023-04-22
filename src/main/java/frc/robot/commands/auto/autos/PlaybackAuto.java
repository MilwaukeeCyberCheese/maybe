package frc.robot.commands.auto.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.auto.timeBased.Playback;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class PlaybackAuto extends SequentialCommandGroup {

    // constructor
    public PlaybackAuto(Intake intake, Drivetrain drivetrain,
            ElevatorSubsystem elevatorSubsystem,
            Shifter shifter) {

        addCommands(
                new Playback(intake, drivetrain, elevatorSubsystem, shifter, () -> Constants.autos.DRIVE_OUT));
    }

}
