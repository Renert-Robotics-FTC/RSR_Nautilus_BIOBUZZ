package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
public class Odometry {
    private GoBildaPinpointDriver odometry;

    public void initializeOdometry(HardwareMap hardwareMap){
        odometry = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
        odometry.setOffsets(-120.0, -30.0, DistanceUnit.MM);
        odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odometry.resetPosAndIMU();
    }

    public void updateOdometry(){
        odometry.update(); //updates data
    }

    //methods for other classes to access odometry data
    public double getxPos(){
        double xpos = odometry.getPosX(DistanceUnit.MM);
        return xpos;
    }
    public double getyPos(){
        double ypos = odometry.getPosY(DistanceUnit.MM);
        return ypos;
    }
    public double getHeading(){
        double heading = odometry.getHeading(AngleUnit.DEGREES);
        return heading;
    }
}