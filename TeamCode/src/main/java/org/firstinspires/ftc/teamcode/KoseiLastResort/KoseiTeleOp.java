package org.firstinspires.ftc.teamcode.KoseiLastResort;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class KoseiTeleOp extends LinearOpMode {
//    Servo leftIntakeServo, rightIntakeServo, clawServo, arm;
    Servo leftIntakeServo, rightIntakeServo, clawServo, LeftVerticalArm, RightVerticalArm;
    DcMotor leftVerticalSlide, rightVerticalSlide;

    // POSITION = 0: pickup position, 1: up position
    public void setIntake(double positionL, double positionR) {
        leftIntakeServo.setPosition(positionL);
        rightIntakeServo.setPosition(positionR);
    }
    public void clawServo(double positionVS) {
        clawServo.setPosition(positionVS);
    }
    public void setArm (double RarmPosition, double LarmPosition) {
        LeftVerticalArm.setPosition(LarmPosition);
        RightVerticalArm.setPosition(RarmPosition);

    }
//    /*public void setV (double positionV) {
//        leftVerticalSlide.setPower(positionV);
//        rightVerticalSlide.setPower(positionV);
//    }

//     */



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


        // hSlide limit(setting)
        telemetry.addData("RV slide pos", rightVerticalSlide.getCurrentPosition());
        telemetry.addData("LV slide pos", leftVerticalSlide.getCurrentPosition());
        telemetry.addData("hSlide", hSlide.getCurrentPosition());

        int minPosition = 20; // Minimum position (fully retracted)
        int maxPosition = 100000; // Maximum position (fully extended)
        int currentPosition;
        hSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftVerticalSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftVerticalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightVerticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightVerticalSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightVerticalSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.update();




        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);



        waitForStart();

        while (opModeIsActive()) {

            double x = -gamepad1.left_stick_x; // Remember, Y stick value is reversed
            double y = -gamepad1.left_stick_y * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;


            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);



            //telemetry
            int HslidecurrentPosition = hSlide.getCurrentPosition();
            telemetry.addData("HSlide pos", HslidecurrentPosition);

            int lvSlideCurrentPostion = leftVerticalSlide.getCurrentPosition();
            telemetry.addData("LV slide pos", lvSlideCurrentPostion);

            int rvSlideCurrentPosition = rightVerticalSlide.getCurrentPosition();
            telemetry.addData("RV slide pos", rvSlideCurrentPosition);

            telemetry.update();

            //Movement hSlide
//            if (HslidecurrentPosition <= maxPosition) {
//                telemetry.update();
//                hSlide.setPower(0);
//            } else if (HslidecurrentPosition >= minPosition) {
//                telemetry.update();
//                hSlide.setPower(0);
//            } else {
//                // Boolean stuff(horizontal slide)
//                hSlide.setPower(-gamepad2.right_stick_y / 2.0);
//            }

// Movement for hSlide
            if (Math.abs(gamepad2.right_stick_y) > 0.1) {  // Add deadzone to avoid jittering
                hSlide.setPower(-gamepad2.right_stick_y / 2.0);  // Adjust the divisor if needed
            } else {
                hSlide.setPower(0);  // Stop when there's no input
            }

//            if (gamepad1.circle) {
//                hSlide.setPower(0.5);
//            } else if (gamepad1.cross) {
//                hSlide.setPower(-0.5);
//            } else {
//               hSlide.setPower(0);
//           }

            // intake code
            if (gamepad2.triangle) {
                intakeServo.setPower(1);
            } else if (gamepad2.square) {
                intakeServo.setPower(-1);
            } else {
                intakeServo.setPower(0);
            }



            if (gamepad2.circle) {
                // go to up position(1)
                //You need to set a value that add up to 1
                setIntake(0.77,0.23);
            }
            if (gamepad2.cross) {
                // go to pick up position(0)
                //You need to set a value that add up to 1
                setIntake(0.44,0.56);
            }


            //Movement VerticalSlide
            currentPosition = rightVerticalSlide.getCurrentPosition();

            if (currentPosition <= minPosition) {
                leftVerticalSlide.setPower(0);
                rightVerticalSlide.setPower(0);
            } else if (currentPosition >= maxPosition) {
                leftVerticalSlide.setPower(0);
                rightVerticalSlide.setPower(0);
            }

            if (gamepad1.left_bumper) {
                // vertical slide up
                leftVerticalSlide.setPower(0.5);
                rightVerticalSlide.setPower(-0.5);
            } else if (gamepad1.right_bumper ) {
                // vertical slide down
                leftVerticalSlide.setPower(-0.5);
                rightVerticalSlide.setPower(0.5);
            } else {
                leftVerticalSlide.setPower(0);
                rightVerticalSlide.setPower(0);
            }


            //claw
            if (gamepad2.left_stick_button) {
                //claw Open
                clawServo(0.6);
            }
            else if (gamepad2.right_stick_button) {

                //claw close
                clawServo(0);
            }

            if(gamepad2.left_bumper) {
                //down position (changed to specimen)
                setArm(0.6, 0.4);
                long ms = 1000;
                sleep(ms);
                setArm(0.83, 0.17);
                //sleep(ms);
                //setArm(1, 0);

            } else if (gamepad2.right_bumper) {
                //up position
                setArm(0.4, 0.6);
                long ms = 1000;
                sleep(ms);
                setArm(0.2, 0.8);
                sleep(ms);
                setArm(0, 1);
            }

        }
    }

}