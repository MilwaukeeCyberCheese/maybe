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

public class BalanceMiddleCone extends SequentialCommandGroup {

    // constructor
    public BalanceMiddleCone(Intake intake, Drivetrain drivetrain,
            ElevatorSubsystem elevatorSubsystem,
            Shifter shifter) {

       
            addCommands(
                    new IntakeDown(intake),

                    // new WaitCommand(0.5),

                    new ConePlacePosition(elevatorSubsystem), 

                    new IntakeAuto(intake, Constants.intake.INTAKE_UP, () -> Constants.intake.CUBE_SPEED, () -> 1500),

                    Commands.race(new CubeIntakePosition(elevatorSubsystem), new ProtectIntake(intake)),

            new AutoBalanceDriveOut(drivetrain, shifter, intake)
            );
      
    }

}
