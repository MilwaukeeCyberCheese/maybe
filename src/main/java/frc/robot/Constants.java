package frc.robot;

import edu.wpi.first.hal.CTREPCMJNI;
import edu.wpi.first.hal.simulation.CTREPCMDataJNI;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public final class Constants {

        public static final class sensors {

                public static final Encoder m_leftEncoder = new Encoder(1, 2);
                public static final Encoder m_rightEncoder = new Encoder(3, 4);


                public static final double LEFT_DRIVE_ENCODER_DISTANCE_PER_PULSE = 0.0;
                public static final double RIGHT_DRIVE_ENCODER_DISTANCE_PER_PULSE = 0.0;

                public static final Encoder leftLiftEncoder = new Encoder(5, 6);
                public static final Encoder rightLiftEncoder = new Encoder(7, 8);

                public static final int GYRO = 0;
        }

        public static final class pneumatics {
                public static final Solenoid lSolenoid = new
                Solenoid(PneumaticsModuleType.CTREPCM, 0);
                public static final Solenoid rSolenoid = new
                Solenoid(PneumaticsModuleType.CTREPCM, 1);
                public static final Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
                
                public static final PneumaticsControlModule PCM = new PneumaticsControlModule();
                
        }

        public static final class controllers {
                public static final int DRIVETRAIN_LEFT_FRONT_SPARK = 1; // front = closest to intake, left = facing intake
                public static final int DRIVETRAIN_LEFT_REAR_SPARK = 2;
                public static final int DRIVETRAIN_RIGHT_FRONT_SPARK = 3;
                public static final int DRIVETRAIN_RIGHT_REAR_SPARK = 4;
                public static final int LEFT_LIFT_SPARK = 5;
                public static final int RIGHT_LIFT_SPARK = 6;
                public static final int INTAKE_SPARK = 7;

                /**
                 * These static objects are used throughout the program.
                 * Access this object using the following code, for example:
                 * Constants.controllers.leftFrontSpark
                 */
                public static final CANSparkMax leftFrontSpark = new CANSparkMax(DRIVETRAIN_LEFT_FRONT_SPARK,
                                MotorType.kBrushed);
                public static final CANSparkMax leftRearSpark = new CANSparkMax(DRIVETRAIN_LEFT_REAR_SPARK,
                                MotorType.kBrushed);
                public static final CANSparkMax rightFrontSpark = new CANSparkMax(DRIVETRAIN_RIGHT_FRONT_SPARK,
                                MotorType.kBrushed);
                public static final CANSparkMax rightRearSpark = new CANSparkMax(DRIVETRAIN_RIGHT_REAR_SPARK,
                                MotorType.kBrushed);

                public static final CANSparkMax intakeSpark = new CANSparkMax(INTAKE_SPARK,
                                MotorType.kBrushless);

                public static final CANSparkMax leftLiftSpark = new CANSparkMax(LEFT_LIFT_SPARK, 
                                MotorType.kBrushed);
                public static final CANSparkMax rightLiftSpark = new CANSparkMax(RIGHT_LIFT_SPARK, 
                                MotorType.kBrushed);
        }

        public static final class drive {
                public static final MotorControllerGroup m_leftMotorController = new MotorControllerGroup(
                                controllers.leftFrontSpark, controllers.leftRearSpark);
                public static final MotorControllerGroup m_rightMotorController = new MotorControllerGroup(
                                controllers.rightFrontSpark, controllers.rightRearSpark);

                public static final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotorController,
                                m_rightMotorController);

                public static final double DRIVE_SPEED = 0.5;

                public static final boolean LEFT_FRONT_INVERTED = true;
                public static final boolean LEFT_REAR_INVERTED = false;
                public static final boolean RIGHT_FRONT_INVERTED = false;
                public static final boolean RIGHT_REAR_INVERTED = true;
        }

        public static final class intake {
                public static final boolean INVERTED = true;

                public static final double INTAKE_SPEED = 0.5;
        }

        public static final class lift {
                public static final boolean LEFT_INVERTED = true;
                public static final boolean RIGHT_INVERTED = true;



                public static final double LIFT_SPEED = 0.5;

                public static final double POSITION_ZERO = 0;
                public static final double POSITION_ONE = 1;
                public static final double POSITION_TWO = 2;
                public static final double POSITION_THREE = 3;
        }
}