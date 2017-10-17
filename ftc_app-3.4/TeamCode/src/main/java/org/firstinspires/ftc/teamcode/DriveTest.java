package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.DriveUtils.EncoderDriver;
import org.firstinspires.ftc.teamcode.HardwareFrame;

/**
 * Created by 5815-Disgruntled on 10/16/2017.
 */

@Autonomous(name="Test: Driving", group="Tests")
public class DriveTest extends LinearOpMode {

    private static DcMotor[] motors = new DcMotor[4];
    public HardwareFrame robot = new HardwareFrame();
    private ElapsedTime runtime = new ElapsedTime();
    private EncoderDriver Drive;
    private static final double DRIVE_SPEED = 0.6;
    private static final double TURN_SPEED = 0.5;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        motors[0] = robot.frontLeftMotor;
        motors[1] = robot.frontRightMotor;
        motors[2] = robot.backLeftMotor;
        motors[3] = robot.backRightMotor;

        Drive = new EncoderDriver(motors);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        robot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                robot.frontLeftMotor.getCurrentPosition(),
                robot.frontRightMotor.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        Drive.linear(DRIVE_SPEED, 20);
        Drive.linear(DRIVE_SPEED, -20);

        telemetry.addData("Path", "Complete");
        telemetry.update();

    }

}
