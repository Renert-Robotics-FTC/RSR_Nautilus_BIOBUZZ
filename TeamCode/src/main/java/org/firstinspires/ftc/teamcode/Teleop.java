
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Teleop")

public class Teleop extends LinearOpMode {

    private DriveTrainAlina driveTrain;
    private CatapultAlina catapult;

    private boolean previousA = false;
    private boolean previousCatapultA = false;

    private boolean gyro = false;

    private boolean previousOptions = false;

    public double speed = 1;
    public double slowspeed = 0.5;
    public double fastspeed = 1;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrainAlina(hardwareMap, telemetry);
        catapult = new CatapultAlina(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            // DRIVETRAIN

            speed = gamepad1.left_bumper ? slowspeed : fastspeed;

            double forward = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotation = gamepad1.right_stick_x;

            forward = driveTrain.applyDeadband(forward);
            strafe = driveTrain.applyDeadband(strafe);
            rotation = driveTrain.applyDeadband(rotation);

            driveTrain.drive(forward, strafe, rotation, speed);

            // HEADING RESET

            if (gamepad1.a && !previousA) {
                driveTrain.resetHeading();
            }

            previousA = gamepad1.a;

            // CATAPULT

            catapult.update();

            if (gamepad2.a && !previousCatapultA) {
                catapult.startingShooting();
            }

            previousCatapultA = gamepad2.a;

            // TELEMETRY

            telemetry.addData("Forward", forward);
            telemetry.addData("Strafe", strafe);
            telemetry.addData("Rotation", rotation);

            telemetry.addData("Catapult", "Control interface connected");

            if (speed == slowspeed) {
                telemetry.addLine("slow mode enabled");
            } else {
                telemetry.addLine("fast mode enabled");
            }

            telemetry.update();
        }

        driveTrain.stop();
    }
}



