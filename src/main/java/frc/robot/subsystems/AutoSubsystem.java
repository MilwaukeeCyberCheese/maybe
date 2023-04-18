package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AutoSubsystem extends SubsystemBase {

    // adds drive speeds to recording
    public void addDriveSpeeds(double frontLeft, double frontRight, double backLeft, double backRight) {
        AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(0).add(frontLeft);
        AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(0).add(frontRight);
        AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(0).add(backLeft);
        AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(0).add(backRight);
    }

    // adds intake position to recording
    public void addIntakePos(Value position) {
        AutoSubsystemValues.intakePos.intakePos.get(0).add(position);
    }

    // adds gear of shifter to recording
    public void addShifter(Boolean gear) {
        AutoSubsystemValues.gear.gear.get(0).add(gear);
    }

    // adds speed of intake to recording
    public void addIntaking(double intake) {
        AutoSubsystemValues.intaking.intaking.get(0).add(intake);
    }

    // adds lift position to intaking
    public void addLiftPos(double liftPos) {
        AutoSubsystemValues.liftPos.liftPos.get(0).add(liftPos);
    }

    // clears values of lists
    public void clearShit() {
        AutoSubsystemValues.liftPos.liftPos.get(0).clear();
        AutoSubsystemValues.intaking.intaking.get(0).clear();
        AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(0).clear();
        AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(0).clear();
        AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(0).clear();
        AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(0).clear();
        AutoSubsystemValues.intakePos.intakePos.get(0).clear();
        AutoSubsystemValues.gear.gear.get(0).clear();
    }

    // prints out speeds and positions
    public void printShit() {
        String toPrint = "";
        // header and whatnot
        // toPrint += "package frc.robot.subsystems; import java.util.Arrays; import
        // java.util.LinkedList; import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
        // import java.util.List; import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
        // public final class AutoSubsystemValues{ ";

        // append the frontLeft speeds
        toPrint += "\n\n public static List<Double> //TODO frontLeftSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(0).size(); i++) {
            double frontLeftSpeeds = AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(0).get(i);
            toPrint += frontLeftSpeeds;

            if (i != AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(0).size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the frontRight speeds
        toPrint += "\n\n public static List<Double> //TODO frontRightSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(0).size(); i++) {
            double frontRightSpeeds = AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(0).get(i);
            toPrint += frontRightSpeeds;

            if (i != AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(0).size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the backLeft speeds
        toPrint += "\n\n public static List<Double> //TODO backLeftSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(0).size(); i++) {
            double backLeftSpeeds = AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(0).get(i);
            toPrint += backLeftSpeeds;

            if (i != AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(0).size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the backRight speeds
        toPrint += "\n\n public static List<Double> //TODO backRightSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(0).size(); i++) {
            double backRightSpeeds = AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(0).get(i);
            toPrint += backRightSpeeds;

            if (i != AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(0).size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the intake speeds
        toPrint += "\n\n public static List<Double> //TODO intaking = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.intaking.intaking.get(0).size(); i++) {
            double intake = AutoSubsystemValues.intaking.intaking.get(0).get(i);
            toPrint += intake;

            if (i != AutoSubsystemValues.intaking.intaking.get(0).size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the shifter gear
        toPrint += "\n\n public static List<Boolean> //TODO gear = new LinkedList<Boolean>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.gear.gear.get(0).size(); i++) {
            Boolean gear = AutoSubsystemValues.gear.gear.get(0).get(i);
            toPrint += gear;

            if (i != AutoSubsystemValues.gear.gear.get(0).size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the lift position
        toPrint += "\n\n public static List<Double> //TODO liftPos = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.liftPos.liftPos.get(0).size(); i++) {
            double lift = AutoSubsystemValues.liftPos.liftPos.get(0).get(i);
            toPrint += lift;

            if (i != AutoSubsystemValues.liftPos.liftPos.get(0).size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the intake position
        toPrint += "\n\n public static List<Value> //TODO intakePos = new LinkedList<Value>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.intakePos.intakePos.get(0).size(); i++) {
            Value position = AutoSubsystemValues.intakePos.intakePos.get(0).get(i);
            toPrint += "Value." + position;

            if (i != AutoSubsystemValues.intakePos.intakePos.get(0).size() - 1) {
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

        AutoSubsystemValues.liftPos.liftPos.get(0).clear();
        AutoSubsystemValues.intaking.intaking.get(0).clear();
        AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(0).clear();
        AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(0).clear();
        AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(0).clear();
        AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(0).clear();
        AutoSubsystemValues.intakePos.intakePos.get(0).clear();
        AutoSubsystemValues.gear.gear.get(0).clear();
    }
}
