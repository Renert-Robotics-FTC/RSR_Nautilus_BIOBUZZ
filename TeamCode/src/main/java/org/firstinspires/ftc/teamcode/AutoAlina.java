package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name= "AutoAlina")
public class AutoAlina extends LinearOpMode {
    @Override
    public void runOpMode(){
        //initializing yay
        CatapultAlina catapult = new CatapultAlina (hardwareMap);
        DriveTrainAlina driveTrain = new DriveTrainAlina(hardwareMap, telemetry);

        telemetry.addLine("Ready");

        telemetry.update();

        //wait for start

        waitForStart();

        if (!opModeIsActive()) return;

        // 1. SHOOT
        CatapultAlina.catapultShooting();
        sleep(1250);

        // 2. DRIVE FORWARD
        if (opModeIsActive()) {
            driveTrain.drive(1, 0, 0, 0.5);
            sleep(1000);
        }

        // Stop drivetrain
        driveTrain.stop();
        CatapultAlina.catapultStops();
    }
}


