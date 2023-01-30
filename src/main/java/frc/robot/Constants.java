package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public final class Constants {

    public static final class pneumatics{
        public static final Solenoid lSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
        public static final Solenoid rSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 1);
    }

    public static final class controllers {
        public static final int DRIVETRAIN_LEFT_FRONT_SPARK = 1; // front = closest to intake, left = facing intake
        public static final int DRIVETRAIN_LEFT_REAR_SPARK = 2;
        public static final int DRIVETRAIN_RIGHT_FRONT_SPARK = 3;
        public static final int DRIVETRAIN_RIGHT_REAR_SPARK = 4;

        /**
         * These static objects are used throughout the program.
         * Access this object using the following code, for example:
         * Constants.controllers.leftFrontSpark
         */
        public static final CANSparkMax leftFrontSpark = new CANSparkMax(DRIVETRAIN_LEFT_FRONT_SPARK,
                MotorType.kBrushed);
        public static final CANSparkMax leftRearSpark = new CANSparkMax(DRIVETRAIN_LEFT_REAR_SPARK, MotorType.kBrushed);
        public static final CANSparkMax rightFrontSpark = new CANSparkMax(DRIVETRAIN_RIGHT_FRONT_SPARK,
                MotorType.kBrushed);
        public static final CANSparkMax rightRearSpark = new CANSparkMax(DRIVETRAIN_RIGHT_REAR_SPARK,
                MotorType.kBrushed);
        }

        public static final class drive{
            public static final MotorController m_leftMotorController = new MotorControllerGroup(controllers.leftFrontSpark, controllers.leftRearSpark);
            public static final MotorController m_rightMotorController = new MotorControllerGroup(controllers.rightFrontSpark, controllers.rightRearSpark);

            public static final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotorController, m_rightMotorController);
        }
    }