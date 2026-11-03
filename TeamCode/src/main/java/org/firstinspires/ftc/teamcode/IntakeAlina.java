package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeAlina {
    private static DcMotor intake;

    public IntakeAlina(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    public static void forward(){
        intake.setPower(1);
    }

    public static void backwards(){
        intake.setPower(-1);
    }

    public static void stop(){
        intake.setPower(0);
    }
}