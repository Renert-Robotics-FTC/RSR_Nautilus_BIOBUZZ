
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeFlower {

    private Servo Servo1;
    private Servo Servo2;

    public IntakeFlower(HardwareMap hardwareMap) {
        Servo1 = hardwareMap.get(Servo.class, "Servo1");
        Servo2 = hardwareMap.get(Servo.class, "Servo2");


    }

    public void Open() {
        Servo1.setPosition(0.75);
        Servo2.setPosition(0.25);
    }

    public void Close(){
        Servo1.setPosition(0.25);
        Servo2.setPosition(0.75);
    }

    public double getServo1Position(){
        return Servo1.getPosition();
    }

    public double getServo2Position(){
        return Servo2.getPosition();
    }

}