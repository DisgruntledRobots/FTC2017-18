package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.NeveRest40Gearmotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by 5815-Disgruntled on 10/13/2017.
 */

public class HardwareFrame {

    /* Public OpMode members. */
    public DcMotor frontRightMotor = null;
    public DcMotor  backRightMotor = null;
    public DcMotor frontLeftMotor = null;
    public DcMotor backLeftMotor = null;

    public DcMotor cubeMotor = null;
    public DcMotor liftMotor = null;

    public Servo Lift1 = null;
    public Servo Lift2 = null;

    private HardwareMap hwMap =  null;
    private ElapsedTime period = new ElapsedTime();

    public HardwareFrame(){

    }



    private void init_drive() {

        // Define and Initialize Motors
        frontRightMotor = hwMap.get(DcMotor.class, "drive_motor_front_right");
        backRightMotor = hwMap.get(DcMotor.class, "drive_motor_back_right");
        frontLeftMotor = hwMap.get(DcMotor.class, "drive_motor_front_left");
        backLeftMotor = hwMap.get(DcMotor.class, "drive_motor_back_left");

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);

        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

    }

    private void init_servo (){

        Lift1  = hwMap.get(Servo.class, "servo_1");
        Lift2 = hwMap.get(Servo.class, "servo_2");
        Lift1.setPosition(0);
        Lift2.setPosition(0);

    }

    private void init_cube_motors() {

        cubeMotor = hwMap.get(DcMotor.class, "throat_motor");
        liftMotor = hwMap.get(DcMotor.class, "lift_motor");

        cubeMotor.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        cubeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        cubeMotor.setPower(0);
        liftMotor.setPower(0);

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map
        hwMap = ahwMap;

        init_drive();

        init_cube_motors();

        init_servo();

    }

}
