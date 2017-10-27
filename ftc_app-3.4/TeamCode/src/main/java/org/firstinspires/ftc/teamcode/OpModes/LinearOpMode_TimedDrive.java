package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareFrame;

/**
 * Created by 5815-Disgruntled on 10/19/2017.
 */

@Autonomous(name="Test: Driving", group="Tests")
public class LinearOpMode_TimedDrive extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    public HardwareFrame robot = new HardwareFrame();

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    public static final double INCHES_PER_SECOND = 9;
    public static final double DEGREES_PER_SECOND = 90;
    private static final double DRIVE_SPEED = 0.6;
    private static final double TURN_SPEED = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);
        frontLeft = robot.frontLeftMotor;
        frontRight = robot.frontRightMotor;
        backLeft = robot.backLeftMotor;
        backRight = robot.backRightMotor;

        telemetry.addData("Status: ", "Ready to start");
        telemetry.update();

        waitForStart();

        //fwd 45 inches
        linear(true, DRIVE_SPEED, 10);
        //back 45 inches
        linear(false, DRIVE_SPEED, 10);
        //fwd 45 inches
        linear(true, DRIVE_SPEED, 10);


        //rotate to the right 90 degrees
        rotate(true, TURN_SPEED, 90);
        //rotate to the left 90 degrees
        rotate(false, TURN_SPEED, 90);

        telemetry.addData("Status: ", "Ending");
        telemetry.update();

    }

    public void linear(boolean fwd, double speed, double distance) {

        runtime.reset();

        if( fwd ) {

            setMotorsForward();

            while( opModeIsActive() && (runtime.seconds() < (distance / INCHES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        } else {

            setMotorsBackward();

            while( opModeIsActive() && (runtime.seconds() < (distance / INCHES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        }
    }

    public void rotate(boolean cw, double speed, double degrees) {

        runtime.reset();

        if( cw ) {

            setMotorsRight();

            while( opModeIsActive() && (runtime.seconds() < (degrees / DEGREES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        } else {

            setMotorsLeft();

            while( opModeIsActive() && (runtime.seconds() < (degrees / DEGREES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        }

    }

    public void setMotorsForward() {

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

    }

    public void setMotorsBackward() {

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

    }

    public void setMotorsLeft() {

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

    }


    public void setMotorsRight() {

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

    }

    private void powerMotors(double speed) {

        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);

    }

    private void stopMotors() {

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }

}
