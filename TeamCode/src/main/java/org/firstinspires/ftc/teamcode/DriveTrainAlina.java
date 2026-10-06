package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class DriveTrainAlina{
        //initializing!!
        //the drive motors first
        private DcMotorEx frontLeft;
        private DcMotorEx frontRight;
        private DcMotorEx backLeft;
        private DcMotorEx backRight;

        //constructing bc thats the order
        public DriveTrainAlina(HardwareMap hardwareMap) {
            frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
            frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
            backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
            backRight = hardwareMap.get(DcMotorEx.class, "backRight");

            //direction initializing
            frontLeft.setDirection(DcMotorEx.Direction.FORWARD);
            backLeft.setDirection(DcMotorEx.Direction.FORWARD);
            frontRight.setDirection(DcMotorEx.Direction.REVERSE);
            backRight.setDirection(DcMotorEx.Direction.REVERSE);

            //breaks
            frontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);


        }
        //field centric now :)

}


