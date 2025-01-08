package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class SingleMotorTest extends LinearOpMode {
    Servo armL, armR;
    Servo LeftVerticalArm, RightVerticalArm, leftIntakeServo, rightIntakeServo;
    DcMotor leftVerticalSlide, rightVerticalSlide;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");
        DcMotor hSlide = hardwareMap.dcMotor.get("hSlide");
        CRServo intakeServo = hardwareMap.get(CRServo.class, "intake");
        DcMotor leftVerticalSlide = hardwareMap.dcMotor.get("LVslide");
        DcMotor rightVerticalSlide = hardwareMap.dcMotor.get("RVslide");
        leftIntakeServo = hardwareMap.get(Servo.class, "leftIntakeServo");
        rightIntakeServo = hardwareMap.get(Servo.class,"rightIntakeServo");

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.cross) {
                frontLeftMotor.setPower(1);
            } else if(gamepad1.circle) {
                frontLeftMotor.setPower(-1);
            } else {
                frontLeftMotor.setPower(0);
            }

            //second motor
            if(gamepad1.square) {
                frontRightMotor.setPower(1);
            } else if(gamepad1.triangle) {
                frontRightMotor.setPower(-1);
            } else {
                frontRightMotor.setPower(0);
            }

            //third motor
            if(gamepad1.left_bumper) {
                backLeftMotor.setPower(1);
            } else if(gamepad1.right_bumper) {
                backLeftMotor.setPower(-1);
            } else {
                backLeftMotor.setPower(0);
            }

            //fourth motor
            if(gamepad2.cross) {
                backRightMotor.setPower(1);
            } else if(gamepad2.circle) {
                backRightMotor.setPower(-1);
            } else {
                backRightMotor.setPower(0);
            }
        }
    }
}
