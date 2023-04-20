// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.BalanceMiddleCone;
import frc.robot.commands.IntakeOff;
import frc.robot.commands.Second;
import frc.robot.commands.ZeroSlides;
import frc.robot.commands.LowCubeBalance;
import frc.robot.commands.PlaybackAuto;

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
  // creates sendable for choosing autonomous
  public static SendableChooser<SequentialCommandGroup> autoChooser = new SendableChooser<>();

  private SequentialCommandGroup m_autoCommand;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    //silence joystick error messages
   DriverStation.silenceJoystickConnectionWarning(true);
   
    // initialize auto options
    autoChooser.setDefaultOption("Zilch", null);
    autoChooser.addOption("Low Cube Balance", new LowCubeBalance(RobotContainer.m_intake, RobotContainer.m_drivetrain, RobotContainer.m_elevatorSubsystem, RobotContainer.m_shifter));
    autoChooser.addOption("Somethin Else", new PlaybackAuto(RobotContainer.m_intake, RobotContainer.m_drivetrain, RobotContainer.m_elevatorSubsystem, RobotContainer.m_shifter));
    autoChooser.addOption("Middle Cone and Balance", new BalanceMiddleCone(RobotContainer.m_intake, RobotContainer.m_drivetrain, RobotContainer.m_elevatorSubsystem, RobotContainer.m_shifter));

    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    // calibrate gyro
    Constants.balance.gyro.calibrate();

    // send camera video to dashboard
    CameraServer.startAutomaticCapture();

    // put auto picker on dashboard
    SmartDashboard.putData("Autonomous", autoChooser);

    // send command running to dashboard
    SmartDashboard.putData(CommandScheduler.getInstance());

    // zeroes out the slide position so that the position it is at when the
    // robot turns on is set as the lowest possible position
    // which means the slide needs to be all the way down
    new ZeroSlides(RobotContainer.m_elevatorSubsystem);

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * 
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
    new IntakeOff(RobotContainer.m_intake);
    new Second(RobotContainer.m_shifter);
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {

    m_autoCommand = m_robotContainer.getAutonomousCommand();

    SmartDashboard.putData("Autonomous Command", m_autoCommand);

    // schedule the autonomous command
    if (m_autoCommand != null) {
      m_autoCommand.schedule();
    }

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running.
    if (m_autoCommand != null) {
      m_autoCommand.cancel();
    }

    // put robot in second gear at the start of teleop
    new Second(RobotContainer.m_shifter);
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

  }
}