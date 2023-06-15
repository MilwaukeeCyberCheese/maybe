// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.commands.First;
import frc.robot.commands.Second;
import frc.robot.commands.balancing.AutoBalanceDrive;
import frc.robot.commands.balancing.AutoBalancer;
import frc.robot.commands.elevator.ConeIntakePosition;
import frc.robot.commands.elevator.ConeIntakeSingle;
import frc.robot.commands.elevator.ConePlacePosition;
import frc.robot.commands.elevator.CubeIntakePosition;
import frc.robot.commands.elevator.CubePlacePosition;
import frc.robot.commands.elevator.ElevatorPID;
import frc.robot.commands.elevator.MidConeScore;
import frc.robot.commands.elevator.MidCubeScore;
import frc.robot.commands.elevator.ZeroSlides;
import frc.robot.commands.intake.IntakeConeCommand;
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
  private static final Joystick m_buttons = new Joystick(2);

  public static final AutoSubsystem m_autoSubsystem = new AutoSubsystem();
  public static boolean readAuto = false;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Assign default commands
    m_drivetrain.setDefaultCommand(
        new ArcadeDrive(
            () -> m_filteredController.getXRight(), () -> m_filteredController
                .getYLeft(.2),
            () -> m_filteredController.getLeftTriggerActive(0.2), () -> m_filteredController.getRightTriggerActive(0.2),
            m_drivetrain));
    if (!Constants.SINGLE_DRIVER) {
      m_elevatorSubsystem.setDefaultCommand(
          new ElevatorPID(() -> -m_filteredControllerTwo.getYLeft(0.2), m_elevatorSubsystem,
              () -> !m_controllerTwo.getStartButton()));
    }

    m_intake.setDefaultCommand(new ProtectIntake(m_intake));

    // Configure the button bindings
    if (Constants.SINGLE_DRIVER) {
      configureButtonBindingsOneDriver();
    } else {
      configureButtonBindingsTwoDrivers();
    }
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
  private void configureButtonBindingsTwoDrivers() {
    // Create the triggers
    new Trigger(m_filteredController::getLeftBumper).onTrue(new First(m_shifter));
    new Trigger(m_filteredController::getRightBumper).onTrue(new Second(m_shifter));

    new Trigger(m_filteredControllerTwo::getAButton).onTrue(new IntakeDown(m_intake));
    new Trigger(m_filteredControllerTwo::getYButton).onTrue(new IntakeUp(m_intake));

    new Trigger(m_filteredControllerTwo::getLeftBumper).whileTrue(new IntakeCubeCommand(m_intake));
    new Trigger(m_filteredControllerTwo::getRightBumper).whileTrue(new IntakeConeCommand(m_intake));
    new Trigger(m_filteredControllerTwo::getLeftTriggerActive).whileTrue(new IntakeCubeDown(m_intake));

    new Trigger(() -> m_buttons.getRawButton(1).whileTrue(new IntakeCubeDown(m_intake));
    new Trigger(() -> m_buttons.getRawButton(2)).onTrue(new ZeroSlides(m_elevatorSubsystem));
    new Trigger(() -> m_buttons.getRawButton(3)).whileTrue(new IntakeConeCommand(m_intake));
    new Trigger(() -> m_buttons.getRawButton(4)).whileTrue(new ElevatorPID(() -> 1.0, m_elevatorSubsystem, () -> true));
    new Trigger(() -> m_buttons.getRawButton(5)).onTrue(new AutoBalancer(m_drivetrain, m_shifter));
    new Trigger(() -> m_buttons.getRawButton(6)).whileTrue(new ElevatorPID(() -> -1.0, m_elevatorSubsystem, () -> true));
    new Trigger(() -> m_buttons.getRawButton(7)).onTrue(new MidConeScore(m_intake, m_elevatorSubsystem));
    new Trigger(() -> m_buttons.getRawButton(9)).onTrue(new MidCubeScore(m_intake, m_elevatorSubsystem));

    new Trigger(m_filteredControllerTwo::getBackButton).onTrue(new ZeroSlides(m_elevatorSubsystem));

    new Trigger(() -> m_filteredControllerTwo.getPOVButton() == 8)
        .onTrue(new ConePlacePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));
    new Trigger(() -> m_filteredControllerTwo.getPOVButton() == 2)
        .onTrue(new ConeIntakePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));
    new Trigger(() -> m_filteredControllerTwo.getPOVButton() == 6)
        .onTrue(new CubePlacePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));
    new Trigger(() -> m_filteredControllerTwo.getPOVButton() == 4)
        .onTrue(new CubeIntakePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));

    // new Trigger(m_filteredController::getLeftStickPressed).onTrue(new
    // AutoBalancer(m_drivetrain, m_shifter));
    // new Trigger(m_filteredController::getRightStickPressed)
    // .onTrue(new AutoBalanceDrive(m_drivetrain, m_shifter, m_intake));

    // map the trigers to commands

  }

  private void configureButtonBindingsOneDriver() {
    // Create the triggers
    Trigger leftBumperOne = new JoystickButton(m_controller, 5);
    Trigger rightBumperOne = new JoystickButton(m_controller, 6);

    Trigger aButtonOne = new JoystickButton(m_controller, 1);
    Trigger bButtonOne = new JoystickButton(m_controller, 2);
    Trigger xButtonOne = new JoystickButton(m_controller, 3);
    Trigger yButtonOne = new JoystickButton(m_controller, 4);

    Trigger backButtonOne = new JoystickButton(m_controller, 7);

    Trigger leftStickButtonOne = new JoystickButton(m_controller, 9);
    Trigger rightStickButtonOne = new JoystickButton(m_controller, 10);

    Trigger dpadUpOne = new Trigger(() -> m_filteredController.getPOVButton() == 8);
    Trigger dpadDownOne = new Trigger(() -> m_filteredController.getPOVButton() == 2);
    Trigger dpadLeftOne = new Trigger(() -> m_filteredController.getPOVButton() == 6);
    Trigger dpadRightOne = new Trigger(() -> m_filteredController.getPOVButton() == 4);

    // map the trigers to commands
    leftBumperOne.onTrue(new First(m_shifter));
    rightBumperOne.onTrue(new Second(m_shifter));

    backButtonOne.whileTrue(new ZeroSlides(m_elevatorSubsystem));

    leftStickButtonOne.onTrue(new AutoBalancer(m_drivetrain, m_shifter));
    rightStickButtonOne.onTrue(new AutoBalanceDrive(m_drivetrain, m_shifter, m_intake));

    yButtonOne.onTrue(new IntakeUp(m_intake));
    aButtonOne.onTrue(new IntakeDown(m_intake));

    bButtonOne.whileTrue(new IntakeCubeCommand(m_intake));
    xButtonOne.whileTrue(new IntakeConeCommand(m_intake));

    dpadUpOne.onTrue(new ConePlacePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));
    dpadDownOne.onTrue(new ConeIntakePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));
    dpadLeftOne.onTrue(new CubePlacePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));
    dpadRightOne.onTrue(new CubeIntakePosition(m_elevatorSubsystem, () -> Constants.intake.INTAKE_DELAY));

  }

}