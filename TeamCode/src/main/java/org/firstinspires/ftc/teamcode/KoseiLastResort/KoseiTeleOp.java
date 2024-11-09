package org.firstinspires.ftc.teamcode.KoseiLastResort;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class KoseiTeleOp extends LinearOpMode {
    Servo leftServo, rightServo;

    // POSITION = 0: pickup position, 1: up position
    public void setServo(double position) {
        leftServo.setPosition(position);
        rightServo.setPosition(1 - position);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");
        DcMotor hSlide = hardwareMap.dcMotor.get("hSlide");
        CRServo intakeServo = hardwareMap.get(CRServo.class, "intake");
        leftServo = hardwareMap.get(Servo.class, "leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");


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
            double rx = gamepad1.right_trigger - gamepad1.left_trigger;

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

            // Boolean stuff(horizontal slide)
            hSlide.setPower(-gamepad2.right_stick_y / 2.0);

            // intake code
            if (gamepad2.triangle) {
                intakeServo.setPower(0.5);
            }
            else if (gamepad2.square) {
                intakeServo.setPower(-0.5);
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
        }
    }
}
