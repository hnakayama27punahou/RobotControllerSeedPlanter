package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Run Robot")
public class Robot extends OpMode {

    DcMotor frontLeft, backLeft, frontRight, backRight, waterDispenser, augerDrill, augerElevation;
    CRServo seedPlanterServo;
    double leftDrivePower = 0;
    double rightDrivePower = 0;
    double drive;
    double turn;

    @Override
    public void init() {

        //Hardware Mapping
        frontLeft = hardwareMap.dcMotor.get("driveMotor1");
        backLeft = hardwareMap.dcMotor.get("driveMotor2");
        frontRight = hardwareMap.dcMotor.get("driveMotor3");
        backRight = hardwareMap.dcMotor.get("driveMotor4");
        waterDispenser = hardwareMap.dcMotor.get("waterDispenser");
        augerDrill = hardwareMap.dcMotor.get("augerDrill");
        augerElevation = hardwareMap.dcMotor.get("augerElevation");

        //Zero Power Behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        augerDrill.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        augerElevation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Setting Direction (Very IMPORTANT - same side motors should be going the same way to avoid shearing pinions)
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        //The note above does not apply for the auger motors or water dispenser

        augerDrill.setDirection(DcMotorSimple.Direction.FORWARD);
        augerElevation.setDirection(DcMotorSimple.Direction.FORWARD);

        //water dispenser must go forward - otherwise will break pump (make sure plugged in properly)

        waterDispenser.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    @Override
    public void loop() {

        //Y-axis on joystick is inverted
        drive = gamepad1.left_stick_y *-1;
        turn = gamepad1.left_stick_x;

        leftDrivePower = Range.clip(drive + turn,-1,1);
        rightDrivePower = Range.clip(drive - turn,-1,1);

        frontRight.setPower(rightDrivePower);
        backRight.setPower(rightDrivePower);
        frontLeft.setPower(leftDrivePower);
        backLeft.setPower(leftDrivePower);
        //drill elevation

        augerElevation.setPower(gamepad1.right_stick_y * -1);

        //drill dig

        if (gamepad1.a) {
            augerDrill.setDirection(DcMotorSimple.Direction.FORWARD);
            augerDrill.setPower(1);
        } else if (gamepad1.b) {
            augerDrill.setDirection(DcMotorSimple.Direction.REVERSE);
            augerDrill.setPower(1);
        } else {
            augerDrill.setPower(0);
        }

        //water dispenser
        if (gamepad1.x) {
            waterDispenser.setPower(0.5);
        } else {
            waterDispenser.setPower(0);
        }
        //seed planter rotation
        if (gamepad1.right_trigger > 0.05) {
            seedPlanterServo.setPower(gamepad1.right_trigger);
        } else {
            seedPlanterServo.setPower(0);
        }

//        seedPlanterServo.setPower(gamepad1.right_trigger); //also works btw, just not sure if there is trigger error
    }
}