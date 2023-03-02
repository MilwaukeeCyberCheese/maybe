package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    private double speed;
    private Value position;

    /**
     * Creates a new ExampleSubsystem.
     */
    public Intake() {
        Constants.controllers.intakeSpark.setInverted(Constants.intake.INVERTED);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        log();
    }

    public void drive(double speed) {
        this.speed = speed;
        Constants.controllers.intakeSpark.set(speed);
    }

    public void setPosition(Value position) {
        Constants.pneumatics.lIntakeSolenoid.set(position);
        Constants.pneumatics.rIntakeSolenoid.set(position);

    }

    public void log() {
        SmartDashboard.putNumber("IntakeSpeed", speed);
        SmartDashboard.putData(Constants.pneumatics.lIntakeSolenoid);
        SmartDashboard.putData(Constants.pneumatics.rIntakeSolenoid);
    }
}
