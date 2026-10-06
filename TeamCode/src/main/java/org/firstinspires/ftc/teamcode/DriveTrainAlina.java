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
    //Acc moving
    //front+back, strife, rotating
    //strife=sideways(left or right)
    public void drive (double forward, double strafe, double rotation){
            double frontLeftPower=forward+strafe+rotation;
            double backLeftPower=forward-strafe+rotation;
            double frontRightPower=forward-strafe-rotation;
            double backRightPower=forward+strafe-rotation;

//restrict the values so their absv is 1
        double max=Math.max(
                Math.max(Math.abs(frontLeftPower),Math.abs(frontRightPower)),
                Math.max(Math.abs(backLeftPower),Math.abs(backRightPower))
        );

        if (max>1.0){
            frontLeftPower /=max;
            backLeftPower /=max;
            frontRightPower /=max;
            backRightPower /=max;
        }

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);


    }

        //field centric now :)

}


