
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoAlina")
public class AutoAlina extends LinearOpMode {

    @Override
    public void runOpMode() {

        // Initialize
        CatapultAlina catapult = new CatapultAlina(hardwareMap);
        DriveTrainAlina driveTrain = new DriveTrainAlina(hardwareMap, telemetry);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for start
        waitForStart();

        if (!opModeIsActive()) return;

        // shoot
        catapult.catapultShooting();
        sleep(1250);

        // drive forward
        if (opModeIsActive()) {
            driveTrain.drive(1, 0, 0, 0.5);
            sleep(2000);
        }

        // stop drivetrain
        driveTrain.stop();

        // Stop catapult
        catapult.catapultStops();
    }
}
