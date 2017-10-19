package org.firstinspires.ftc.teamcode.DriveUtils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by 5815-Disgruntled on 10/18/2017.
 *
 * An autonomous drive system that relies on timing
 * its linear and rotational movements for control
 */

public class TimedDriver {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    private DcMotor[] motors_ref = new DcMotor[4];
    private ElapsedTime runtime = new ElapsedTime();

    public static final double INCHES_PER_SECOND = 9;

    public TimedDriver(DcMotor[] motors) {

        frontLeft = motors[0];
        frontRight = motors[1];
        backLeft = motors[2];
        backRight = motors[3];

        motors_ref = motors;

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

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

    public void linear(Double speed, Double distance, Double runtime) {

            if( (runtime < (distance / INCHES_PER_SECOND)) ) {

                powerMotors(speed);

            } else {

                stopMotors();

            }

    }

    public void testLinear(boolean fwd, double speed, double distance) {

        runtime.reset();

        if( fwd ) {

            setMotorsForward();

            while( runtime.seconds() < (distance / INCHES_PER_SECOND) ) {

                powerMotors(speed);

            }

            stopMotors();

        } else {

            setMotorsBackward();

            while( runtime.seconds() < (distance / INCHES_PER_SECOND) ) {

                powerMotors(speed);

            }
            stopMotors();

        }
    }

}
