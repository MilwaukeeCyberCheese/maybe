package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ArcadeDrive;

public class AutoSubsystem extends SubsystemBase {

    // side to love handles is 10, ass to line is 23 inches

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
            if (DriverStation.isAutonomousEnabled() && stopwatchCounter < AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.size() - 1) {
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

                // double m_throttle = AutoSubsystemValues.throttle.throttle.get(stopwatchCounter);
                // double m_rotation = AutoSubsystemValues.rotation.rotation.get(stopwatchCounter);

                // drive using m_chassisSpeeds
                // get the wheel speeds
                // m_drivetrain.drive(m_throttle, m_rotation);

            } else if (DriverStation.isAutonomousEnabled()
                    && stopwatchCounter >= AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.size() - 1) {
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
            if (DriverStation.isAutonomousEnabled() && stopwatchCounter < AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.size() - 1) {
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

                // double m_throttle = AutoSubsystemValues.throttle.throttle.get(stopwatchCounter);
                // double m_rotation = AutoSubsystemValues.rotation.rotation.get(stopwatchCounter);

                // drive using m_chassisSpeeds
                // get the wheel speeds
                // m_drivetrain.drive(m_throttle, m_rotation);
            } else if (DriverStation.isAutonomousEnabled()
                    && stopwatchCounter >= AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.size() - 1) {
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

public void addDriveSpeeds(double frontLeft, double frontRight, double backLeft, double backRight){
    AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.add(frontLeft);
    AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.add(frontRight);
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
        AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.clear();
        AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.clear();
        AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.clear();
        AutoSubsystemValues.backRightSpeeds.backRightSpeeds.clear();
        AutoSubsystemValues.intakePos.intakePos.clear();
        AutoSubsystemValues.gear.gear.clear();
        stopwatchCounter = -1;
    }

    public void printSpeeds() {
        String toPrint = "";


        // append the frontLeft speeds
        toPrint += "\n\npublic static class frontLeftSpeeds {\nstatic List<Double> frontLeftSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.size(); i++) {
            double frontLeftSpeeds = AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.get(i);
            toPrint += frontLeftSpeeds;

            if (i != AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the rotation speeds
        toPrint += "\n\npublic static class frontRightSpeeds {\nstatic List<Double> frontRightSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.size(); i++) {
            double  frontRightSpeeds = AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.get(i);
            toPrint += frontRightSpeeds;

            if (i != AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the backLeft speeds
        toPrint += "\n\npublic static class backLeftSpeeds {\nstatic List<Double> backLeftSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.size(); i++) {
            double backLeftSpeeds = AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.get(i);
            toPrint += backLeftSpeeds;

            if (i != AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "));}\n";

        // append the backRight speeds
        toPrint += "\n\npublic static class backRightSpeeds {\nstatic List<Double> backRightSpeeds = new LinkedList<Double>(Arrays.asList(";
        for (int i = 0; i < AutoSubsystemValues.backRightSpeeds.backRightSpeeds.size(); i++) {
            double  backRightSpeeds = AutoSubsystemValues.backRightSpeeds.backRightSpeeds.get(i);
            toPrint += backRightSpeeds;

            if (i != AutoSubsystemValues.backRightSpeeds.backRightSpeeds.size() - 1) {
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
            toPrint += "Value." + position;

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
        AutoSubsystemValues.frontLeftSpeeds.frontLeftSpeeds.clear();
        AutoSubsystemValues.frontRightSpeeds.frontRightSpeeds.clear();
        AutoSubsystemValues.backLeftSpeeds.backLeftSpeeds.clear();
        AutoSubsystemValues.backRightSpeeds.backRightSpeeds.clear();
        AutoSubsystemValues.intakePos.intakePos.clear();
        AutoSubsystemValues.gear.gear.clear();
    }
}
