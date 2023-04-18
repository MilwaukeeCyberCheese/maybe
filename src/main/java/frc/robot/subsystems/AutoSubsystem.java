package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AutoSubsystem extends SubsystemBase {

    

    /**
     * This function is called every twenty milliseconds when the robot is enabled
     * 
     * For this subsystem, this function drives all auto paths
     */
    @Override
    public void periodic() {
        // auto = Robot.autoChooser.getSelected();

        // if (auto == 1) {
        //     if (DriverStation.isAutonomousEnabled()
        //             && stopwatchCounter < (AutoSubsystemValues.gear.gear.size() - 2) && !balance) {
        //         stopwatchCounter++;
        //         System.out.println(stopwatchCounter);
        //         // gear
        //         Boolean gear = AutoSubsystemValues.gear.gear.get(stopwatchCounter);
        //         Constants.pneumatics.shifterSolenoid.set(gear);
        //         // actuate lift
        //         double leftLiftSpeed = AutoSubsystemValues.leftLiftSpeeds.leftLiftSpeeds.get(stopwatchCounter);
        //         double rightLiftSpeed = AutoSubsystemValues.rightLiftSpeeds.rightLiftSpeeds.get(stopwatchCounter);

        //         Constants.controllers.leftLiftSpark.set(leftLiftSpeed);
        //         Constants.controllers.rightLiftSpark.set(rightLiftSpeed);
        //         // System.out.println(AutoSubsystemValues.intaking.intaking.size());
        //         // System.out.println(stopwatchCounter);
        //         // intake
        //         double intake = AutoSubsystemValues.intaking.intaking.get(stopwatchCounter);

        //         // if (intake != prevState) {
        //         // System.out.println("Switching State");
        //         // System.out.println(intake);
        //         // System.out.println(stopwatchCounter);
        //         // Constants.controllers.intakeSpark.set(intake);
        //         // prevState = intake;
        //         // }
        //         Constants.controllers.intakeSpark.set(intake);
        //         // System.out.println(intake);

        //         // // intake pos
        //         Value intakePos = AutoSubsystemValues.intakePos.intakePos.get(stopwatchCounter);
        //         // System.out.println(intakePos);
        //         Constants.pneumatics.intakeSolenoid.set(intakePos);

        //         // if(stopwatchCounter >= 175){
        //         // ended = false;
        //         // }
        //         // get speeds for wheels
        //         double frontLeft = AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(stopwatchCounter);
        //         double frontRight = AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(stopwatchCounter);
        //         double backLeft = AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(stopwatchCounter);
        //         double backRight = AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(stopwatchCounter);

        //         // System.out.println(frontLeft);
        //         // // set wheel speeds
        //         Constants.controllers.leftFrontSpark.set(frontLeft);
        //         Constants.controllers.rightFrontSpark.set(frontRight);
        //         Constants.controllers.leftRearSpark.set(backLeft);
        //         Constants.controllers.rightRearSpark.set(backRight);

        //         if (Math.abs(Constants.balance.gyro.getRoll()) > 12) {
        //             balance = true;
        //         }
        //     } else if (DriverStation.isAutonomousEnabled()
        //             && stopwatchCounter >= AutoSubsystemValues.gear.gear.size() - 1) {
        //         balance = true;
        //     }
        // } else if (auto == 2) {
        //     if (DriverStation.isAutonomousEnabled()) {
        //         stopwatchCounter++;
        //         // System.out.println(stopwatchCounter);

        //         Constants.pneumatics.intakeSolenoid.set(Constants.intake.intakeUp);
        //         if (stopwatchCounter < 100) {
        //             Constants.controllers.intakeSpark.set(Constants.intake.CONE_SPEED);
        //         } else if (stopwatchCounter > 100) {
        //             balance = true;
        //             Constants.controllers.intakeSpark.set(0);
        //         }
        //     }
        // } else if (auto == 3) {
        //     if (DriverStation.isAutonomousEnabled()) {
        //         Constants.controllers.leftLiftSpark.set(0.0);
        //         Constants.controllers.rightLiftSpark.set(0.0);
        //         Constants.controllers.intakeSpark.set(0.0);
        //         System.out.println("Auto 3");
        //         Constants.controllers.leftFrontSpark.set(0.0);
        //         Constants.controllers.leftRearSpark.set(0.0);
        //         Constants.controllers.rightFrontSpark.set(0.0);
        //         Constants.controllers.rightRearSpark.set(0.0);
        //     }
        // }
    }

    public void addDriveSpeeds(double frontLeft, double frontRight, double backLeft, double backRight) {
        AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(0).add(frontLeft);
        AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(0).add(frontRight);
        AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.add(backLeft);
        AutoSubsystemValues.backRightSpeeds.backRightSpeeds.add(backRight);
    }

    public void addIntakePos(Value position) {
        AutoSubsystemValues.intakePos.intakePos.add(position);
    }

    public void addShifter(Boolean gear) {
        AutoSubsystemValues.gear.gear.add(gear);
    }

    public void addIntaking(double intake) {
        AutoSubsystemValues.intaking.intaking.add(intake);
    }

    public void addLiftPos(double liftPos) {
        AutoSubsystemValues.liftPos.liftPos.add(liftPos);
    }

    public void clearShit() {
        AutoSubsystemValues.liftPos.liftPos.clear();
        AutoSubsystemValues.intaking.intaking.clear();
        AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.clear();
        AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.clear();
        AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.clear();
        AutoSubsystemValues.backRightSpeeds.backRightSpeeds.clear();
        AutoSubsystemValues.intakePos.intakePos.clear();
        AutoSubsystemValues.gear.gear.clear();
    }

    public void printSpeeds() {
        String toPrint = "";
//header and whatnot
toPrint += "package frc.robot.subsystems; import java.util.Arrays; import java.util.LinkedList; import edu.wpi.first.wpilibj.DoubleSolenoid.Value; import java.util.List; import edu.wpi.first.wpilibj.DoubleSolenoid.Value; public final class AutoSubsystemValues{ ";

        // append the frontLeft speeds
        toPrint += "\n\npublic static class frontLeftSpeeds {\npublic static List<Double> frontLeftSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.size(); i++) {
            double frontLeftSpeeds = AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(i);
            toPrint += frontLeftSpeeds;

            if (i != AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the rotation speeds
        toPrint += "\n\npublic static class frontRightSpeeds {\npublic static List<Double> frontRightSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.size(); i++) {
            double frontRightSpeeds = AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(i);
            toPrint += frontRightSpeeds;

            if (i != AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the backLeft speeds
        toPrint += "\n\npublic static class backLeftSpeeds {\npublic static List<Double> backLeftSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.size(); i++) {
            double backLeftSpeeds = AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(i);
            toPrint += backLeftSpeeds;

            if (i != AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the backRight speeds
        toPrint += "\n\npublic static class backRightSpeeds {\npublic static List<Double> backRightSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.backRightSpeeds.backRightSpeeds.size(); i++) {
            double backRightSpeeds = AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(i);
            toPrint += backRightSpeeds;

            if (i != AutoSubsystemValues.backRightSpeeds.backRightSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the intake speeds
        toPrint += "\n\npublic static class intaking {\npublic static List<Double> intaking = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.intaking.intaking.size(); i++) {
            double intake = AutoSubsystemValues.intaking.intaking.get(i);
            toPrint += intake;

            if (i != AutoSubsystemValues.intaking.intaking.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the shifter gear
        toPrint += "\n\npublic static class gear {\npublic static List<Boolean> gear = new LinkedList<Boolean>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.gear.gear.size(); i++) {
            Boolean gear = AutoSubsystemValues.gear.gear.get(i);
            toPrint += gear;

            if (i != AutoSubsystemValues.gear.gear.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the left lift speeds
        toPrint += "\n\npublic static class liftPos {\npublic static List<Double> liftPos = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.liftPos.liftPos.size(); i++) {
            double lift = AutoSubsystemValues.liftPos.liftPos.get(i);
            toPrint += lift;

            if (i != AutoSubsystemValues.liftPos.liftPos.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the intake position
        toPrint += "\n\npublic static class intakePos {\npublic static List<Value> intakePos = new LinkedList<Value>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.intakePos.intakePos.size(); i++) {
            Value position = AutoSubsystemValues.intakePos.intakePos.get(i);
            toPrint += "Value." + position;

            if (i != AutoSubsystemValues.intakePos.intakePos.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n\n";
        toPrint += "}";

        System.out.println("\n");
        System.out.println("\n");
        System.out.println(toPrint);
        System.out.println("\n");
        System.out.println("\n");

        AutoSubsystemValues.liftPos.liftPos.clear();
        AutoSubsystemValues.intaking.intaking.clear();
        AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.clear();
        AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.clear();
        AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.clear();
        AutoSubsystemValues.backRightSpeeds.backRightSpeeds.clear();
        AutoSubsystemValues.intakePos.intakePos.clear();
        AutoSubsystemValues.gear.gear.clear();
    }
}
