package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ArcadeDrive;

public class AutoSubsystem extends SubsystemBase {

    // 36 and 3/4 is how far from the edge of the tape we need to line up, we also
    // need to have the left bumper slightly off the line

    private double m_throttle = 0;
    private double m_rotation = 0;
    private int auto = 1;

    private int stopwatchCounter = -1;

    private Drivetrain m_drivetrain = new Drivetrain();

    /**
     * This function is called every few milliseconds when the robot is enabled
     * 
     * For this subsystem, this function drives all auto paths
     */
    @Override
    public void periodic() {

        if (auto == 1) {
            if (DriverStation.isAutonomousEnabled() && stopwatchCounter < AutoSubsystemValues.throttle.size() - 1) {
                stopwatchCounter++;

                // lift
                double leftLiftSpeed = AutoSubsystemValues.leftLiftSpeeds.get(stopwatchCounter);
                double rightLiftSpeed = AutoSubsystemValues.rightLiftSpeeds.get(stopwatchCounter);

                Constants.controllers.leftLiftSpark.set(leftLiftSpeed);
                Constants.controllers.rightLiftSpark.set(rightLiftSpeed);

                // intake
                double intake = AutoSubsystemValues.intaking.get(stopwatchCounter);

                Constants.controllers.intakeSpark.set(intake);

                // intake pos
                Value intakePos = AutoSubsystemValues.intakePos.get(stopwatchCounter);

                Constants.pneumatics.intakeSolenoid.set(intakePos);


                //gear
Boolean gear = AutoSubsystemValues.gear.get(stopwatchCounter);

Constants.pneumatics.shifterSolenoid.set(gear);

                m_throttle = AutoSubsystemValues.throttle.get(stopwatchCounter);
                m_rotation = AutoSubsystemValues.rotation.get(stopwatchCounter);

                // drive using m_chassisSpeeds
                // get the wheel speeds
                new ArcadeDrive(() -> m_throttle, () -> m_rotation, m_drivetrain);

            } else if (DriverStation.isAutonomousEnabled()
                    && stopwatchCounter >= AutoSubsystemValues.throttle.size() - 1) {
                // if we run out of code to run in auto, make sure everything is not moving
                Constants.controllers.leftLiftSpark.set(0.0);
                Constants.controllers.rightLiftSpark.set(0.0);
                Constants.controllers.intakeSpark.set(0.0);

                Constants.controllers.leftFrontSpark.set(0.0);
                Constants.controllers.leftRearSpark.set(0.0);
                Constants.controllers.rightFrontSpark.set(0.0);
                Constants.controllers.rightRearSpark.set(0.0);
            }
        } else if (auto == 2) {
            if (DriverStation.isAutonomousEnabled() && stopwatchCounter < AutoSubsystemValues.throttle.size() - 1) {
                stopwatchCounter++;

                // shoot
                double leftLiftSpeed = AutoSubsystemValues.leftLiftSpeeds.get(stopwatchCounter);
                double rightLiftSpeed = AutoSubsystemValues.rightLiftSpeeds.get(stopwatchCounter);

                Constants.controllers.leftLiftSpark.set(leftLiftSpeed);
                Constants.controllers.rightLiftSpark.set(rightLiftSpeed);

                // intake
                double intake = AutoSubsystemValues.intaking.get(stopwatchCounter);

                Constants.controllers.intakeSpark.set(intake);

                // intake pos
                Value intakePos = AutoSubsystemValues.intakePos.get(stopwatchCounter);

                Constants.pneumatics.intakeSolenoid.set(intakePos);

                m_throttle = AutoSubsystemValues.throttle.get(stopwatchCounter);
                m_rotation = AutoSubsystemValues.rotation.get(stopwatchCounter);

                // drive using m_chassisSpeeds
                // get the wheel speeds
                new ArcadeDrive(() -> m_throttle, () -> m_rotation, m_drivetrain);
            } else if (DriverStation.isAutonomousEnabled()
                    && stopwatchCounter >= AutoSubsystemValues.throttle.size() - 1) {
                // if we run out of code to run in auto, make sure everything is not moving
                Constants.controllers.leftLiftSpark.set(0.0);
                Constants.controllers.rightLiftSpark.set(0.0);
                Constants.controllers.intakeSpark.set(0.0);

                Constants.controllers.leftFrontSpark.set(0.0);
                Constants.controllers.leftRearSpark.set(0.0);
                Constants.controllers.rightFrontSpark.set(0.0);
                Constants.controllers.rightRearSpark.set(0.0);
            }
        } else if (auto == 3) { // do nothing
            Constants.controllers.leftLiftSpark.set(0.0);
            Constants.controllers.rightLiftSpark.set(0.0);
            Constants.controllers.intakeSpark.set(0.0);

            Constants.controllers.leftFrontSpark.set(0.0);
            Constants.controllers.leftRearSpark.set(0.0);
            Constants.controllers.rightFrontSpark.set(0.0);
            Constants.controllers.rightRearSpark.set(0.0);
        }
    }

    public void setAuto(int auto) {
        this.auto = auto;
    }

public void addDriveSpeeds(double throttle, double rotation){
    AutoSubsystemValues.throttle.add(throttle);
    AutoSubsystemValues.rotation.add(rotation);
}

    public void addIntakePos(Value position) {
        AutoSubsystemValues.intakePos.add(position);
    }

    public void addShifter(Boolean gear) {
        AutoSubsystemValues.gear.add(gear);
    }

    public void addIntaking(double intake) {
        AutoSubsystemValues.intaking.add(intake);
    }

    public void addLeftLiftSpeed(double leftLiftSpeed) {
        AutoSubsystemValues.leftLiftSpeeds.add(leftLiftSpeed);
    }

    public void addRightLiftSpeed(double rightLiftSpeed) {
        AutoSubsystemValues.rightLiftSpeeds.add(rightLiftSpeed);
    }

    public void clearShit() {
        AutoSubsystemValues.leftLiftSpeeds.clear();
        AutoSubsystemValues.rightLiftSpeeds.clear();
        AutoSubsystemValues.intaking.clear();
        AutoSubsystemValues.throttle.clear();
        AutoSubsystemValues.rotation.clear();
        AutoSubsystemValues.intakePos.clear();
        AutoSubsystemValues.gear.clear();
        stopwatchCounter = -1;
    }

    public void printSpeeds() {
        String toPrint = "";

        // append the throttle speeds
        toPrint += "\n\nList<Double> throttle = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.throttle.size(); i++) {
            double throttle = AutoSubsystemValues.throttle.get(i);
            toPrint += throttle;

            if (i != AutoSubsystemValues.throttle.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));\n";

        // append the rotation speeds
        toPrint += "\n\nList<Double> rotation = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.rotation.size(); i++) {
            double  rotation = AutoSubsystemValues.rotation.get(i);
            toPrint += rotation;

            if (i != AutoSubsystemValues.intaking.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));\n";


        // append the intake speeds
        toPrint += "\n\nList<Double> intaking = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.intaking.size(); i++) {
            double intake = AutoSubsystemValues.intaking.get(i);
            toPrint += intake;

            if (i != AutoSubsystemValues.intaking.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));\n";

        // append the shifter gear
        toPrint += "\n\nList<Boolean> gear = new LinkedList<Boolean>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.gear.size(); i++) {
            Boolean gear = AutoSubsystemValues.gear.get(i);
            toPrint += gear;

            if (i != AutoSubsystemValues.gear.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));\n";

        // append the left lift speeds
        toPrint += "\n\nList<Double> leftLiftSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.leftLiftSpeeds.size(); i++) {
            double lift = AutoSubsystemValues.leftLiftSpeeds.get(i);
            toPrint += lift;

            if (i != AutoSubsystemValues.leftLiftSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));\n";

        // append the right lift speeds
        toPrint += "\n\nList<Double> rightLiftSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.rightLiftSpeeds.size(); i++) {
            double lift = AutoSubsystemValues.rightLiftSpeeds.get(i);
            toPrint += lift;

            if (i != AutoSubsystemValues.rightLiftSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));\n";

        // append the intake position
        toPrint += "\n\nList<Value> intakePos = new LinkedList<Value>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.intakePos.size(); i++) {
            Value position = AutoSubsystemValues.intakePos.get(i);
            toPrint += position;

            if (i != AutoSubsystemValues.intakePos.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));\n";

        System.out.println("\n");
        System.out.println("\n");
        System.out.println(toPrint);
        System.out.println("\n");
        System.out.println("\n");

        AutoSubsystemValues.leftLiftSpeeds.clear();
        AutoSubsystemValues.rightLiftSpeeds.clear();
        AutoSubsystemValues.intaking.clear();
        AutoSubsystemValues.throttle.clear();
        AutoSubsystemValues.rotation.clear();
        AutoSubsystemValues.intakePos.clear();
        AutoSubsystemValues.gear.clear();
    }
}
