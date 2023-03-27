// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.IntakeOff;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.Second;
import frc.robot.commands.ZeroSlides;
import frc.robot.other.Stopwatch;
import frc.robot.subsystems.AutoSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LeftElevator;
import frc.robot.subsystems.RightElevator;
import frc.robot.commands.AutoCommand;
import frc.robot.subsystems.AutoSubsystemValues;

import frc.robot.subsystems.Shifter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  SendableChooser<Integer> autoChooser = new SendableChooser<>();

  private AutoCommand m_autoCommand;

  private RobotContainer m_robotContainer;

  public static final AutoSubsystem m_autoSubsystem = new AutoSubsystem();
  
  public static final Stopwatch timer = new Stopwatch();

  private final Intake m_intake = new Intake();
  private final Shifter m_shifter = new Shifter();

  private final LeftElevator m_leftElevator = new LeftElevator();
  private final RightElevator m_rightElevator = new RightElevator();

  private static Boolean autoEnabled = false;
  private static double testStartTime = 0;
  private static double testCurrentTime = 0;
  private static double testTimeDiff = 0;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    autoChooser.setDefaultOption("Nothing", 1);
    autoChooser.addOption("Driving", 2);
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    CameraServer.startAutomaticCapture();
    SmartDashboard.putData("Autonomous", autoChooser);

    new IntakeUp(m_intake);

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
   
      CommandScheduler.getInstance().run();
    

  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    new IntakeOff(m_intake);
    new Second(m_shifter);
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    
    int autoMode = autoChooser.getSelected();

    m_autoCommand = m_robotContainer.getAutonomousCommand();

    // timer.stop();
    // timer.reset();
    // timer.start();
    // autoEnabled = true;
    // Constants.pneumatics.intakeSolenoid.set(Constants.intake.intakeUp);

    m_autoCommand.setAuto(autoMode);

    // // schedule the autonomous command (example)
    if (m_autoCommand != null) {
    m_autoCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

    // if(Constants.pneumatics.shifterSolenoid.get() != Constants.drive.FIRST_GEAR){
    //   Constants.pneumatics.shifterSolenoid.set(Constants.drive.FIRST_GEAR);
    // }
    // if (timer.getTime() >= 1.0 && timer.getTime() <= 3.0) {
    //   m_intake.drive(0.7);
    // } else {
    //   m_intake.drive(0.0);
    // }
    // if (timer.getTime() >= 3.5 && timer.getTime() <= 6.5) {
    //   Constants.controllers.leftFrontSpark.set(-0.5);
    //   Constants.controllers.leftRearSpark.set(-0.5);
    //   Constants.controllers.rightFrontSpark.set(-0.5);
    //   Constants.controllers.rightRearSpark.set(-0.5);
    // } else {
    //   Constants.controllers.leftFrontSpark.set(-0.0);
    //   Constants.controllers.leftRearSpark.set(-0.0);
    //   Constants.controllers.rightFrontSpark.set(-0.0);
    //   Constants.controllers.rightRearSpark.set(-0.0);
    // }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    // if (m_autonomousCommand != null) {
    // m_autonomousCommand.cancel();
    // }
    autoEnabled = false;

    // zeroes out the slide position so that the position it is at when the
    // initialization of teleop occurs is set as the lowest possible position
    // this means the slide needs to be all the way down so the topmost limit is
    // also accurate
    new ZeroSlides(m_leftElevator, m_rightElevator);
    new Second(m_shifter);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    testStartTime = System.currentTimeMillis();

  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {

    testCurrentTime = System.currentTimeMillis();
    testTimeDiff = (testCurrentTime - testStartTime) / 1000;

    if (testTimeDiff >= 1.0 && testTimeDiff <= 2.0) {
      m_intake.drive(0.7);
    } else if (testTimeDiff >= 3.0 && testTimeDiff <= 6.0) {
      Constants.controllers.leftFrontSpark.set(0.5);
      Constants.controllers.leftRearSpark.set(0.5);
    } else if (testTimeDiff >= 7.0 && testTimeDiff <= 7.3) {
      System.out.println("Test Done");
    } else {
      Constants.controllers.leftFrontSpark.set(0.0);
      Constants.controllers.leftRearSpark.set(0.0);
      m_intake.drive(0.0);
      // m_drivetrain.drive(0.5, 0);
    }
  }
}
