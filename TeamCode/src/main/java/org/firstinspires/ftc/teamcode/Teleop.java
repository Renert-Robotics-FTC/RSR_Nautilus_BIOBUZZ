package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name = "Teleop")

public class Teleop extends LinearOpMode {

    private DriveTrainAlina driveTrain;
    private Telemetry telemetry;

    private boolean previousOptions = false;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrainAlina(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {
            double speed = gamepad1.left_bumper ? 0.4 : 1.0;


            double forward = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotation = gamepad1.right_stick_x;

            forward=driveTrain.applyDeadband(forward);
            strafe=driveTrain.applyDeadband(strafe);
            rotation=driveTrain.applyDeadband(rotation);


            driveTrain.drive(forward, strafe, rotation, speed);

            telemetry.addData("Forward",forward);
            telemetry.addData("Strafe",strafe);
            telemetry.addData("Rotation",rotation);
            telemetry.addData("Speed",speed);
            telemetry.update();


            if (gamepad1.options && previousOptions) {
                driveTrain.resetHeading();
            }
            previousOptions=gamepad1.options;
            //making it trigger while pressed

            driveTrain.stop();

        }
    }
}
