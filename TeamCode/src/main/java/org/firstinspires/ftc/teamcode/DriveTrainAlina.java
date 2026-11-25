
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveTrainAlina {

    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;

    private Telemetry telemetry;

    public double applyDeadband(double value) {
        if (Math.abs(value) < 0.05) {
            return 0;
        }
        return value;
    }

    public DriveTrainAlina(HardwareMap hardwareMap, Telemetry telemetry) {

        this.telemetry = telemetry;

        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);

        frontRight.setDirection(DcMotorEx.Direction.FORWARD);
        backRight.setDirection(DcMotorEx.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void drive(double forward, double strafe,
                      double rotation, double heading, double speed) {

        forward = -applyDeadband(forward);
        strafe = applyDeadband(strafe);
        rotation = applyDeadband(rotation);

        // Convert Pinpoint heading to radians
        double yaw = Math.toRadians(heading);

        // Field-centric transformation
        double robotForward =
                forward * Math.cos(yaw) + strafe * Math.sin(yaw);

        double robotStrafe =
                -forward * Math.sin(yaw) + strafe * Math.cos(yaw);

        // Mecanum calculations
        double frontLeftPower = robotForward + robotStrafe + rotation;
        double backLeftPower = robotForward - robotStrafe + rotation;
        double frontRightPower = robotForward - robotStrafe - rotation;
        double backRightPower = robotForward + robotStrafe - rotation;

        // Normalize
        double max = Math.max(
                Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower)),
                Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))
        );

        if (max > 1.0) {
            frontLeftPower /= max;
            backLeftPower /= max;
            frontRightPower /= max;
            backRightPower /= max;
        }

        // Apply speed
        frontLeft.setPower(frontLeftPower * speed);
        backLeft.setPower(backLeftPower * speed);
        frontRight.setPower(frontRightPower * speed);
        backRight.setPower(backRightPower * speed);

        telemetry.addData("Heading", heading);
    }

    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
