package frc.robot.commands;

import java.util.function.IntSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.other.Stopwatch;
import frc.robot.subsystems.AutoSubsystemValues;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shifter;

public class AutoCommand extends SequentialCommandGroup {

    // constructor
    public AutoCommand(Intake intake, Drivetrain drivetrain,
            ElevatorSubsystem elevatorSubsystem,
            Shifter shifter, IntSupplier autoMode) {
                
        if (autoMode.getAsInt() == 2) {
            addCommands(
                    new ConePlacePosition(elevatorSubsystem),

                    new IntakeAuto(intake, Constants.intake.intakeUp, () -> Constants.intake.CUBE_SPEED, () -> 1500),

                    new CubeIntakePosition(elevatorSubsystem),

                    new AutoBalanceDrive(drivetrain, shifter, intake));
        }
    }

}
