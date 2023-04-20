package frc.robot.commands;

import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class LowCubeBalance extends SequentialCommandGroup {

    // constructor
    public LowCubeBalance(Intake intake, Drivetrain drivetrain,
            ElevatorSubsystem elevatorSubsystem,
            Shifter shifter) {

        addCommands(
                new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> Constants.intake.CONE_SPEED, () -> 500),
                new AutoBalanceDrive(drivetrain, shifter, intake));

    }

}
