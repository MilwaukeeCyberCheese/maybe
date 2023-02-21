package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
 
private double speed;
    /**
     * Creates a new ExampleSubsystem.
     */
    public Intake() {
        Constants.controllers.intakeSpark.setInverted(Constants.intake.INVERTED);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }




    
    public void drive(double speed) {
        this.speed = speed;
        Constants.controllers.intakeSpark.set(speed);
    }
}
