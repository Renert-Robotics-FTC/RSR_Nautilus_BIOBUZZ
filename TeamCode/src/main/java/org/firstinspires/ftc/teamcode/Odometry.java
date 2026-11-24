
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Odometry {

    private GoBildaPinpointDriver odometry;

    public void initializeOdometry(HardwareMap hardwareMap) {

        odometry = hardwareMap.get(
                GoBildaPinpointDriver.class,
                "odo"
        );

        // Physical offsets
        odometry.setOffsets(-120.0, -30.0, DistanceUnit.MM);

        // Configure odometry pods
        odometry.setEncoderResolution(
                GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD
        );

        // Reset starting position and Pinpoint IMU
        odometry.resetPosAndIMU();
    }

    public void updateOdometry() {
        odometry.update();
    }

    public double getxPos() {
        return odometry.getPosX(DistanceUnit.MM);
    }

    public double getyPos() {
        return odometry.getPosY(DistanceUnit.MM);
    }

    public double getHeading() {
        return odometry.getHeading(AngleUnit.DEGREES);
    }
}
