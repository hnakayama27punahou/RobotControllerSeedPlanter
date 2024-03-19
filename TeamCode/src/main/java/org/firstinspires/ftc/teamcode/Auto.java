package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Auto extends LinearOpMode {

    DcMotor frontLeft, backLeft, frontRight, backRight, waterDispenser, augerDrill, augerElevation;
    CRServo seedPlanterServo;
    double leftDrivePower = 0;
    double rightDrivePower = 0;
    double drive;
    double turn;

    @Override
    public void runOpMode() throws InterruptedException {
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

        //setting encoders (this is a replacement for running for time (for auger Drill and Auger Elevation)

        augerDrill.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        augerElevation.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //set encoder positioning

        for (int i = 0; i<20; i++){
            frontLeft.setPower(1);
            backLeft.setPower(1);
            frontRight.setPower(1);
            backRight.setPower(1);

            augerElevation.setTargetPosition(250);
            augerElevation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            augerElevation.setPower(1);

        }
    }
}
