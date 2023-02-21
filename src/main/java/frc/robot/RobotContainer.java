// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.commands.Autonomous;
import frc.robot.commands.SetElevatorSetpoint;
import frc.robot.commands.First;
import frc.robot.commands.Second;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.RightElevator;
import frc.robot.subsystems.Shifter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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
  


  private final XboxController m_joystick = new XboxController(0);

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
        new TankDrive(() -> -m_joystick.getLeftY(), () -> -m_joystick.getRightY(), m_drivetrain));

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
    final JoystickButton a = new JoystickButton(m_joystick, 1);
    final JoystickButton b = new JoystickButton(m_joystick, 2);
    final JoystickButton x = new JoystickButton(m_joystick, 3);
    final JoystickButton y = new JoystickButton(m_joystick, 4);
    final JoystickButton dpadUp = new JoystickButton(m_joystick, 5);
    final JoystickButton dpadRight = new JoystickButton(m_joystick, 6);
    final JoystickButton dpadDown = new JoystickButton(m_joystick, 7);
    final JoystickButton dpadLeft = new JoystickButton(m_joystick, 8);
    final JoystickButton l2 = new JoystickButton(m_joystick, 9);
    final JoystickButton r2 = new JoystickButton(m_joystick, 10);
    final JoystickButton l1 = new JoystickButton(m_joystick, 11);
    final JoystickButton r1 = new JoystickButton(m_joystick, 12);

    a.onTrue(new First(m_shifter));
    b.onTrue(new Second(m_shifter));
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
