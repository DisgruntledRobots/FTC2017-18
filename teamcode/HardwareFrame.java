package org.firstinspires.ftc.FTC2017_18.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.hardware.motors.NeveRest40Gearmotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
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

    public DcMotor blockTray = null;
    public DcMotor intakePos = null;
    public DcMotor rollers = null;

    public Servo servo1;

    public ColorSensor colorSensor;
    public ModernRoboticsI2cGyro gyroSensor;
    public ModernRoboticsI2cRangeSensor rangeSensor;

    private HardwareMap hwMap =  null;
    private ElapsedTime period = new ElapsedTime();

    public HardwareFrame(){

    }



    private void init_servos () {

        servo1  = hwMap.get(Servo.class, "servo_1");
        servo1.setPosition(0);

    }



    private void init_sensors() {

        gyroSensor = hwMap.get(ModernRoboticsI2cGyro.class, "gyro_sensor");

        colorSensor = hwMap.get(ColorSensor.class, "color_sensor");
        colorSensor.enableLed(true);

        rangeSensor = hwMap.get(ModernRoboticsI2cRangeSensor.class, "range_sensor");
        rangeSensor.enableLed(true);

    }



    private void init_scoring() {

        blockTray = hwMap.get(DcMotor.class, "block_tray");
        intakePos = hwMap.get(DcMotor.class, "intake_pos");
        rollers = hwMap.get(DcMotor.class, "rollers");

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



    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {

        // Save reference to Hardware map
        hwMap = ahwMap;

        init_drive();

        init_scoring();

        init_servos();

        init_sensors();

    }

}
