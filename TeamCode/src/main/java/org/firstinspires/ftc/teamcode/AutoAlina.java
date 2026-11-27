package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutoAlina")
public class AutoAlina extends LinearOpMode {

    @Override
    public void runOpMode() {

        // Initializing
        CatapultAlina catapult = new CatapultAlina(hardwareMap);
        DriveTrainAlina driveTrain = new DriveTrainAlina(hardwareMap, telemetry);

        // Declare hardware objects
        DcMotor leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        DcMotor rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        ElapsedTime runtime = new ElapsedTime();

        // Set motor directions
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for start
        waitForStart();

        if (!opModeIsActive()) return;

        // 1. SHOOT
        CatapultAlina.catapultShooting();
        sleep(1250);

        // 2. DRIVE FORWARD
        runtime.reset();

        leftDrive.setPower(0.5);
        rightDrive.setPower(0.5);

        while (opModeIsActive() && runtime.seconds() < 2.0) {
            telemetry.addData("Path", "Driving Forward: %2.5f S", runtime.seconds());
            telemetry.update();
        }

        // Stop after driving forward
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        // 3. ROTATE RIGHT
        leftDrive.setPower(0.5);
        rightDrive.setPower(-0.5);

        sleep(400); // need to test this cuz idk how far it goes

        // Stop after turning
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        // 4. ADDITIONAL DRIVE
        if (opModeIsActive()) {
            driveTrain.drive(1, 0, 0, 0.5);
            sleep(1000);
        }

        // Stop drivetrain
        driveTrain.stop();
        CatapultAlina.catapultStops();
    }
}
