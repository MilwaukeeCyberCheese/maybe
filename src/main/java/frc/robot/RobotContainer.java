// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

import frc.robot.commands.First;
import frc.robot.commands.Second;
import frc.robot.commands.balancing.AutoBalanceDrive;
import frc.robot.commands.balancing.AutoBalancer;
import frc.robot.commands.elevator.ConeIntakePosition;
import frc.robot.commands.elevator.ConePlacePosition;
import frc.robot.commands.elevator.CubeIntakePosition;
import frc.robot.commands.elevator.CubePlacePosition;
import frc.robot.commands.elevator.ElevatorPID;
import frc.robot.commands.elevator.MidConeScore;
import frc.robot.commands.elevator.MidCubeScore;
import frc.robot.commands.elevator.ZeroSlides;
import frc.robot.commands.intake.IntakeConeCommand;
import frc.robot.commands.intake.IntakeConeUp;
import frc.robot.commands.intake.IntakeCubeCommand;
import frc.robot.commands.intake.IntakeCubeDown;
import frc.robot.commands.intake.IntakeDown;
import frc.robot.commands.intake.IntakeUp;
import frc.robot.commands.intake.ProtectIntake;
import frc.robot.commands.ArcadeDrive;
import frc.robot.other.FilteredController;
import frc.robot.subsystems.AutoSubsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Shifter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Intake;

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
  public static final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
  public static final Intake m_intake = new Intake();
  public static final Shifter m_shifter = new Shifter();

  private static final XboxController m_controller = new XboxController(0);
  public static final FilteredController m_filteredController = new FilteredController(m_controller);
  private static final XboxController m_controllerTwo = new XboxController(1);
  public static final FilteredController m_filteredControllerTwo = new FilteredController(m_controllerTwo);

  public static final AutoSubsystem m_autoSubsystem = new AutoSubsystem();
  public static boolean readAuto = false;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Assign default commands
    m_drivetrain.setDefaultCommand(
        new ArcadeDrive(
            () -> -m_filteredController.getYLeft(.2), () -> -m_filteredController
                .getXRight(.2),
            () -> m_filteredController.getLeftTriggerActive(0.2), () -> m_filteredController.getRightTriggerActive(0.2),
            m_drivetrain));

    m_elevatorSubsystem.setDefaultCommand(
        new ElevatorPID(() -> -m_filteredControllerTwo.getYLeft(0.2), m_elevatorSubsystem,
            () -> !m_controllerTwo.getStartButton()));

    m_intake.setDefaultCommand(new ProtectIntake(m_intake));

    // m_autoSubsystem.setDefaultCommand(
    // new RecordAuto(m_autoSubsystem, () -> m_controllerTwo.getXButton(), () ->
    // m_controllerTwo.getBButton()));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public SequentialCommandGroup getAutonomousCommand() {
    return Robot.autoChooser.getSelected();
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
    // Create the triggers
    Trigger leftBumperOne = new JoystickButton(m_controller, 5);
    Trigger rightBumperOne = new JoystickButton(m_controller, 6);

    Trigger aButtonTwo = new JoystickButton(m_controllerTwo, 1);
    Trigger bButtonTwo = new JoystickButton(m_controllerTwo, 2);
    Trigger xButtonTwo = new JoystickButton(m_controllerTwo, 3);
    Trigger yButtonTwo = new JoystickButton(m_controllerTwo, 4);
    Trigger leftBumperTwo = new JoystickButton(m_controllerTwo, 5);
    Trigger rightBumperTwo = new JoystickButton(m_controllerTwo, 6);
    Trigger backButtonTwo = new JoystickButton(m_controllerTwo, 7);

    Trigger leftTriggerTwo = new Trigger(() -> m_filteredControllerTwo.getLeftTriggerActive());
    Trigger rightTriggerTwo = new Trigger(() -> m_filteredControllerTwo.getRightTriggerActive());

    Trigger dpadUpTwo = new Trigger(() -> m_filteredControllerTwo.getPOVButton() == 8);
    Trigger dpadDownTwo = new Trigger(() -> m_filteredControllerTwo.getPOVButton() == 2);
    Trigger dpadLeftTwo = new Trigger(() -> m_filteredControllerTwo.getPOVButton() == 6);
    Trigger dpadRightTwo = new Trigger(() -> m_filteredControllerTwo.getPOVButton() == 4);

    Trigger leftStickButtonOne = new JoystickButton(m_controller, 9);
    Trigger rightStickButtonOne = new JoystickButton(m_controller, 10);

    // map the trigers to commands
    leftBumperOne.onTrue(new First(m_shifter));
    rightBumperOne.onTrue(new Second(m_shifter));

    backButtonTwo.whileTrue(new ZeroSlides(m_elevatorSubsystem));

    leftStickButtonOne.onTrue(new AutoBalancer(m_drivetrain, m_shifter));
    rightStickButtonOne.onTrue(new AutoBalanceDrive(m_drivetrain, m_shifter, m_intake));

    yButtonTwo.whileTrue(new IntakeUp(m_intake));
    aButtonTwo.whileTrue(new IntakeDown(m_intake));

    leftBumperTwo.whileTrue(new IntakeCubeCommand(m_intake));
    rightBumperTwo.whileTrue(new IntakeConeCommand(m_intake));

    leftTriggerTwo.whileTrue(new IntakeCubeDown(m_intake));
    rightTriggerTwo.whileTrue(new IntakeConeUp(m_intake));

    xButtonTwo.onTrue(new MidCubeScore(m_intake, m_elevatorSubsystem));
    bButtonTwo.onTrue(new MidConeScore(m_intake, m_elevatorSubsystem));

    dpadUpTwo.onTrue(new ConePlacePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));
    dpadDownTwo.onTrue(new ConeIntakePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));
    dpadLeftTwo.onTrue(new CubePlacePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));
    dpadRightTwo.onTrue(new CubeIntakePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));

  }

}