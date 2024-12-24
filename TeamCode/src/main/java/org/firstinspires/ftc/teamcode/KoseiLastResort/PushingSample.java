package org.firstinspires.ftc.teamcode.KoseiLastResort;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class PushingSample extends LinearOpMode{
    DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;

    public void set(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        backRightMotor = hardwareMap.dcMotor.get("backRight");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();


        double p = 0.45;
        long ms = 1700;

        //left
//        set(p, -p, -p, p);
//        sleep(ms);
//
//        // forward
//        p = 0.5;
//        ms = (1500);
//        set(p, p, p, p);
//        sleep(ms);

        //rotation
        ms = (1850);
        set(p, -p, p, -p);
        sleep(ms);

//        //return
//        ms = (1500);
//        set(p, p, p, p);
//        sleep(ms);

        set(0, 0, 0, 0);
    }
}
