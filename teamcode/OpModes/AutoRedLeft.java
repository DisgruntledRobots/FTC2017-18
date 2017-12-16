package org.firstinspires.ftc.FTC2017_18.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.FTC2017_18.teamcode.DriveUtils.TimedDriver;
import org.firstinspires.ftc.FTC2017_18.teamcode.HardwareFrame;

/**
 * Created by Maximos on 12/8/2017.
 */
@Autonomous(name="Red Left", group="Autonomous")
public class AutoRedLeft extends LinearOpMode {



    private static final double DRIVE_SPEED = 0.6;
    private static final double TURN_SPEED = 0.5;

    private static DcMotor[] motors = new DcMotor[4];

    public HardwareFrame robot = new HardwareFrame();
    private TimedDriver drive;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

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
        while(robot.gyroSensor.isCalibrating()) {

            //do nothing

        }

        telemetry.clear();
        telemetry.addData("Status: ", "Running");
        telemetry.update();

        //score a glyph
        robot.servo1.setPosition(1);
        telemetry.addData("Red: ", robot.colorSensor.red());
        telemetry.addData("Blue: ", robot.colorSensor.blue());
        telemetry.update();
        while((robot.colorSensor.alpha() < 5) && !opModeIsActive()) {

            //do nothing

        }
        if( robot.colorSensor.red() > robot.colorSensor.blue() ) {

            drive.rotateRight(TURN_SPEED,20.0);
            drive.rotateLeft(TURN_SPEED,20.0);

            drive.backward(DRIVE_SPEED,17.0);
            drive.rotateRight(TURN_SPEED,180.0);
            drive.forward(DRIVE_SPEED,13.0);

        } else {

            drive.rotateLeft(TURN_SPEED,20.0);
            drive.rotateRight(TURN_SPEED,20.0);

            drive.backward(DRIVE_SPEED,17.0);
            drive.rotateRight(TURN_SPEED,180.0);
            drive.forward(DRIVE_SPEED,13.0);

        }

        //Indicate smooth ending to opmode
        telemetry.addData("Status: ", "Ending");
        telemetry.update();


    }



}
