package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class CatapultAlina {
    private static DcMotor catapult1;
    private static DcMotor catapult2;
    private ElapsedTime timer=new ElapsedTime();

    private enum state {
        IDLE,
        SHOOTING,

    }

    private state currentState=state.IDLE;
    private static final double shootTime=1.25;


    //Constructors now yay

    public CatapultAlina (HardwareMap hardwareMap){
        catapult1=hardwareMap.get(DcMotor.class, "catapult1");
        catapult2=hardwareMap.get(DcMotor.class, "catapult2");
        catapult1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        catapult1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        catapult2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        catapult2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    //Methods so it shoots kaboom kabbam yay

    public static void catapultShooting(){
        catapult1.setPower(-1);
        catapult2.setPower(1);
    }

    public static void catapultRetracting(){
        catapult1.setPower(1);
        catapult2.setPower(-1);
    }

    public static void catapultStops(){
        catapult1.setPower(0);
        catapult2.setPower(0);
    }



}

