package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class HighBasAuto extends LinearOpMode {
//    DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor, rightVerticalSlide, leftVerticalSlide;
    Servo leftIntakeServo, rightIntakeServo, LeftVerticalArm, RightVerticalArm, clawServo;

//    public void set(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
//        frontLeftMotor.setPower(frontLeftPower);
//        backLeftMotor.setPower(backLeftPower);
//        frontRightMotor.setPower(frontRightPower);
//        backRightMotor.setPower(backRightPower);
//    }
    public void setArm(double RightVerticalPosition, double LeftVerticalPosition) {
        LeftVerticalArm.setPosition(LeftVerticalPosition);
        RightVerticalArm.setPosition(RightVerticalPosition);
    }
    /*public void setVerticalSlide(double rightVerticalSlidePower, double leftVerticalSlidePower) {
        leftVerticalSlide.setPower(leftVerticalSlidePower);
        rightVerticalSlide.setPower(rightVerticalSlidePower);
    }
     */
    public void setClawServo(double clawPosition) {
        clawServo.setPosition(clawPosition);
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
        double p = 0.55;
        long ms = 1800;

        //claw hold sample
        ms = 1000;
        setClawServo(0.8);
        sleep(ms);

        //left
//        set(-p, p, p, -p);
        p = 0.55;
        ms = 1800;
        frontLeftMotor.setPower(-p);
        backLeftMotor.setPower(p);
        frontRightMotor.setPower(p);
        backRightMotor.setPower(-p);
        sleep(ms);

        // forward
        p = 0.1;
        ms = (1000);
//        set(p, p, p, p);
        frontLeftMotor.setPower(p);
        backLeftMotor.setPower(p);
        frontRightMotor.setPower(p);
        backRightMotor.setPower(p);
        sleep(ms);

        //claw hold sample
        ms = 1000;
        setClawServo(0.8);
        sleep(ms);

        //rotation
        p = 0.32;
        ms = (1000);
//        set(p, -p, p, -p);
        //code for drive
        frontLeftMotor.setPower(p);
        backLeftMotor.setPower(p);
        frontRightMotor.setPower(-p);
        backRightMotor.setPower(-p);
        sleep(ms);

        //backward
        p = 0.075;
        ms = (1000);

        frontLeftMotor.setPower(-p);
        backLeftMotor.setPower(-p);
        frontRightMotor.setPower(-p);
        backRightMotor.setPower(-p);
        sleep(ms);



        //return
//        ms = (1400);
//        set(p, p, p, p);
//        sleep(ms);

        //back
//        p = 0.4;
//        ms = (1200);
//        set(-p, -p, -p, -p);
//        sleep(ms);
//
//        set(-p, -p, -p, -p);
//        sleep(ms);

//        set(0, 0, 0, 0);
//         vSlide limit(setting)
        int minPosition = 0; // Minimum position (fully retracted)
        int maxPosition = 1500; // Maximum position (fully extended)
        int currentPosition;
        rightVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightVerticalSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //vertical Slide maximum movemnet
        currentPosition = rightVerticalSlide.getCurrentPosition();
        if (currentPosition <= minPosition) {
            rightVerticalSlide.setPower(0);
            leftVerticalSlide.setPower(0);
        } else if (currentPosition >= maxPosition) {
            rightVerticalSlide.setPower(0);
            leftVerticalSlide.setPower(0);
        } else {
            rightVerticalSlide.setPower(0.01);
            leftVerticalSlide.setPower(0.01);
        }

        ms = 1000;

        //claaw close
        setClawServo(0.8);
        sleep(ms);


        //vertical slide move

        p = 0.4;
        rightVerticalSlide.setPower(p);
        leftVerticalSlide.setPower(-p);
        sleep(2500);

        //arm up hehehehe
        ms = 1000;
        setArm(0.4, 0.6);
        sleep(ms);
        setArm(0.2, 0.8);
        sleep(ms);
        setArm(0,1);
        sleep(ms);


        //Dropping sample(claw)
        setClawServo(0);
        sleep(ms);

        setArm(0.6, 0.4);
        sleep(ms);
        setArm(0.80, 0.20);
        sleep(ms);

        //starting position intake
        leftIntakeServo.setPosition(0.77);
        rightIntakeServo.setPosition(0.33);
        sleep(ms);

        p = 0.4;
        rightVerticalSlide.setPower(-p);
        leftVerticalSlide.setPower(p);
        sleep(ms);


        //arm down
        /*setArm(0.4, 0.6);
        ms = 3000;
        sleep(ms);
        setArm(0.2, 0.8);
        sleep(ms);
        setArm(0, 1);

         */

    }
}
