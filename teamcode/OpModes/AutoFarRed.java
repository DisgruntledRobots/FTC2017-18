package org.firstinspires.ftc.FTC2017_18.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.FTC2017_18.teamcode.DriveUtils.TimedDriver;
import org.firstinspires.ftc.FTC2017_18.teamcode.HardwareFrame;

/**
 * Created by Maximos on 12/30/2017.
 */
@Autonomous(name="Far Red", group="Autonomous")
public class AutoFarRed extends LinearOpMode {



    private static final double DRIVE_SPEED = 0.6;
    private static final double TURN_SPEED = 0.5;
    private static final double STONE_TO_PARK = 28.0;

    private static DcMotor[] motors = new DcMotor[4];

    public HardwareFrame robot = new HardwareFrame();
    private TimedDriver drive;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        double adjust = 0;

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

        //read jewel
        robot.servo1.setPosition(1);
        telemetry.addData("Red: ", robot.colorSensor.red());
        telemetry.addData("Blue: ", robot.colorSensor.blue());
        telemetry.update();
        runtime.reset();
        while((runtime.seconds() < 2) && opModeIsActive()) {

            //do nothing, waiting for servo

        }

        while(robot.colorSensor.red() == robot.colorSensor.blue()) {

            drive.rotateLeft(TURN_SPEED, 0.01);
            adjust += 0.01;

        }

        telemetry.clear();
        if( robot.colorSensor.red() > robot.colorSensor.blue() ) {

            //jerk right
            telemetry.addData("Action: ", "Knocking off blue jewel to the right");
            telemetry.update();
            drive.rotateRight(0.5 * TURN_SPEED,45.0 + adjust);
            telemetry.addData("Action: ", "Bringing arm back up");
            telemetry.update();
            robot.servo1.setPosition(0);
            runtime.reset();
            while( (runtime.seconds() < 2) && opModeIsActive() ) {

                //do nothing, waiting for servo arm to move up

            }
            telemetry.addData("Action: ", "Straightening out on stone");
            telemetry.update();
            drive.rotateLeft(0.5 * TURN_SPEED,45.0);

            //align with cryptobox
            telemetry.addData("Action: ", "Driving to cryptobox");
            telemetry.update();
            drive.backward(DRIVE_SPEED, STONE_TO_PARK);
            telemetry.addData("Action: ", "Aiming glyph");
            telemetry.update();
            drive.rotateLeft(TURN_SPEED, 15.0);

            //score glyph
            telemetry.addData("Action: ", "Firing glyph");
            telemetry.update();
            runtime.reset();
            while( opModeIsActive() && (runtime.seconds() <= 5) ) {

                robot.blockTray.setPower(-DRIVE_SPEED);

            }

            runtime.reset();
            while( opModeIsActive() && (runtime.seconds() <= 5) ) {

                robot.blockTray.setPower(+DRIVE_SPEED);

            }

        } else {

            //jerk left
            drive.rotateLeft(0.5 * TURN_SPEED,45.0 - adjust);
            robot.servo1.setPosition(0);
            runtime.reset();
            while( (runtime.seconds() < 2) && opModeIsActive() ) {

                //do nothing, waiting for servo arm to move up

            }
            drive.rotateRight(0.5 * TURN_SPEED,45.0);

            //align with cryptobox
            drive.backward(DRIVE_SPEED, STONE_TO_PARK);
            drive.rotateLeft(TURN_SPEED, 15.0);

            //score glyph
            runtime.reset();
            while( opModeIsActive() && (runtime.seconds() <= 5) ) {

                robot.blockTray.setPower(-DRIVE_SPEED);

            }

            runtime.reset();
            while( opModeIsActive() && (runtime.seconds() <= 5) ) {

                robot.blockTray.setPower(+DRIVE_SPEED);

            }

        }

        //Indicate smooth ending to opmode
        telemetry.addData("Status: ", "Ending");
        telemetry.update();


    }



}
