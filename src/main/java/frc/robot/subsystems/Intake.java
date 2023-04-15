package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.other.Stopwatch;

public class Intake extends SubsystemBase {

    private double speed;
    public Value position;
    private Stopwatch timer = new Stopwatch();

    /**
     * Creates a new ExampleSubsystem.
     */
    public Intake() {
        Constants.controllers.intakeSpark.setInverted(Constants.intake.INVERTED);
        Constants.controllers.intakeSpark.setSmartCurrentLimit(Constants.intake.STALL_LIMIT, Constants.intake.FREE_LIMIT);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if (RobotContainer.readAuto) {
            RobotContainer.m_autoSubsystem.addIntaking(Constants.controllers.intakeSpark.get());
            RobotContainer.m_autoSubsystem.addIntakePos(Constants.pneumatics.intakeSolenoid.get());
        }
        log();
        position = Constants.pneumatics.intakeSolenoid.get();
        
        
    }

    public void drive(double speed) {
    
        Constants.controllers.intakeSpark.set(speed);
    }

    public void setPosition(Value position) {
        Constants.pneumatics.intakeSolenoid.set(position);

    }

    public Value getPosition(){
        return Constants.pneumatics.intakeSolenoid.get();
    }

    public void log() {
        SmartDashboard.putNumber("IntakeSpeed", speed);
    }

}
