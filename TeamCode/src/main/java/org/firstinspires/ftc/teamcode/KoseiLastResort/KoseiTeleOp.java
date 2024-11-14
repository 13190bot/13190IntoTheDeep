package org.firstinspires.ftc.teamcode.KoseiLastResort;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class KoseiTeleOp extends LinearOpMode {
    Servo leftServo, verticalServo;

    // POSITION = 0: pickup position, 1: up position
    public void setServo(double position) {
        leftServo.setPosition(position);
    }
    public void setServoV (double positionV) {
        verticalServo.setPosition(positionV);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");
        DcMotor hSlide = hardwareMap.dcMotor.get("hSlide");
        CRServo intakeServo = hardwareMap.get(CRServo.class, "intake");
        DcMotor verticalSlide = hardwareMap.dcMotor.get("Vslide");
        leftServo = hardwareMap.get(Servo.class, "leftServo");

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
            currentPosition = hSlide.getCurrentPosition();

            if (currentPosition <= minPosition) {
                hSlide.setPower(0);
            } else if (currentPosition >= maxPosition) {
                hSlide.setPower(0);
            } else {
                // Boolean stuff(horizontal slide)
                hSlide.setPower(-gamepad2.right_stick_y / 2.0);
            }

            // intake code
            if (gamepad2.triangle) {
                intakeServo.setPower(1);
            }
            else if (gamepad2.square) {
                intakeServo.setPower(-1);
            }
            else {
                intakeServo.setPower(0);
            }


            if (gamepad2.circle) {
                // go to pickup position
//                leftServo.setPosition(0);
//                rightServo.setPosition(1);
                setServo(0);
            }
            if (gamepad2.cross) {
                // go to up position
//                leftServo.setPosition(1);
//                rightServo.setPosition(0);
                setServo(1);
            }
            if (gamepad2.left_bumper) {
                // vertical slide up
                verticalSlide.setPower(5);
            }
            else if (gamepad2.right_bumper) {
                // vertical slide down
                verticalSlide.setPower(-5);
            }
            else {
                verticalSlide.setPower(0.05);
            }
            if (gamepad2.left_stick_button) {
                setServoV(1);
            }
            if (gamepad2.right_stick_button) {
                setServoV(0);
            }
        }
    }
}
