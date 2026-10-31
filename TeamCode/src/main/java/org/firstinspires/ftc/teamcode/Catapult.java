package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Catapult {
    private static DcMotor catapult1;
    private static DcMotor catapult2;
    public Catapult(HardwareMap hardwareMap) {
        catapult1 = hardwareMap.get(DcMotor.class, "catapult1");
        catapult2 = hardwareMap.get(DcMotor.class, "catapult2");
        catapult1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        catapult1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        catapult2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        catapult2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public static void catapultshoot() {
        catapult1.setPower(1);
        catapult2.setPower(-1);
    }
    public static void catapultretract() {
        catapult1.setPower(-1);
        catapult2.setPower(1);

    }
    public static void setpower0() {
        catapult1.setPower(0);
        catapult2.setPower(0);
    }
}
