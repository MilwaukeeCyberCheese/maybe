package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.kauailabs.navx.frc.AHRS;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public final class Constants {

        public static final class balance {
                public static final AHRS gyro = new AHRS(SerialPort.Port.kUSB);
                public static final double IMBALANCED_THRESHOLD_DEGREES = 2.5;
                public static final double BALANCED_THRESHOLD_DEGREES = 1.0;
                public static final double BALANCE_SPEED_MOD = 1;
                public static final double P = 0.023;
                public static final double I = 0.000;
                public static final double D = 0.002;

                public static final double DRIVE_SPEED = -0.45;

                public static final double START_BALANCE_ANGLE = 13;

        }

        public static final class sensors {

                public static final double LEFT_DRIVE_ENCODER_DISTANCE_PER_PULSE = 0.0;
                public static final double RIGHT_DRIVE_ENCODER_DISTANCE_PER_PULSE = 0.0;

                public static final RelativeEncoder leftLift = controllers.leftLiftSpark.getEncoder();
                public static final RelativeEncoder rightLift = controllers.rightLiftSpark.getEncoder();
        }

        public static final class pneumatics {
                public static final Solenoid shifterSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

                public static final DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6,
                                7);

                public static final Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

                public static final PneumaticsControlModule PCM = new PneumaticsControlModule();

        }

        public static final class controllers {
                public static final int DRIVETRAIN_LEFT_FRONT_SPARK = 1; // front = closest to intake, left = facing
                                                                         // intake
                public static final int DRIVETRAIN_RIGHT_FRONT_SPARK = 2;
                public static final int DRIVETRAIN_LEFT_REAR_SPARK = 3;
                public static final int DRIVETRAIN_RIGHT_REAR_SPARK = 4;
                public static final int LEFT_LIFT_SPARK = 6;
                public static final int RIGHT_LIFT_SPARK = 5;
                public static final int INTAKE_SPARK = 7;

                /**
                 * These static objects are used throughout the program.
                 * Access this object using the following code, for example:
                 * Constants.controllers.leftFrontSpark
                 */
                public static final CANSparkMax leftFrontSpark = new CANSparkMax(DRIVETRAIN_LEFT_FRONT_SPARK,
                                MotorType.kBrushless);
                public static final CANSparkMax leftRearSpark = new CANSparkMax(DRIVETRAIN_LEFT_REAR_SPARK,
                                MotorType.kBrushless);
                public static final CANSparkMax rightFrontSpark = new CANSparkMax(DRIVETRAIN_RIGHT_FRONT_SPARK,
                                MotorType.kBrushless);
                public static final CANSparkMax rightRearSpark = new CANSparkMax(DRIVETRAIN_RIGHT_REAR_SPARK,
                                MotorType.kBrushless);

                public static final CANSparkMax intakeSpark = new CANSparkMax(INTAKE_SPARK,
                                MotorType.kBrushless);

                public static final CANSparkMax leftLiftSpark = new CANSparkMax(LEFT_LIFT_SPARK,
                                MotorType.kBrushless);
                public static final CANSparkMax rightLiftSpark = new CANSparkMax(RIGHT_LIFT_SPARK,
                                MotorType.kBrushless);
        }

        public static final class drive {

                public static final MotorControllerGroup m_leftMotorController = new MotorControllerGroup(
                                controllers.leftFrontSpark, controllers.leftRearSpark);
                public static final MotorControllerGroup m_rightMotorController = new MotorControllerGroup(
                                controllers.rightFrontSpark, controllers.rightRearSpark);

                public static final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotorController,
                                m_rightMotorController);

                public static final IdleMode IDLE_MODE = IdleMode.kBrake;

                public static final int CURRENT_LIMIT = 60;

                public static final double THROTTLE_LIMITER = 0.8;
                public static final double ROTATION_LIMITER = 0.7;

                public static final double DRIVE_SPEED = 0.8;
                public static final double TURN_SPEED = 0.9;

                public static final double SLOW_DRIVE_SPEED = 0.5;
                public static final double SLOW_TURN_SPEED = 0.7;
                public static final double TURBO_DRIVE_SPEED = 1.0;
                public static final double TURBO_TURN_SPEED = 0.9;

                public static final boolean LEFT_FRONT_INVERTED = true;
                public static final boolean LEFT_REAR_INVERTED = true;
                public static final boolean RIGHT_FRONT_INVERTED = false;
                public static final boolean RIGHT_REAR_INVERTED = false;

                public static final boolean SECOND_GEAR = false;
                public static final boolean FIRST_GEAR = true;
        }

        public static final class intake {
                public static final boolean INVERTED = true;

                public static final double CONE_SPEED = 0.7;
                public static final double CUBE_SPEED = -0.5;

                public static final double CONE_SLOW = 0.3;
                public static final double CUBE_SLOW = -0.2;

                public static final Value intakeDown = Value.kForward;
                public static final Value intakeUp = Value.kReverse;
                public static final Value intakeOff = Value.kOff;

                public static final int STALL_LIMIT = 30;
                public static final int FREE_LIMIT = 60;
        }

        public static final class lift {
                public static final double MIN_INTAKE = 0;
                public static final double MAX_INTAKE = 25;

                public static final boolean LEFT_INVERTED = true;
                public static final boolean RIGHT_INVERTED = false;

                public static final double LIFT_SPEED = 0.35;
                public static final double PID_CHANGE_SPEED = 1.2;

                public static final double SPEED_LIMITER_UPPER = 0.45;
                public static final double SPEED_LIMITER_LOWER = -0.45;

                public static final double CUBE_INTAKE_POSITION = 0;

                public static final double CONE_INTAKE_POSITION = 7;

                public static final double CUBE_PLACE_POSITION = 90;

                public static final double CONE_PLACE_POSITION = 102;

                public static final double MIN_POSITION = 0;
                public static final double MAX_POSITION = 105;

                public static final int MAX_FREE_AMPS = 60;
                public static final int MAX_STALL_AMPS = 5;
                public static final int STALL_RPM = 20;

                public static double P = 0.5;
                public static double I = 0;
                public static double D = 0;
        }
}