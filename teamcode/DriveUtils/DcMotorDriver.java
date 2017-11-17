package org.firstinspires.ftc.FTC2017_18.teamcode.DriveUtils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Maximos on 10/28/2017.
 *
 *
 *
 * This class models how any class that provides the functionality
 * to drive a robot with four dc motors should look, regardless of the specific
 * techniques used to actually control the robot
 */

public abstract class DcMotorDriver {

    protected DcMotor[] motors_ref = new DcMotor[4];
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backLeft;
    protected DcMotor backRight;
    protected boolean headedFwd = true;
    protected boolean headedRight = true;
    protected LinearOpMode opMode;
    protected ElapsedTime runtime = new ElapsedTime();
    protected static final double driveCoef = 1;



    protected final void setMotorsForward() {

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

    }



    protected final void setMotorsBackward() {

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

    }



    protected final void setMotorsLeft() {

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

    }



    protected final void setMotorsRight() {

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

    }



    protected final void powerMotors(double speed) {

        frontLeft.setPower(driveCoef * speed);
        frontRight.setPower(driveCoef * speed);
        backLeft.setPower(driveCoef * speed);
        backRight.setPower(driveCoef * speed);

    }



    protected final void stopMotors() {

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }



    abstract public void forward(Double speed, Double distance);



    abstract public void backward(Double speed, Double distance);



    abstract public void rotateRight(Double speed, Double degrees);



    abstract public void rotateLeft(Double speed, Double degrees);



}
