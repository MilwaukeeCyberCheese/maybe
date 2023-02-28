// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.Constants.intake;
import frc.robot.commands.Autonomous;
import frc.robot.commands.First;
import frc.robot.commands.IntakeConeCommand;
import frc.robot.commands.IntakeCubeCommand;
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

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final RightElevator m_elevator = new RightElevator();
  private final Shifter m_shifter = new Shifter();
  private final Intake m_intake = new Intake();
  


  private static final XboxController m_controller = new XboxController(0);
  public static final FilteredController m_filteredController = new FilteredController(m_controller);

  private final CommandBase m_autonomousCommand =
      new Autonomous(m_drivetrain, m_elevator);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Put Some buttons on the SmartDashboard
    // SmartDashboard.putData("Elevator Bottom", new SetElevatorSetpoint(0, m_elevator));
    // SmartDashboard.putData("Elevator Top", new SetElevatorSetpoint(0.25, m_elevator));

    // SmartDashboard.putData("Wrist Horizontal", new SetWristSetpoint(0, m_wrist));
    // SmartDashboard.putData("Raise Wrist", new SetWristSetpoint(-45, m_wrist));

    // SmartDashboard.putData("Open Claw", new OpenClaw(m_claw));
    // SmartDashboard.putData("Close Claw", new CloseClaw(m_claw));

    // SmartDashboard.putData(
    //     "Deliver Soda", new Autonomous(m_drivetrain, m_claw, m_wrist, m_elevator));

    // Assign default commands
    m_drivetrain.setDefaultCommand(
        new ArcadeDrive(() -> -m_filteredController.getYLeft(.2), () -> -m_filteredController.getXRight(.2), m_drivetrain));

    // // Show what command your subsystem is running on the SmartDashboard
    // SmartDashboard.putData(m_drivetrain);
    // SmartDashboard.putData(m_elevator);
    // SmartDashboard.putData(m_wrist);
    // SmartDashboard.putData(m_claw);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Create some buttons
    
Trigger rightBumper = new JoystickButton(m_controller, 6);
Trigger leftBumper = new JoystickButton(m_controller, 5);

leftBumper.whileTrue(new IntakeConeCommand(m_intake));
rightBumper.whileTrue(new IntakeCubeCommand(m_intake));

    // Connect the buttons to commands
    // dpadUp.onTrue(new SetElevatorSetpoint(0.25, m_elevator));
    // dpadDown.onTrue(new SetElevatorSetpoint(0.0, m_elevator));
    // dpadRight.onTrue(new CloseClaw(m_claw));
    // dpadLeft.onTrue(new OpenClaw(m_claw));

    // r1.onTrue(new PrepareToPickup(m_claw, m_wrist, m_elevator));
    // r2.onTrue(new Pickup(m_claw, m_wrist, m_elevator));
    // l1.onTrue(new Place(m_claw, m_wrist, m_elevator));
    // l2.onTrue(new Autonomous(m_drivetrain, m_claw, m_wrist, m_elevator));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autonomousCommand;
  }
}
