package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Teleop")

public class Teleop extends LinearOpMode {

    private DriveTrainAlina driveTrain;
    private CatapultAlina catapult;
    private IntakeAlina intake;
    private Odometry odometry;

    private IntakeFlower intakeFlower;

    private double speed = 1;
    private double slowspeed = 0.5;
    private double fastspeed = 1;

    private boolean shoot = false;

    private boolean isOpen = false;
    private boolean previousB = false;

    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrainAlina(hardwareMap, telemetry);
        catapult = new CatapultAlina(hardwareMap);
        intake = new IntakeAlina(hardwareMap);
        intakeFlower=new IntakeFlower(hardwareMap);

        // Initialize Pinpoint
        odometry = new Odometry();
        odometry.initializeOdometry(hardwareMap);

        telemetry.addLine("Pinpoint initialized!");
        telemetry.update();

        waitForStart();

        timer.reset();

        while (opModeIsActive()) {

            // ODOMETRY
            odometry.updateOdometry();

            double x = odometry.getxPos();
            double y = odometry.getyPos();
            double heading = odometry.getHeading();

            // DRIVETRAIN
            speed = gamepad1.left_bumper ? slowspeed : fastspeed;

            double forward = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotation = gamepad1.right_stick_x;

            driveTrain.drive(
                    forward,
                    strafe,
                    rotation,
                    heading
            );

            // CATAPULT
            if (gamepad1.right_trigger > 0.3) {
                timer.reset();
                shoot = true;
            }

            if (shoot) {
                if (timer.seconds() < 0.1) {
                    catapult.catapultShooting();
                } else if (timer.seconds() > 0.6
                        && timer.seconds() < 1.3) {
                    catapult.catapultRetracting();
                } else {
                    catapult.catapultStops();
                }
            }

            // INTAKE
            if (gamepad1.left_trigger > 0.3) {
                intake.forward();
            } else if (gamepad1.left_bumper) {
                intake.backwards();
            } else {
                intake.stop();
            }

            // INTAKE FLOWER TOGGLE
            if (gamepad1.b && !previousB) {

                isOpen = !isOpen;

                if (isOpen) {
                    intakeFlower.Open();
                } else {
                    intakeFlower.Close();
                }
            }

            telemetry.addData("Servo 1 Position",
                    intakeFlower.getServo1Position());

            telemetry.addData("Servo 2 Position",
                    intakeFlower.getServo2Position());

            previousB = gamepad1.b;

            // TELEMETRY
            telemetry.addData("X Position (mm)", x);
            telemetry.addData("Y Position (mm)", y);
            telemetry.addData("Heading (degrees)", heading);

            telemetry.addData("Forward", forward);
            telemetry.addData("Strafe", strafe);
            telemetry.addData("Rotation", rotation);

            telemetry.addData("Catapult", "Control interface connected");

            telemetry.addLine(
                    speed == slowspeed
                            ? "Slow mode enabled"
                            : "Fast mode enabled"
            );

            telemetry.update();
        }

        driveTrain.stop();
    }

}