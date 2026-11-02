package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeAlina {
    //make sure motors are positive for intake and negative for spitting out!
    private DcMotorEx intake;

    private Telemetry telemetry;
    public IntakeAlina(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry=telemetry;

        intake = hardwareMap.get(DcMotorEx.class, "intake");

        //construcors on top

        //direction initializing
        intake.setDirection(DcMotorEx.Direction.FORWARD);


        //breaks
       intake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    

        //deadband=NPV non permissible values in code


    }
    public void inTake;


}
