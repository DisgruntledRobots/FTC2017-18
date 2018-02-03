package org.firstinspires.ftc.FTC2017_18.teamcode.DriveUtils;

import android.util.Range;

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

    public void rotate(double degrees) {

        gyro.resetZAxisIntegrator();
        double targetHeading = gyro.getIntegratedZValue() + degrees;
        double errorUnscaled = targetHeading - gyro.getIntegratedZValue();
        double errorScaled = errorUnscaled / Math.abs(degrees);
        double errorThreshold = 5.0;
        double Kp = 0.1;
        double power;

        if( degrees < 0 ) {

            setMotorsRight();

        } else if( degrees > 0 ) {

            setMotorsLeft();

        }

        while((Math.abs(errorUnscaled) > errorThreshold) && opMode.opModeIsActive()) {

            errorUnscaled = targetHeading - gyro.getIntegratedZValue();
            errorScaled = errorUnscaled / Math.abs(degrees);
            power = Math.abs(errorScaled * Kp);
            opMode.telemetry.addData("Power: ", power);
            powerMotors(power);

        }

    }

    public void rotateRight(Double speed, Double degrees) {

        //empty implementation

    }



    public void rotateLeft(Double speed, Double degrees) {

        //empty implementation

    }

}
