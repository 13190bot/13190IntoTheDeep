package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class SingleMotorTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor intake = hardwareMap.dcMotor.get("intake");


        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                intake.setPower(1);
            }
            if(gamepad1.b) {
                intake.setPower(0);
            }
        }
    }
}
