package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Runstation")
public class Robot extends OpMode {

    DcMotor frontLeft, backLeft, frontRight, backRight;
    double leftDrivePower = 0;
    double rightDrivePower = 0;

    boolean isTurn = false;
    double drive;
    double turn;
    double rightSidePower, leftSidePower;

    @Override
    public void init() {

        // drive motor 1 and 2 are on the left side
        // drive motor 3 and 4 are on the right side
        frontLeft = hardwareMap.dcMotor.get("driveMotor1");
        backLeft = hardwareMap.dcMotor.get("driveMotor2");
        frontRight = hardwareMap.dcMotor.get("driveMotor3");
        backRight = hardwareMap.dcMotor.get("driveMotor4");
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop() {
        drive = gamepad1.left_stick_y *-1;
        turn = gamepad1.left_stick_x;

        leftDrivePower = Range.clip(drive + turn,-1,1);
        rightDrivePower = Range.clip(drive - turn,-1,1);

        frontRight.setPower(rightSidePower);
        backRight.setPower(rightSidePower);
        frontLeft.setPower(leftDrivePower);
        backLeft.setPower(leftDrivePower);
//drill function
        if (gamepad1.b) {

        }
        //water dispenser
        if (gamepad1.x) {

        }
        //seed planter rotation
        if (gamepad1.a) {

        }
    }
}