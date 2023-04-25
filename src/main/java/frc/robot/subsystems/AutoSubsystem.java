package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AutoSubsystem extends SubsystemBase {

    @Override
    public void periodic(){
        
    }

    // adds drive speeds to recording
    public void addDriveSpeeds(double left, double right) {
        AutoSubsystemValues.left.left.get(0).add(left);
        AutoSubsystemValues.right.right.get(0).add(right);
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
        AutoSubsystemValues.left.left.get(0).clear();
        AutoSubsystemValues.right.right.get(0).clear();
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
        for (int i = 0; i < AutoSubsystemValues.left.left.get(0).size(); i++) {
            double frontLeftSpeeds = AutoSubsystemValues.left.left.get(0).get(i);
            toPrint += frontLeftSpeeds;

            if (i != AutoSubsystemValues.left.left.get(0).size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the frontRight speeds
        toPrint += "\n\n public static List<Double> //TODO frontRightSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.right.right.get(0).size(); i++) {
            double frontRightSpeeds = AutoSubsystemValues.right.right.get(0).get(i);
            toPrint += frontRightSpeeds;

            if (i != AutoSubsystemValues.right.right.get(0).size() - 1) {
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
        AutoSubsystemValues.left.left.get(0).clear();
        AutoSubsystemValues.right.right.get(0).clear();
        AutoSubsystemValues.intakePos.intakePos.get(0).clear();
        AutoSubsystemValues.gear.gear.get(0).clear();
    }
}
