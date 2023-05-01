package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {

    private double speed;
    public Value position;

    /**
     * Creates a subsystem to control the intake
     */
    public Intake() {
        Constants.controllers.intakeSpark.setInverted(Constants.intake.INVERTED);
    }

    @Override
    public void periodic() {
        // adds position and speed to recording
        if (RobotContainer.readAuto) {
            RobotContainer.m_autoSubsystem.addIntaking(Constants.controllers.intakeSpark.get());
            RobotContainer.m_autoSubsystem.addIntakePos(Constants.pneumatics.intakeSolenoid.get());
        }

        log();

    }

    /**
     * Run intake at given speed
     *
     * @param speed Speed in range [-1,1]
     * 
     */
    public void drive(double speed) {

        Constants.controllers.intakeSpark.set(speed);
    }

    /**
     * Set position of the intake
     *
     * @param position
     * 
     */
    public void setPosition(Value position) {
        this.position = position;
        Constants.pneumatics.intakeSolenoid.set(position);

    }

    public double getCurrent() {
        return Constants.controllers.intakeSpark.getOutputCurrent();
    }

    public void log() {
        //log intake speed
        SmartDashboard.putNumber("IntakeSpeed", speed);
        //log intake current
        SmartDashboard.putNumber("IntakeCurrent", getCurrent());
    }

}
