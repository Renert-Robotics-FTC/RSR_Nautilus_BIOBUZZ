package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name = "Teleop")

public class Teleop extends LinearOpMode {

    private DriveTrainAlina driveTrain;
    private Telemetry telemetry;
    private boolean previousA = false;
    private boolean gyro = false;

    private boolean previousOptions = false;
    public double speed = 1;
    public double slowspeed = 0.5;
    public double fastspeed = 1;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrainAlina(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {
            speed = gamepad1.left_bumper ? slowspeed : fastspeed;


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
            if (speed == slowspeed) {
                telemetry.addLine("slow mode enabled");
            } else if (speed == fastspeed){
                telemetry.addLine("fast mode enabled");
            }
            telemetry.update();


            if (gamepad1.a && !previousA) {
                driveTrain.resetHeading();
            }
            previousA = gamepad1.a;
            driveTrain.stop();

        }
    }
}
