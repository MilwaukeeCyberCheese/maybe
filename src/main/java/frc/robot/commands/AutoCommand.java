package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.other.Stopwatch;
import frc.robot.subsystems.AutoSubsystem;
import frc.robot.subsystems.AutoSubsystemValues;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class AutoCommand extends CommandBase {
    private final AutoSubsystem m_autoSubsystem;

    // constructor
    public AutoCommand(AutoSubsystem autoSubsystem) {
        this.m_autoSubsystem = autoSubsystem;
    }

 public void setAuto(int auto){
    m_autoSubsystem.setAuto(auto);
 }

    @Override
    public void execute() {

       

    }

    /**
     * This function ensures when the action is complete, the robot stops moving
     * 
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {

    }
}
