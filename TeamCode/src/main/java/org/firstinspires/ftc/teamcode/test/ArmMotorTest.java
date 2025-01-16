package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(group = "tests")
public class ArmMotorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor rightVerticalSlide = hardwareMap.dcMotor.get("RVslide");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("rvs pos", rightVerticalSlide.getCurrentPosition());
            telemetry.update();
        }
    }
}
