package org.firstinspires.ftc.FTC2017_18.teamcode.DriveUtils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by 5815-Disgruntled on 10/18/2017.
 *
 * A DcMotorDriver() implementation using
 * timing to control linear motion.
 *
 * Gyro sensor is used for rotational motion.
 */

public class TimedDriver extends DcMotorDriver {



    public static final double INCHES_PER_SECOND = 18;
    public static final double DEGREES_PER_SECOND = 180;
    //public static ModernRoboticsI2cGyro gyro;

    public TimedDriver(DcMotor[] motors, LinearOpMode opMode) {

        motors_ref = motors;
        //gyro = gyroParam;

        frontLeft = motors_ref[0];
        frontRight = motors_ref[1];
        backLeft = motors_ref[2];
        backRight = motors_ref[3];

        this.opMode = opMode;

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }



    public void forward(Double speed, Double distance) {

        runtime.reset();

        if( headedFwd ) {

            while( opMode.opModeIsActive() && (runtime.seconds() < (distance / INCHES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        } else {

            setMotorsForward();
            headedFwd = true;

            while( opMode.opModeIsActive() && (runtime.seconds() < (distance / INCHES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        }

    }



    public void backward(Double speed, Double distance) {

        runtime.reset();

        if( !headedFwd ) {

            while( opMode.opModeIsActive() && (runtime.seconds() < (distance / INCHES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        } else {

            setMotorsBackward();
            headedFwd = false;

            while( opMode.opModeIsActive() && (runtime.seconds() < (distance / INCHES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        }

    }



    public void rotateRight(Double speed, Double degrees) {

        runtime.reset();

        if( headedRight ) {

            while( opMode.opModeIsActive() && (runtime.seconds() < (degrees / DEGREES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        } else {

            setMotorsRight();
            headedRight = true;

            while( opMode.opModeIsActive() && (runtime.seconds() < (degrees / DEGREES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        }

    }



    public void rotateLeft(Double speed, Double degrees) {

        runtime.reset();

        if( !headedRight ) {

            while( opMode.opModeIsActive() && (runtime.seconds() < (degrees / DEGREES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        } else {

            setMotorsLeft();
            headedRight = false;

            while( opMode.opModeIsActive() && (runtime.seconds() < (degrees / DEGREES_PER_SECOND)) ) {

                powerMotors(speed);

            }

            stopMotors();

        }

    }



}
