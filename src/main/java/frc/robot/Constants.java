package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public final class Constants {

    public static final class pneumatics{
        // public static final Solenoid lSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
        // public static final Solenoid rSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 1);
        public static final DoubleSolenoid lSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
        public static final DoubleSolenoid rSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    }

    public static final class controllers {
        public static final int DRIVETRAIN_LEFT_FRONT_SPARK = 1; // front = closest to intake, left = facing intake
        public static final int DRIVETRAIN_LEFT_REAR_SPARK = 2;
        public static final int DRIVETRAIN_RIGHT_FRONT_SPARK = 3;
        public static final int DRIVETRAIN_RIGHT_REAR_SPARK = 4;
        public static final int LEFT_INTAKE_SPARK = 5;
        public static final int RIGHT_INTAKE_SPARK = 6;
        public static final int ELEVATOR_SPARK = 7;

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
        public static final CANSparkMax leftIntakeSpark = new CANSparkMax(LEFT_INTAKE_SPARK, 
                MotorType.kBrushless);
        public static final CANSparkMax rightIntakeSpark = new CANSparkMax(RIGHT_INTAKE_SPARK, 
                MotorType.kBrushless);
        public static final CANSparkMax elevatorSpark = new CANSparkMax(ELEVATOR_SPARK,
                MotorType.kBrushless);        
        }

        public static final class drive{
            public static final MotorControllerGroup m_leftMotorController = new MotorControllerGroup(controllers.leftFrontSpark, controllers.leftRearSpark);
            public static final MotorControllerGroup m_rightMotorController = new MotorControllerGroup(controllers.rightFrontSpark, controllers.rightRearSpark);

            public static final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotorController, m_rightMotorController);
            public static final double DRIVE_SPEED = 0.3;
        }

        public static final class intake{
            public static final boolean LEFT_INVERTED = true;
            public static final boolean RIGHT_INVERTED = false;

            public static final double INTAKE_SPEED = 0.5;
        }
    }