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
//    DcMotor leftVerticalSlide, rightVerticalSlide;

    // POSITION = 0: pickup position, 1: up position
//    public void setServo(double positionL, double positionR) {
//        leftIntakeServo.setPosition(positionL);
//        rightIntakeServo.setPosition(positionR);
//    }
//    public void setVServo (double positionVS) {
//        clawServo.setPosition(positionVS);
//    }
//    public void setAxon (double armPosition) {
//        arm.setPosition(armPosition);
//    }
//    /*public void setV (double positionV) {
//        leftVerticalSlide.setPower(positionV);
//        rightVerticalSlide.setPower(positionV);
//    }
//
//     */



    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");
        DcMotor hSlide = hardwareMap.dcMotor.get("hSlide");
//        CRServo intakeServo = hardwareMap.get(CRServo.class, "intake");
//        DcMotor leftVerticalSlide = hardwareMap.dcMotor.get("LVslide");
//        DcMotor rightVerticalSlide = hardwareMap.dcMotor.get("RVslide");
//        leftIntakeServo = hardwareMap.get(Servo.class, "leftIntakeServo");
//        rightIntakeServo = hardwareMap.get(Servo.class,"rightIntakeServo");
//        clawServo = hardwareMap.get(Servo.class,"claw");
//        arm = hardwareMap.get(Servo.class, "arm");


        // hSlide limit(setting)
        int minPosition = 0; // Minimum position (fully retracted)
        int maxPosition = 1500; // Maximum position (fully extended)
        int currentPosition;
        hSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);




        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);



        waitForStart();

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
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



            //Movement hSlide
//            currentPosition = hSlide.getCurrentPosition();
//
//            if (currentPosition <= minPosition) {
//                hSlide.setPower(0);
//            } else if (currentPosition >= maxPosition) {
//                hSlide.setPower(0);
//            } else {
//                // Boolean stuff(horizontal slide)
//                hSlide.setPower(-gamepad2.right_stick_y / 2.0);
//            }
            if(gamepad2.circle) {
                hSlide.setPower(0.5);
            }
            if(gamepad2.cross) {
                hSlide.setPower(-0.5);
            }
            else {
                hSlide.setPower(0);
            }



            // intake code
//            if (gamepad2.triangle) {
//                intakeServo.setPower(1);
//            }
//            else if (gamepad2.square) {
//                intakeServo.setPower(-1);
//            }
//            else {
//                intakeServo.setPower(0);
//            }



//            if (gamepad2.circle) {
//                // go to pickup position
//                /*leftIntakeServo.setPosition(0);
//                rightIntakeServo.setPosition(1);
//
//
//                 */
//                setServo(0,0.23);
//            }
//            if (gamepad2.cross) {
//                // go to up position
//                /*leftIntakeServo.setPosition(1);
//                rightIntakeServo.setPosition(0);
//
//                 */
//                setServo(0.8,0);
//                //setServo(0.55,0);
//            }
//
//
//
//            if (gamepad1.left_bumper) {
//                // vertical slide up
//                leftVerticalSlide.setPower(0.5);
//                rightVerticalSlide.setPower(-0.5);
//            }
//            else if (gamepad1.right_bumper) {
//                // vertical slide down
//                leftVerticalSlide.setPower(-0.5);
//                rightVerticalSlide.setPower(0.5);
//            }
//
//
//
//            if (gamepad2.left_stick_button) {
//                setVServo(1);
//            }
//            else if (gamepad2.right_stick_button) {
//                setVServo(0);
//            }
//            if(gamepad2.left_bumper) {
//                setAxon(-1);
//            }
//            else if (gamepad2.right_bumper) {
//                setAxon(0);
//            }


        }
    }

}
