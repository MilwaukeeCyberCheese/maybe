// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.commands.Autonomous;
import frc.robot.commands.First;
import frc.robot.commands.IntakeConeCommand;
import frc.robot.commands.IntakeCubeCommand;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.Second;
import frc.robot.commands.ArcadeDrive;
import frc.robot.other.FilteredController;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.RightElevator;
import frc.robot.subsystems.Shifter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Intake;
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
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final RightElevator m_rightElevator = new RightElevator();
  private final LeftElevator m_leftElevator = new LeftElevator();
  private final Shifter m_shifter = new Shifter();
  private final Intake m_intake = new Intake();

  private static final XboxController m_controller = new XboxController(0);
  public static final FilteredController m_filteredController = new FilteredController(m_controller);


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
        new ArcadeDrive(() -> -m_filteredController.getYLeft(.2), () -> -m_filteredController.getXRight(.2),
            m_drivetrain));

    // Configure the button bindings
    configureButtonBindings();
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
    Trigger aButton = new JoystickButton(m_controller, 1);
    Trigger bButton = new JoystickButton(m_controller, 2);
    Trigger xButton = new JoystickButton(m_controller, 3);
    Trigger yButton = new JoystickButton(m_controller, 4);
    Trigger leftBumper = new JoystickButton(m_controller, 5);
    Trigger rightBumper = new JoystickButton(m_controller, 6);

    leftBumper.whileTrue(new IntakeConeCommand(m_intake));
    rightBumper.whileTrue(new IntakeCubeCommand(m_intake));

    aButton.onTrue(new First(m_shifter));
    bButton.onTrue(new Second(m_shifter));
    xButton.onTrue(new IntakeDown(m_intake));
    yButton.onTrue(new IntakeUp(m_intake));

  }

 
}
