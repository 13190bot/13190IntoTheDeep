package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Drive extends LinearOpMode {
//    Servo leftIntakeServo, rightIntakeServo;
//
//    public void setServo(double positionL, double positionR) {
//        leftIntakeServo.setPosition(positionL);
//        rightIntakeServo.setPosition(positionR);
//    }


    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");
        CRServo intakeServo = hardwareMap.get(CRServo.class, "intake");


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

//            if (gamepad2.triangle) {
//                intakeServo.setPower(1);
//            } else if (gamepad2.square) {
//                intakeServo.setPower(-1);
//            } else {
//                intakeServo.setPower(0);
//            }
//
//
//            if (gamepad2.circle) {
//                // go to pickup position
//                /*leftIntakeServo.setPosition(0);
//                rightIntakeServo.setPosition(1);
//
//                 */
//
//
//                setServo(0, 0.23);
//            }
//
//            if (gamepad2.cross) {
//                // go to up position
//                /*leftIntakeServo.setPosition(1);
//                rightIntakeServo.setPosition(0);
//
//                 */
//
//
//                setServo(0.8, 0);
            }
        }
    }

