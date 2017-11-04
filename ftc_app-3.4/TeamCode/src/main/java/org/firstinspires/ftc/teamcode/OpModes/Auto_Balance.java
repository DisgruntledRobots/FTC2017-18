package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.DriveUtils.TimedDriver;
import org.firstinspires.ftc.teamcode.HardwareFrame;

/**
 * Created by 5815-Disgruntled on 11/3/2017.
 */
@Autonomous(name="Autonomous: Balance", group="Autonomous")
public class Auto_Balance extends LinearOpMode {

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

        /*drive.rotateLeft(TURN_SPEED, 22.5);
        telemetry.addData("Test", "Catch this");
        telemetry.update();*/
        drive.forward(DRIVE_SPEED, 12.0);

        //Indicate smooth ending to opmode
        telemetry.addData("Status: ", "Ending");
        telemetry.update();


    }

}
