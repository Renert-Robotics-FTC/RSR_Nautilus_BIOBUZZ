package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Basic: Linear Auto", group="Linear OpMode")
public class AutoAlina extends LinearOpMode {

    // Declare hardware objects
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize hardware variables. The strings here must match your robot configuration.
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        // Most robots need the motor on one side to be reversed to drive forward
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // Step 1: Drive forward for 2.0 seconds
        if (opModeIsActive()) {
            // Set motor power to half speed
            leftDrive.setPower(0.5);
            rightDrive.setPower(0.5);

            // Keep moving until 2 seconds have passed
            while (opModeIsActive() && (runtime.seconds() < 2.0)) {
                telemetry.addData("Path", "Driving Forward: %2.5f S", runtime.seconds());
                telemetry.update();
            }

            // Step 2: Stop the robot
            leftDrive.setPower(0.0);
            rightDrive.setPower(0.0);

            telemetry.addData("Path", "Complete");
            telemetry.update();
        }
    }
}







