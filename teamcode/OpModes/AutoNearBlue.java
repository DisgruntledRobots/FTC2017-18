package org.firstinspires.ftc.FTC2017_18.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.FTC2017_18.teamcode.DriveUtils.TimedDriver;
import org.firstinspires.ftc.FTC2017_18.teamcode.HardwareFrame;

/**
 * Created by Maximos on 1/3/2018.
 */
@Autonomous(name="Near Blue", group="Autonomous")
public class AutoNearBlue extends LinearOpMode {



    private static final double DRIVE_SPEED = 0.6;
    private static final double TURN_SPEED = 0.5;
    private static final double STONE_TO_PARK = 28.0;

    private static DcMotor[] motors = new DcMotor[4];

    public HardwareFrame robot = new HardwareFrame();
    private TimedDriver drive;
    private ElapsedTime runtime = new ElapsedTime();
    private double adjust = 0;

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

        //read jewel
        robot.servo1.setPosition(1);
        telemetry.addData("Red: ", robot.colorSensor.red());
        telemetry.addData("Blue: ", robot.colorSensor.blue());
        telemetry.update();
        runtime.reset();
        while((runtime.seconds() < 2) && opModeIsActive()) {

            //do nothing, waiting for servo to lower

        }

        while(robot.colorSensor.red() == robot.colorSensor.blue()) {

            drive.rotateLeft(TURN_SPEED, 0.01);
            adjust += 0.01;

        }

        if( robot.colorSensor.blue() > robot.colorSensor.red() ) {

            //jerk right
            drive.rotateRight(0.5 * TURN_SPEED,45.0 + adjust);
            robot.servo1.setPosition(0);
            runtime.reset();
            while( (runtime.seconds() < 2) && opModeIsActive() ) {

                //do nothing, waiting for servo arm to move up

            }
            drive.rotateLeft(0.5 * TURN_SPEED,45.0);

            //align with cryptobox
            drive.forward(DRIVE_SPEED, STONE_TO_PARK);
            drive.rotateRight(TURN_SPEED, 45.0);

            //score glyph
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
            drive.forward(DRIVE_SPEED, STONE_TO_PARK);
            drive.rotateRight(TURN_SPEED, 45.0);

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
