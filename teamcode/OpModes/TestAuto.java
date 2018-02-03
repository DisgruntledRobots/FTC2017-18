package org.firstinspires.ftc.FTC2017_18.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.FTC2017_18.teamcode.DriveUtils.DcMotorDriver;
import org.firstinspires.ftc.FTC2017_18.teamcode.DriveUtils.GyroDriver;
import org.firstinspires.ftc.FTC2017_18.teamcode.DriveUtils.TimedDriver;
import org.firstinspires.ftc.FTC2017_18.teamcode.HardwareFrame;

/**
 * Created by 5815-Disgruntled on 2/1/2018.
 */
@Autonomous(name="Test Auto", group="Tests")
public class TestAuto extends LinearOpMode {



    private static final double DRIVE_SPEED = 0.6;
    private static final double TURN_SPEED = 0.5;
    private static final double STONE_TO_PARK = 28.0;

    private static DcMotor[] motors = new DcMotor[4];

    public HardwareFrame robot = new HardwareFrame();
    private GyroDriver drive;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize hardware and scheduler
        robot.init(hardwareMap);
        motors[0] = robot.frontLeftMotor;
        motors[1] = robot.frontRightMotor;
        motors[2] = robot.backLeftMotor;
        motors[3] = robot.backRightMotor;
        drive = new GyroDriver(motors, this, robot.gyroSensor);


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

        drive.rotate(90.0);
        drive.rotate(-90.0);
        drive.rotate(90.0);
        drive.rotate(-90.0);
        drive.rotate(90.0);
        drive.rotate(-90.0);

        //Indicate smooth ending to opmode
        telemetry.addData("Status: ", "Ending");
        telemetry.update();


    }

}
