// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

import frc.robot.commands.First;
import frc.robot.commands.IntakeConeCommand;
import frc.robot.commands.IntakeCubeCommand;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.ProtectIntake;
import frc.robot.commands.RecordAuto;
import frc.robot.commands.Second;
import frc.robot.commands.ZeroSlides;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoBalanceDrive;
import frc.robot.commands.AutoBalancer;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.IntakeCubeSlow;
import frc.robot.commands.IntakeConeSlow;
import frc.robot.other.FilteredController;
import frc.robot.subsystems.AutoSubsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.RightElevator;
import frc.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Intake;
import frc.robot.commands.Elevator;
import frc.robot.subsystems.LeftElevator;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final Drivetrain m_drivetrain = new Drivetrain();
  private static final RightElevator m_rightElevator = new RightElevator();
  private static final LeftElevator m_leftElevator = new LeftElevator();
  static final Shifter m_shifter = new Shifter();
  public static final Intake m_intake = new Intake();

  private static final XboxController m_controller = new XboxController(0);
  public static final FilteredController m_filteredController = new FilteredController(m_controller);
  private static final XboxController m_controllerTwo = new XboxController(1);
  public static final FilteredController m_filteredControllerTwo = new FilteredController(m_controllerTwo);

  public static final AutoSubsystem m_autoSubsystem = new AutoSubsystem();
  private static final AutoCommand m_autoCommand = new AutoCommand(m_autoSubsystem, m_intake, m_drivetrain,
      m_leftElevator, m_rightElevator, m_shifter, () -> Robot.autoChooser.getSelected());

  public static boolean readAuto = false;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Put Some buttons on the SmartDashboard
    // SmartDashboard.putData("Elevator Bottom", new SetElevatorSetpoint(0,
    // m_elevator));
    // SmartDashboard.putData("Elevator Top", new SetElevatorSetpoint(0.25,
    // m_elevator));

    // SmartDashboard.putData("Wrist Horizontal", new SetWristSetpoint(0, m_wrist));
    // SmartDashboard.putData("Raise Wrist", new SetWristSetpoint(-45, m_wrist));

    // SmartDashboard.putData("Open Claw", new OpenClaw(m_claw));
    // SmartDashboard.putData("Close Claw", new CloseClaw(m_claw));

    // SmartDashboard.putData(
    // "Deliver Soda", new Autonomous(m_drivetrain, m_claw, m_wrist, m_elevator));

    // Assign default commands
    m_drivetrain.setDefaultCommand(
        new ArcadeDrive(
            () -> -m_filteredController.getYLeft(.2), () -> -m_filteredController
                .getXRight(.2),
            () -> m_filteredController.getLeftTriggerActive(0.2), () -> m_filteredController.getRightTriggerActive(0.2),
            () -> m_controller.getBackButton(),
            m_drivetrain));

    m_leftElevator.setDefaultCommand(
        new Elevator(() -> -m_filteredControllerTwo.getYRight(0.2), m_leftElevator,
            m_rightElevator, () -> !m_controllerTwo.getBackButton()));

    m_rightElevator.setDefaultCommand(
        new Elevator(() -> -m_filteredControllerTwo.getYRight(0.2), m_leftElevator,
            m_rightElevator, () -> !m_controllerTwo.getBackButton()));

    m_intake.setDefaultCommand(new ProtectIntake(m_intake));

    m_autoSubsystem.setDefaultCommand(
        new RecordAuto(m_autoSubsystem, () -> m_controllerTwo.getXButton(), () -> m_controllerTwo.getBButton()));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public AutoCommand getAutonomousCommand() {
    return m_autoCommand;
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Create some buttons
    Trigger leftBumperOne = new JoystickButton(m_controller, 5);
    Trigger rightBumperOne = new JoystickButton(m_controller, 6);

    Trigger aButtonTwo = new JoystickButton(m_controllerTwo, 1);
    Trigger yButtonTwo = new JoystickButton(m_controllerTwo, 4);
    Trigger leftBumperTwo = new JoystickButton(m_controllerTwo, 5);
    Trigger rightBumperTwo = new JoystickButton(m_controllerTwo, 6);
    Trigger startButtonTwo = new JoystickButton(m_controllerTwo, 8);

    Trigger rightTriggerOne = new Trigger(() -> m_filteredController.getRightTriggerActive());

    Trigger leftTriggerTwo = new Trigger(() -> m_filteredControllerTwo.getLeftTriggerActive());
    Trigger rightTriggerTwo = new Trigger(() -> m_filteredControllerTwo.getRightTriggerActive());

    Trigger leftStickButtonOne = new JoystickButton(m_controller, 9);
    Trigger rightStickButtonOne = new JoystickButton(m_controller, 10);


    

    leftBumperOne.onTrue(new First(m_shifter));
    rightBumperOne.onTrue(new Second(m_shifter));

    rightTriggerOne.onTrue(new IntakeUp(m_intake));

    startButtonTwo.whileTrue(new ZeroSlides(m_leftElevator, m_rightElevator));

    leftStickButtonOne.onTrue(new AutoBalancer(m_drivetrain, m_shifter));
    rightStickButtonOne.onTrue(new AutoBalanceDrive(m_drivetrain, m_shifter));

    yButtonTwo.whileTrue(new IntakeUp(m_intake));
    aButtonTwo.whileTrue(new IntakeDown(m_intake));

    leftBumperTwo.whileTrue(new IntakeCubeCommand(m_intake));
    rightBumperTwo.whileTrue(new IntakeConeCommand(m_intake));

    leftTriggerTwo.whileTrue(new IntakeCubeSlow(m_intake));
    rightTriggerTwo.whileTrue(new IntakeConeSlow(m_intake));

  }

}
