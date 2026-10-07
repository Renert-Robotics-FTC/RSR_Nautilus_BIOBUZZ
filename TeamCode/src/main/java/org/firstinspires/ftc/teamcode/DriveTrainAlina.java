package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.Telemetry;





public class DriveTrainAlina{

    private double headingOffset =0;

    public DriveTrainAlina(HardwareMap hardwareMap) {
    }

    public void resetHeading() {
        headingOffset =
                pinpoint.getPosition().getHeading(AngleUnit.RADIANS);
    }

        //initializing!!
        //the drive motors first
        private DcMotorEx frontLeft;
        private DcMotorEx frontRight;
        private DcMotorEx backLeft;
        private DcMotorEx backRight;

        private GoBildaPinpointDriver pinpoint;
        private Telemetry telemetry;



    public double applyDeadband(double value) {
        if (Math.abs(value) < 0.05) {
            return 0;
        }

        return value;
    }

        //constructing bc thats the order
        public DriveTrainAlina(HardwareMap hardwareMap, Telemetry telemetry){
            this.telemetry=telemetry;

            frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
            frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
            backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
            backRight = hardwareMap.get(DcMotorEx.class, "backRight");
            pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "odo");


            //construcors on top

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

            //deadband=NPV non permissable values in code


        }
    //Acc moving
    //front+back, strife, rotating
    //strife=sideways(left or right)



    public void drive (double forward, double strafe, double rotation, double speed){

        forward=applyDeadband(forward);
        strafe=applyDeadband(strafe);
        rotation=applyDeadband(rotation);

            pinpoint.update();
            double heading =pinpoint.getPosition().getHeading(AngleUnit.RADIANS)-headingOffset;


        double robotForward=
                forward*Math.cos(heading)
                        +strafe*Math.sin(heading);
        double robotStrafe=
                -forward*Math.sin(heading)
                        +strafe*Math.cos(heading);

            double frontLeftPower=robotForward+robotStrafe+rotation;
            double backLeftPower=robotForward-robotStrafe+rotation;
            double frontRightPower=robotForward-robotStrafe-rotation;
            double backRightPower=robotForward+robotStrafe-rotation;

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
//control some speed so it doesn't go vroommmmm
        frontLeftPower *=speed;
        frontRightPower *=speed;
        backLeftPower *=speed;
        backRightPower *=speed;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

        telemetry.addData("Heading",heading);
        telemetry.addData("FL Power", frontLeftPower);
        telemetry.addData("BL Power", backLeftPower);
        telemetry.addData("FR Power", frontRightPower);
        telemetry.addData("BR Power", backRightPower);
        telemetry.update();

        }

public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }

        //field centric now :)

}


