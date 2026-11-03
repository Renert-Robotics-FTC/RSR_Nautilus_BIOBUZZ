
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Teleop")

public class Teleop extends LinearOpMode {

    private DriveTrainAlina driveTrain;
    private CatapultAlina catapult;

    private IntakeAlina intake;


    private boolean previousA = false;
    private boolean previousCatapultA = false;

    private boolean gyro = false;

    private boolean previousOptions = false;

    public double speed = 1;
    public double slowspeed = 0.5;
    public double fastspeed = 1;
    public boolean shoot = false;

    public boolean instake = false;

    private ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() {

        driveTrain = new DriveTrainAlina(hardwareMap, telemetry);
        catapult = new CatapultAlina(hardwareMap);
        intake = new IntakeAlina(hardwareMap);

        waitForStart();
        timer.reset();
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

            if (gamepad1.right_trigger > 0.3) {
                timer.reset();
                shoot = true;
            }
            if (shoot) {
                if (timer.seconds() < 0.1) {
                    catapult.catapultShooting();
                } else if (timer.seconds() > 0.6 && timer.seconds() < 1.3) {
                    catapult.catapultRetracting();
                } else {
                    catapult.catapultStops();
                }
            }

            //INTAKE

            if (gamepad1.left_trigger > 0.3) {
                intake.forward();
            } else if (gamepad1.left_bumper){
                intake.backwards();
            } else {
                intake.stop();
            }
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



