package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class SingleMotorTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");


        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                frontLeftMotor.setPower(1);
            }
            if(gamepad1.b) {
                frontLeftMotor.setPower(0);
            }
        }
    }
}
