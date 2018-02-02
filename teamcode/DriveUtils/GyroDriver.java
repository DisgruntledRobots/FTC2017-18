package org.firstinspires.ftc.FTC2017_18.teamcode.DriveUtils;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by 5815-Disgruntled on 2/1/2018.
 */

public class GyroDriver extends DcMotorDriver {

    public static final double INCHES_PER_SECOND = 9;

    public GyroDriver(DcMotor[] motors, LinearOpMode opMode, ModernRoboticsI2cGyro gyroSensor) {

        motors_ref = motors;
        gyro = gyroSensor;

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

        //double targetHeading = gyro.getHeading() - degrees;
        double targetHeading = gyro.getIntegratedZValue() - degrees;

        runtime.reset();

        if( headedRight ) {

            while( opMode.opModeIsActive() && (targetHeading < gyro.getIntegratedZValue()) ) {

                powerMotors(speed);

            }

            stopMotors();

        } else {

            setMotorsRight();
            headedRight = true;

            while( opMode.opModeIsActive() && (targetHeading < gyro.getIntegratedZValue()) ) {

                powerMotors(speed);

            }

            stopMotors();

        }

    }



    public void rotateLeft(Double speed, Double degrees) {

        //double targetHeading = gyro.getHeading() + degrees;
        double targetHeading = gyro.getIntegratedZValue() + degrees;
        double error = targetHeading - gyro.getIntegratedZValue();

        runtime.reset();

        if( !headedRight ) {

            while( opMode.opModeIsActive() && (targetHeading > gyro.getIntegratedZValue()) ) {

                powerMotors(speed);

            }

            stopMotors();

        } else {

            setMotorsLeft();
            headedRight = false;

            while( opMode.opModeIsActive() && (targetHeading > gyro.getIntegratedZValue()) ) {

                powerMotors(speed);

            }

            stopMotors();

        }

    }

}
