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
@Autonomous(name="Near Red", group="Autonomous")
public class AutoNearRed extends LinearOpMode {



    private static final double DRIVE_SPEED = 0.6;
    private static final double TURN_SPEED = 0.5;

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
        while((runtime.seconds() < 5) && opModeIsActive()) {

            //do nothing, waiting for servo to lower

        }
        if( robot.colorSensor.red() > robot.colorSensor.blue() ) {

            //jerk right
            drive.rotateRight(0.5 * TURN_SPEED,45.0);
            drive.rotateLeft(0.5 * TURN_SPEED,45.0);

            //park
            drive.forward(DRIVE_SPEED,12.0);
            drive.rotateRight(0.5 * TURN_SPEED, 90.0);
            drive.forward(DRIVE_SPEED, 5.0);
            runtime.reset();
            while(opModeIsActive() && (runtime.seconds() <= 5)) {

                robot.rightRoller.setPower(1.0);
                robot.leftRoller.setPower(1.0);

            }
            drive.backward(DRIVE_SPEED, 3.0);

        } else {

            //jerk left
            drive.rotateLeft(0.5 * TURN_SPEED,45.0);
            drive.rotateRight(0.5 * TURN_SPEED,45.0);

            //park
            drive.forward(DRIVE_SPEED,12.0);
            drive.rotateRight(0.5 * TURN_SPEED, 90.0);
            drive.forward(DRIVE_SPEED, 5.0);
            runtime.reset();
            while(opModeIsActive() && (runtime.seconds() <= 5)) {

                robot.rightRoller.setPower(1.0);
                robot.leftRoller.setPower(1.0);

            }
            drive.backward(DRIVE_SPEED, 3.0);

        }

        //Indicate smooth ending to opmode
        telemetry.addData("Status: ", "Ending");
        telemetry.update();


    }



}
