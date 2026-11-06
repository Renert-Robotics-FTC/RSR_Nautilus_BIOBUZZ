package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


public class DriveTrainAlina {

    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;
    private Telemetry telemetry;

    private IMU imu;

    public double applyDeadband(double value) {
        if (Math.abs(value) < 0.05) {
            return 0;
        }

        return value;
    }
    public DriveTrainAlina(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        imu = hardwareMap.get(IMU.class, "imu");

        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);
        frontRight.setDirection(DcMotorEx.Direction.FORWARD);
        backRight.setDirection(DcMotorEx.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        imu.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(
                                new Quaternion(
                                        0.130526f, // w
                                        0.0f,      // x
                                        0.991445f, // y
                                        0.0f,      // z
                                        0           // acquisitionTime
                                )
                        )
                )
        );
    }
    public void drive(double forward, double strafe, double rotation, double speed){

        forward=applyDeadband(forward);
        strafe=applyDeadband(strafe);
        rotation=applyDeadband(rotation);

        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        double yaw = 0;

        double robotForward=
                forward*Math.cos(yaw)
                        +strafe*Math.sin(yaw);
        double robotStrafe=
                -forward*Math.sin(yaw)
                        +strafe*Math.cos(yaw);

        double frontLeftPower=robotForward+robotStrafe+rotation;
        double backLeftPower=robotForward-robotStrafe+rotation;
        double frontRightPower=robotForward-robotStrafe-rotation;
        double backRightPower=robotForward+robotStrafe-rotation;

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
        frontLeftPower *=speed;
        frontRightPower *=speed;
        backLeftPower *=speed;
        backRightPower *=speed;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

        telemetry.addData("Heading",yaw);
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
}


