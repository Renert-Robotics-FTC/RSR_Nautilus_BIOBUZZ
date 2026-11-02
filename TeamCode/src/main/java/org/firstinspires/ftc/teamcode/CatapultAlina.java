package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CatapultAlina {
    private static DcMotor catapultMotor1;
    private static DcMotor catapultMotor2;

    //Constructors now yay

    public CatapultAlina (HardwareMap hardwareMap){
        catapultMotor1=hardwareMap.get(DcMotor.class, "catapultMotor1");
        catapultMotor2=hardwareMap.get(DcMotor.class, "catapultMotor2");
        catapultMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        catapultMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        catapultMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        catapultMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    //Methods so it shoots kaboom kabbam yay

    public static void catapultShooting(){
        catapultMotor1.setPower(1);
        catapultMotor2.setPower(-1);
    }

    public static void catapultRetracting(){
        catapultMotor1.setPower(-1);
        catapultMotor2.setPower(1);
    }

    public static void catapultStops(){
        catapultMotor1.setPower(0);
        catapultMotor2.setPower(0);
    }
}

