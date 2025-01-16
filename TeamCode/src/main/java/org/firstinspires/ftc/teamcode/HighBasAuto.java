package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class HighBasAuto extends LinearOpMode {
    DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, rightVerticalSlide, leftVerticalSlide;
    Servo leftIntakeServo, rightIntakeServo, LeftVerticalArm, RightVerticalArm, clawServo;

    public void setDrive(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);
    }
    public void setArm(double LeftVerticalPosition, double RightVerticalPosition) {
        LeftVerticalArm.setPosition(LeftVerticalPosition);
        RightVerticalArm.setPosition(RightVerticalPosition);
    }
    public void setVerticalSlide(double RightVerticalSlidePower, double LeftVerticalSlidePower) {
        leftVerticalSlide.setPower(LeftVerticalSlidePower);
        rightVerticalSlide.setPower(RightVerticalSlidePower);
    }


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
        clawServo = hardwareMap.get(Servo.class,"claw");
        LeftVerticalArm = hardwareMap.get(Servo.class, "Larm");
        RightVerticalArm = hardwareMap.get(Servo.class, "Rarm");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        double p = 0.45;
        long ms = 1800;

        //left
        setDrive(-p, p, p, -p);
        sleep(ms);

        // forward
        p = 0.4;
        ms = (1200);
        setDrive(p, p, p, p);
        sleep(ms);

        //rotation
        p = 0.45;
        ms = (1850);
        setDrive(p, -p, p, -p);
        sleep(ms);

        //return
        ms = (1400);
        setDrive(p, p, p, p);
        sleep(ms);

        //back
        p = 0.4;
        ms = (1200);
        setDrive(-p, -p, -p, -p);
        sleep(ms);

        setDrive(-p, -p, -p, -p);
        sleep(ms);

        setDrive(0, 0, 0, 0);
        // vSlide limit(setting)
//        int minPosition = 0; // Minimum position (fully retracted)
//        int maxPosition = 1500; // Maximum position (fully extended)
//        int currentPosition;
//        leftVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightVerticalSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        //vertical Slide maximum movemnet
//        currentPosition = rightVerticalSlide.getCurrentPosition();
//        if (currentPosition <= minPosition) {
//            rightVerticalSlide.setPower(0);
//            leftVerticalSlide.setPower(0);
//        } else if (currentPosition >= maxPosition) {
//            rightVerticalSlide.setPower(0);
//            leftVerticalSlide.setPower(0);
//        }
//        else {
//            rightVerticalSlide.setPower(0.01);
//            leftVerticalSlide.setPower(0.01);
//        }


        //vertical slide move
        p = 0.5;

        setVerticalSlide(p,p);
        sleep(ms);

        ms = 2000;
        sleep(ms);
    }
}
