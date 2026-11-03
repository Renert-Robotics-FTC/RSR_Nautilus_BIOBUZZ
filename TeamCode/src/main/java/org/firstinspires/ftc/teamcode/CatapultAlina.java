package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class CatapultAlina {
    private static DcMotor catapultMotor1;
    private static DcMotor catapultMotor2;
    private ElapsedTime timer=new ElapsedTime();

    private enum state {
        IDLE,
        SHOOTING,

    }

    private state currentState=state.IDLE;
    private static final double shootTime=1.25;


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

    //starting a cycle
     public void startingShooting(){
        if (currentState==state.IDLE) {
            timer.reset();
            currentState=state.SHOOTING;
        }
    }

    //uhh update and sync?

    public void update(){
        switch (currentState){
            case IDLE:
            catapultStops();
            break;

            case SHOOTING:
                catapultShooting();
                if (timer.seconds()>=shootTime){
                    catapultStops();
                    currentState=state.IDLE;
                }
                break;
        }
    }


}

