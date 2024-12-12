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
    Servo leftIntakeServo, rightIntakeServo, verticalServo;

    public void set(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);
    }

    public void setVServo(double positionVS) {
        verticalServo.setPosition(positionVS);
    }
    public void setV(double postionVR, double postionVL) {
        leftVerticalSlide.setPower(postionVR);
        rightVerticalSlide.setPower(postionVL);
    }


    @Override
    public void runOpMode() throws InterruptedException {
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        backRightMotor = hardwareMap.dcMotor.get("backRight");
        DcMotor rightVerticalSlide = hardwareMap.dcMotor.get("RVslide");
        DcMotor leftVerticalSlide = hardwareMap.dcMotor.get("LVslide");
        DcMotor hSlide = hardwareMap.dcMotor.get("hSlide");
        CRServo intakeServo = hardwareMap.get(CRServo.class, "intake");
        leftIntakeServo = hardwareMap.get(Servo.class, "leftIntakeServo");
        rightIntakeServo = hardwareMap.get(Servo.class, "rightIntakeServo");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        double p = 0.5;
        long ms = 1700;

        set(-p, p, p, -p);
        sleep(ms);

        p = 0.2;
        ms = 20;
        set(-p, p, p, -p);
        sleep(ms);

        set(0, 0, 0, 0);

        // vSlide limit(setting)
        int minPosition = 0; // Minimum position (fully retracted)
        int maxPosition = 1500; // Maximum position (fully extended)
        int currentPosition;
        leftVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftVerticalSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightVerticalSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //vertical Slide maximum movemnet
        currentPosition = rightVerticalSlide.getCurrentPosition();
        if (currentPosition <= minPosition) {
            rightVerticalSlide.setPower(0);
            leftVerticalSlide.setPower(0);
        } else if (currentPosition >= maxPosition) {
            rightVerticalSlide.setPower(0);
            leftVerticalSlide.setPower(0);
        }
        else {
            rightVerticalSlide.setPower(0.01);
            leftVerticalSlide.setPower(0.01);
        }
        //vertical slide move
        p = 0.5;

        setV(p,p);
        sleep(ms);

        ms = 2000;
        setVServo(1);
        sleep(ms);
    }
}
