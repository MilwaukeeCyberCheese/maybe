// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.other.Stopwatch;

/* actuates the pistons to pull the intake up */
public class IntakeUpThenCone extends CommandBase {
  private final Intake m_intake;
  private final Stopwatch timer = new Stopwatch();

    /**
   * Creates a new intake Command.
   *
   * @param intake subsystem controlling the intake
   * 
   */
  public IntakeUpThenCone(Intake intake) {
    m_intake = intake;
    addRequirements(m_intake);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    //sets position of intake to up
    m_intake.setPosition(Constants.intake.INTAKE_UP);
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    //waits for 0.3 seconds
    if(timer.getTime() > 300){
      //sets position of intake to cone
      m_intake.drive(Constants.intake.CONE_SPEED);
    }
  }

  @Override
  public void end(boolean interrupted){
    timer.stop();
    timer.reset();
    m_intake.drive(0.0);
  }
  
}
