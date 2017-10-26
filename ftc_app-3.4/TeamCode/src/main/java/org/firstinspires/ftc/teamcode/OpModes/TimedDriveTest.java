package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ControlUtils.Scheduler;
import org.firstinspires.ftc.teamcode.DriveUtils.EncoderDriver;
import org.firstinspires.ftc.teamcode.DriveUtils.TimedDriver;
import org.firstinspires.ftc.teamcode.HardwareFrame;

import java.sql.Time;

/**
 * Created by 5815-Disgruntled on 10/16/2017.
 */

@Autonomous(name="Test: Driving, timed", group="Tests")
@Disabled
public class TimedDriveTest extends LinearOpMode {

    private static final double DRIVE_SPEED = 0.6;
    private static final double TURN_SPEED = 0.5;

    private static DcMotor[] motors = new DcMotor[4];

    public HardwareFrame robot = new HardwareFrame();
    private TimedDriver drive;
    private DriveScheduler scheduler;
    private ElapsedTime runtime = new ElapsedTime();

    /*
    * The purpose of this class is to enable writing the code for controlling motors
    * outside of the opmode while still being able to run them in the opModeIsActive()
    * control loop
    * */
    private class DriveScheduler<T> extends Scheduler {

        public DriveScheduler(T driver) {

            super(driver, telemetry);

        }

    }

    @Override
    public void runOpMode() {

        //Initialize hardware and scheduler
        robot.init(hardwareMap);
        motors[0] = robot.frontLeftMotor;
        motors[1] = robot.frontRightMotor;
        motors[2] = robot.backLeftMotor;
        motors[3] = robot.backRightMotor;
        drive = new TimedDriver(motors, this);
        //scheduler = new DriveScheduler(timedDriver);


        //Alert driver station that robot is ready to start
        telemetry.addData("Status: ", "Ready to start");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        drive.forward(DRIVE_SPEED, 9.0);
        drive.backward(DRIVE_SPEED, 9.0);

        sleep(5000);
        //Indicate smooth ending to opmode
        telemetry.addData("Status: ", "Ending");
        telemetry.update();

    }

}
