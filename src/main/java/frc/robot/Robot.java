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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.IntakeOff;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.Second;
import frc.robot.commands.ZeroSlides;
import frc.robot.other.Stopwatch;
import frc.robot.subsystems.AutoSubsystem;
import frc.robot.subsystems.AutoSubsystemValues;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LeftElevator;
import frc.robot.subsystems.RightElevator;
import frc.robot.commands.AutoCommand;

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

  private int stopwatchCounter = -1;
  private AutoCommand m_autoCommand;
  private int autoMode = 3;

  private RobotContainer m_robotContainer;

  public static final AutoSubsystem m_autoSubsystem = new AutoSubsystem();

  public static final Stopwatch timer = new Stopwatch();

  private final Intake m_intake = new Intake();
  private final Shifter m_shifter = new Shifter();

  private final LeftElevator m_leftElevator = new LeftElevator();
  private final RightElevator m_rightElevator = new RightElevator();

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    autoChooser.setDefaultOption("Drive", 1);
    autoChooser.addOption("Balance", 2);
    autoChooser.addOption("Nothing", 3);
    autoChooser.addOption("True Auto", 4);
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    // CameraServer.startAutomaticCapture();
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

    autoMode = autoChooser.getSelected();

    

    // timer.stop();
    // timer.reset();
    // timer.start();
    // Constants.pneumatics.intakeSolenoid.set(Constants.intake.intakeUp);

    

    // // schedule the autonomous command (example)
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if (autoMode == 1) {
      if (DriverStation.isAutonomousEnabled()
              && stopwatchCounter < (AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.size() - 2)) {
          stopwatchCounter++;
System.out.println(stopwatchCounter);
          // gear
          Boolean gear = AutoSubsystemValues.gear.gear.get(stopwatchCounter);
          Constants.pneumatics.shifterSolenoid.set(gear);
          // actuate lift
          double leftLiftSpeed = AutoSubsystemValues.leftLiftSpeeds.leftLiftSpeeds.get(stopwatchCounter);
          double rightLiftSpeed = AutoSubsystemValues.rightLiftSpeeds.rightLiftSpeeds.get(stopwatchCounter);

          Constants.controllers.leftLiftSpark.set(leftLiftSpeed);
          Constants.controllers.rightLiftSpark.set(rightLiftSpeed);
          // System.out.println(AutoSubsystemValues.intaking.intaking.size());
          // System.out.println(stopwatchCounter);
          // intake
          double intake = AutoSubsystemValues.intaking.intaking.get(stopwatchCounter);

          // if (intake != prevState) {
          // System.out.println("Switching State");
          // System.out.println(intake);
          // System.out.println(stopwatchCounter);
          // Constants.controllers.intakeSpark.set(intake);
          // prevState = intake;
          // }
          Constants.controllers.intakeSpark.set(intake);
          // System.out.println(intake);

          // // intake pos
          Value intakePos = AutoSubsystemValues.intakePos.intakePos.get(stopwatchCounter);
          // System.out.println(intakePos);
          Constants.pneumatics.intakeSolenoid.set(intakePos);

          // if(stopwatchCounter >= 175){
          // ended = false;
          // }
          // get speeds for wheels
          double frontLeft = AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(stopwatchCounter);
          double frontRight = AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(stopwatchCounter);
          double backLeft = AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(stopwatchCounter);
          double backRight = AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(stopwatchCounter);

          // System.out.println(frontLeft);
          // // set wheel speeds
          Constants.controllers.leftFrontSpark.set(frontLeft);
          Constants.controllers.rightFrontSpark.set(frontRight);
          Constants.controllers.leftRearSpark.set(backLeft);
          Constants.controllers.rightRearSpark.set(backRight);
      } else if (DriverStation.isAutonomousEnabled()
              && stopwatchCounter >= AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.size() - 1) {
          // if we run out of code to run in auto, make sure everything is not moving
          // Constants.controllers.leftLiftSpark.set(0.0);
          // Constants.controllers.rightLiftSpark.set(0.0);
          // Constants.controllers.intakeSpark.set(0.0);
          // // System.out.println("Stopped Robot");
          // Constants.controllers.leftFrontSpark.set(0.0);
          // Constants.controllers.leftRearSpark.set(0.0);
          // Constants.controllers.rightFrontSpark.set(0.0);
          // Constants.controllers.rightRearSpark.set(0.0);
              }
   }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autoCommand != null) {
      m_autoCommand.cancel();
    }

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

  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {

  }
}