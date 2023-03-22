package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ArcadeDrive;

public class AutoSubsystem extends SubsystemBase {

    // side to love handles is 10, ass to line is 23 inches

    private double m_throttle = 0;
    private double m_rotation = 0;
    private int auto = 1;

    private int stopwatchCounter = -1;

    private Drivetrain m_drivetrain = new Drivetrain();

    /**
     * This function is called every twenty milliseconds when the robot is enabled
     * 
     * For this subsystem, this function drives all auto paths
     */
    @Override
    public void periodic() {

        if (auto == 1) {
            if (DriverStation.isAutonomousEnabled() && stopwatchCounter < AutoSubsystemValues.throttle.throttle.size() - 1) {
                stopwatchCounter++;

                // lift
                double leftLiftSpeed = AutoSubsystemValues.leftLiftSpeeds.leftLiftSpeeds.get(stopwatchCounter);
                double rightLiftSpeed = AutoSubsystemValues.rightLiftSpeeds.rightLiftSpeeds.get(stopwatchCounter);

                Constants.controllers.leftLiftSpark.set(leftLiftSpeed);
                Constants.controllers.rightLiftSpark.set(rightLiftSpeed);

                // intake
                double intake = AutoSubsystemValues.intaking.intaking.get(stopwatchCounter);

                Constants.controllers.intakeSpark.set(intake);

                // intake pos
                Value intakePos = AutoSubsystemValues.intakePos.intakePos.get(stopwatchCounter);

                Constants.pneumatics.intakeSolenoid.set(intakePos);


                //gear
Boolean gear = AutoSubsystemValues.gear.gear.get(stopwatchCounter);

Constants.pneumatics.shifterSolenoid.set(gear);

                m_throttle = AutoSubsystemValues.throttle.throttle.get(stopwatchCounter);
                m_rotation = AutoSubsystemValues.rotation.rotation.get(stopwatchCounter);

                // drive using m_chassisSpeeds
                // get the wheel speeds
                m_drivetrain.drive(m_throttle, m_rotation);

            } else if (DriverStation.isAutonomousEnabled()
                    && stopwatchCounter >= AutoSubsystemValues.throttle.throttle.size() - 1) {
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
            if (DriverStation.isAutonomousEnabled() && stopwatchCounter < AutoSubsystemValues.throttle.throttle.size() - 1) {
                stopwatchCounter++;

                // shoot
                double leftLiftSpeed = AutoSubsystemValues.leftLiftSpeeds.leftLiftSpeeds.get(stopwatchCounter);
                double rightLiftSpeed = AutoSubsystemValues.rightLiftSpeeds.rightLiftSpeeds.get(stopwatchCounter);

                Constants.controllers.leftLiftSpark.set(leftLiftSpeed);
                Constants.controllers.rightLiftSpark.set(rightLiftSpeed);

                // intake
                double intake = AutoSubsystemValues.intaking.intaking.get(stopwatchCounter);

                Constants.controllers.intakeSpark.set(intake);

                // intake pos
                Value intakePos = AutoSubsystemValues.intakePos.intakePos.get(stopwatchCounter);

                Constants.pneumatics.intakeSolenoid.set(intakePos);

                m_throttle = AutoSubsystemValues.throttle.throttle.get(stopwatchCounter);
                m_rotation = AutoSubsystemValues.rotation.rotation.get(stopwatchCounter);

                // drive using m_chassisSpeeds
                // get the wheel speeds
                m_drivetrain.drive(m_throttle, m_rotation);;
            } else if (DriverStation.isAutonomousEnabled()
                    && stopwatchCounter >= AutoSubsystemValues.throttle.throttle.size() - 1) {
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
    AutoSubsystemValues.throttle.throttle.add(throttle);
    AutoSubsystemValues.rotation.rotation.add(rotation);
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

    public void addLeftLiftSpeed(double leftLiftSpeed) {
        AutoSubsystemValues.leftLiftSpeeds.leftLiftSpeeds.add(leftLiftSpeed);
    }

    public void addRightLiftSpeed(double rightLiftSpeed) {
        AutoSubsystemValues.rightLiftSpeeds.rightLiftSpeeds.add(rightLiftSpeed);
    }

    public void clearShit() {
        AutoSubsystemValues.leftLiftSpeeds.leftLiftSpeeds.clear();
        AutoSubsystemValues.rightLiftSpeeds.rightLiftSpeeds.clear();
        AutoSubsystemValues.intaking.intaking.clear();
        AutoSubsystemValues.throttle.throttle.clear();
        AutoSubsystemValues.rotation.rotation.clear();
        AutoSubsystemValues.intakePos.intakePos.clear();
        AutoSubsystemValues.gear.gear.clear();
        stopwatchCounter = -1;
    }

    public void printSpeeds() {
        String toPrint = "";


        // append the throttle speeds
        toPrint += "\n\npublic static class throttle {\nstatic List<Double> throttle = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.throttle.throttle.size(); i++) {
            double throttle = AutoSubsystemValues.throttle.throttle.get(i);
            toPrint += throttle;

            if (i != AutoSubsystemValues.throttle.throttle.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the rotation speeds
        toPrint += "\n\npublic static class rotation {\nstatic List<Double> rotation = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.rotation.rotation.size(); i++) {
            double  rotation = AutoSubsystemValues.rotation.rotation.get(i);
            toPrint += rotation;

            if (i != AutoSubsystemValues.rotation.rotation.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";


        // append the intake speeds
        toPrint += "\n\npublic static class intaking {\nstatic List<Double> intaking = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.intaking.intaking.size(); i++) {
            double intake = AutoSubsystemValues.intaking.intaking.get(i);
            toPrint += intake;

            if (i != AutoSubsystemValues.intaking.intaking.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the shifter gear
        toPrint += "\n\npublic static class gear {\nstatic List<Boolean> gear = new LinkedList<Boolean>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.gear.gear.size(); i++) {
            Boolean gear = AutoSubsystemValues.gear.gear.get(i);
            toPrint += gear;

            if (i != AutoSubsystemValues.gear.gear.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the left lift speeds
        toPrint += "\n\npublic static class leftLiftSpeeds {\nstatic List<Double> leftLiftSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.leftLiftSpeeds.leftLiftSpeeds.size(); i++) {
            double lift = AutoSubsystemValues.leftLiftSpeeds.leftLiftSpeeds.get(i);
            toPrint += lift;

            if (i != AutoSubsystemValues.leftLiftSpeeds.leftLiftSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the right lift speeds
        toPrint += "\n\npublic static class rightLiftSpeeds {\nstatic List<Double> rightLiftSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.rightLiftSpeeds.rightLiftSpeeds.size(); i++) {
            double lift = AutoSubsystemValues.rightLiftSpeeds.rightLiftSpeeds.get(i);
            toPrint += lift;

            if (i != AutoSubsystemValues.rightLiftSpeeds.rightLiftSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the intake position
        toPrint += "\n\npublic static class intakePos {\nstatic List<Value> intakePos = new LinkedList<Value>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.intakePos.intakePos.size(); i++) {
            Value position = AutoSubsystemValues.intakePos.intakePos.get(i);
            toPrint += position;

            if (i != AutoSubsystemValues.intakePos.intakePos.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n\n";

        System.out.println("\n");
        System.out.println("\n");
        System.out.println(toPrint);
        System.out.println("\n");
        System.out.println("\n");

        AutoSubsystemValues.leftLiftSpeeds.leftLiftSpeeds.clear();
        AutoSubsystemValues.rightLiftSpeeds.rightLiftSpeeds.clear();
        AutoSubsystemValues.intaking.intaking.clear();
        AutoSubsystemValues.throttle.throttle.clear();
        AutoSubsystemValues.rotation.rotation.clear();
        AutoSubsystemValues.intakePos.intakePos.clear();
        AutoSubsystemValues.gear.gear.clear();
    }
}
