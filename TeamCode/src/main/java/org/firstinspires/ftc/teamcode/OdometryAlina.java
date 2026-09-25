package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//importing linear opmode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp(name = "Odometry Test")
public class OdometryAlina extends LinearOpMode {

    //Declares the Pinpoint
    GoBildaPinpointDriver pinpoint;
    //error because Driver lowkey not connected


    //Basically a variable

    @Override
    public void runOpMode() {

        //Initialize the Pinpoint
        pinpoint = hardwareMap.get(
                GoBildaPinpointDriver.class,
                "odo"
        );

        // Set the physical offsets (offsets tell where the tracking wheels are mounted in relative to the ref. point
        pinpoint.setOffsets(0,0, DistanceUnit.CM);

        // Configure odometry pod setup
        // These values depend actual Pinpoint setup.

        //Set the starting position
        Pose2D startPose = new Pose2D(
                DistanceUnit.CM,
                0,
                0,
                AngleUnit.DEGREES,
                0
        );

        pinpoint.setPosition(startPose);

        telemetry.addLine("Odometry initialized!");
        telemetry.update();

        waitForStart();
        // :(

        //Keep updating and reading the position
        while (opModeIsActive()) {

            pinpoint.update();

            // Get the current pose
            Pose2D pose = pinpoint.getPosition();

            // Get X, Y, and heading
            double x = pose.getX(DistanceUnit.CM);
            double y = pose.getY(DistanceUnit.CM);
            double heading = pose.getHeading(AngleUnit.DEGREES);

            // Display it
            telemetry.addData("X", x);
            telemetry.addData("Y", y);
            telemetry.addData("Heading", heading);

            telemetry.update();
        }
    }
}
