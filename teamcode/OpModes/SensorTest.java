package org.firstinspires.ftc.FTC2017_18.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.FTC2017_18.teamcode.DriveUtils.TimedDriver;
import org.firstinspires.ftc.FTC2017_18.teamcode.HardwareFrame;

/**
 * Created by 5815-Disgruntled on 11/16/2017.
 */
@Autonomous(name="Sensors Test", group="Tests")
public class SensorTest extends LinearOpMode {

    private static final double DRIVE_SPEED = 0.6;
    private static final double TURN_SPEED = 0.5;

    private static DcMotor[] motors = new DcMotor[4];

    public HardwareFrame robot = new HardwareFrame();
    private TimedDriver drive;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        //Initialize hardware and scheduler
        robot.init(hardwareMap);
        motors[0] = robot.frontLeftMotor;
        motors[1] = robot.frontRightMotor;
        motors[2] = robot.backLeftMotor;
        motors[3] = robot.backRightMotor;
        drive = new TimedDriver(motors, this);


        //Alert driver station that robot is ready to start
        telemetry.addData("Status: ", "Ready to start");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status: ", "Calibrating Gyro");
        telemetry.update();
        robot.gyroSensor.calibrate();
        robot.servo1.setPosition(1.0);
        while(robot.gyroSensor.isCalibrating()) {

            //do nothing

        }

        telemetry.clear();
        telemetry.update();


        while(opModeIsActive()) {

            telemetry.addData("Light: ", robot.colorSensor.alpha());
            telemetry.addData("Red: ", robot.colorSensor.red());
            telemetry.addData("Blue: ", robot.colorSensor.blue());
            telemetry.addData("Green: ", robot.colorSensor.green());

            telemetry.addData("Gyro: ", robot.gyroSensor.getHeading());

            telemetry.addData("Range: ", robot.rangeSensor.cmUltrasonic());

            telemetry.update();

        }

        //Indicate smooth ending to opmode
        telemetry.addData("Status: ", "Ending");
        telemetry.update();


    }

}
