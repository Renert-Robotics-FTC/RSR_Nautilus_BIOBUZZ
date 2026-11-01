
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "FIRST TeleOp", group = "Main")
public class teleop extends OpMode {
    public Catapult cappy;
    public Drivetrain drivey;
    public Intake intakky;
    @Override
    public void init() {
        cappy = new Catapult(hardwareMap);
        intakky = new Intake(hardwareMap);
        drivey = new Drivetrain(hardwareMap, telemetry);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            cappy.catapultshoot();
            telemetry.addData("Power", 1);

        } else if (gamepad1.b) {
            cappy.catapultretract();
            telemetry.addData("Power", -1);

        } else {
            cappy.setpower0();
            telemetry.addData("Power", 0);
        }
        if (gamepad1.x) {
            intakky.Intakeforward();
        } else if (gamepad1.y) {
            intakky.Intakebackward();
        }else {
            intakky.Intakestop();
        }
        drivey.drive(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, 1);

        telemetry.update();
    }
}