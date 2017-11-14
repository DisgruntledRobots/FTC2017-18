package org.firstinspires.ftc.teamcode.DriveUtils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by 5815-Disgruntled on 10/16/2017.
 *
 *
 *
 * A DcMotorDriver() implementation using
 * built-in encoders to control linear and rotational
 * motion
 *
 * At the moment, the encoders aren't returning correct values,
 * so we've left this class empty for now.
 */

public class EncoderDriver extends DcMotorDriver {

    private static final double COUNTS_PER_MOTOR_REV = 1440 ;    // eg: TETRIX Motor Encoder
    private static final double DRIVE_GEAR_REDUCTION = 2.0 ;     // This is < 1.0 if geared UP
    private static final double WHEEL_DIAMETER_INCHES = 4.0 ;     // For figuring circumference
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    public EncoderDriver(DcMotor[] motors, LinearOpMode opMode) {

        motors_ref = motors;

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



    public void forward(Double speed, Double distance) { }



    public void backward(Double speed, Double distance) { }



    public void rotateRight(Double speed, Double degrees) { }



    public void rotateLeft(Double speed, Double degrees) { }
    
    

}
