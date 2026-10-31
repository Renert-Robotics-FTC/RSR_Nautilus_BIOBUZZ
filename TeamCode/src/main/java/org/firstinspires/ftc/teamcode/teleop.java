
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "FIRST TeleOp", group = "Main")
public class teleop extends OpMode {
    public Catapult cappy;
    @Override
    public void init() {
        cappy = new Catapult(hardwareMap);
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

        telemetry.update();
    }
}