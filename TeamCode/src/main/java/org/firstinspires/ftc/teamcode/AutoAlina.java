package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name= "AutoAlina")
public class AutoAlina extends LinearOpMode {
    @Override
    public void runOpMode() {
        //initializing yay
        CatapultAlina catapult = new CatapultAlina(hardwareMap);
        DriveTrainAlina driveTrain = new DriveTrainAlina(hardwareMap, telemetry);

        // Declare hardware objects
        DcMotor leftDrive = null;
        DcMotor rightDrive = null;
        ElapsedTime runtime = new ElapsedTime();

        telemetry.addLine("Ready");

            telemetry.addData("Status", "Initialized");
            telemetry.update();

            // Initialize hardware variables. The strings here must match your robot configuration.
            leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
            rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

            // Most robots need the motor on one side to be reversed to drive forward
            leftDrive.setDirection(DcMotor.Direction.FORWARD);
            rightDrive.setDirection(DcMotor.Direction.REVERSE);
            //wait for start

            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            runtime.reset();

            // Step 1: Drive forward for 2.0 seconds
            if (!opModeIsActive()) return;

            // 1. SHOOT
            CatapultAlina.catapultShooting();
            sleep(1250);

            // 2. DRIVE FORWARD
            if (opModeIsActive()) {
                // Set motor power to half speed
                leftDrive.setPower(0.5);
                rightDrive.setPower(0.5);

                // Keep moving until 2 seconds have passed
                while (opModeIsActive() && (runtime.seconds() < 2.0)) {
                    telemetry.addData("Path", "Driving Forward: %2.5f S", runtime.seconds());
                    telemetry.update();
                }

                // Stop the robot
                leftDrive.setPower(0.0);
                rightDrive.setPower(0.0);

                // Rotate right 90 degrees
                leftDrive.setPower(0.5);
                rightDrive.setPower(-0.5);

                sleep(500); //i have to adjust this

                // Stop after turning
                leftDrive.setPower(0);
                rightDrive.setPower(0);

                telemetry.addData("Path", "Complete");
                telemetry.update();
                driveTrain.drive(1, 0, 0, 0.5);
                sleep(1000);
            }

            // Stop drivetrain
            driveTrain.stop();
            CatapultAlina.catapultStops();
        }
    }
