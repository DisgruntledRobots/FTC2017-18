package org.firstinspires.ftc.FTC2017_18.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.FTC2017_18.teamcode.HardwareFrame;

/**
 * Created by Maximos on 11/30/2017.
 */
@TeleOp(name="Mecanum Drive: Test2", group="Tests")
public class Teleop_mecanum_New2 extends LinearOpMode {
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    HardwareFrame robot = new HardwareFrame();

    public double multiplyer = 1;
    public static double a = 10.5/2;
    public static double b = 17/2;

    public double leftDriveStickX;
    public double leftDriveStickY;
    public boolean leftDriveStickEngaged;
    public double leftDriveStickAngle;
    public double leftDriveStickHypot;

    public double rightDriveStickX;
    public double rightDriveStickY;
    public boolean rightDriveStickEngaged;
    public double rightDriveStickAngle;
    public double rightDriveStickHypot;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        /*telemetry.addData("Gyro: ", "Calibrating");
        telemetry.update();
        robot.gyroSensor.calibrate();
        while(robot.gyroSensor.isCalibrating() && !isStopRequested()) {

            //do nothing

        }
        telemetry.clear();
        telemetry.update();*/

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            getStickValues();
            //cartesian coordinates and angle of left joystick on gamepad 1
            telemetry.addData("Left coords: ", "X: " + leftDriveStickX + ", Y: " + leftDriveStickY);
            telemetry.addData("Left angle: ", leftDriveStickAngle);

            //cartesian coordinates and angle of right joystick on gamepad 1
            telemetry.addData("Right coords: ", "X: " + rightDriveStickX + ", Y: " + rightDriveStickY);
            telemetry.addData("Right angle: ", rightDriveStickAngle);

            // mecanum drive if right bumper is held, and tank drive if not
            if( gamepad1.right_bumper ) {

                double direction = (Math.PI / 4) + rightDriveStickAngle;
                double targetForceX = Math.cos(direction) * rightDriveStickHypot;
                double targetForceY = Math.sin(direction) * rightDriveStickHypot;

                robot.frontLeftMotor.setPower(targetForceY);
                robot.frontRightMotor.setPower(-targetForceX);
                robot.backLeftMotor.setPower(-targetForceX);
                robot.backRightMotor.setPower(targetForceY);

                telemetry.addData("Drive mode: ", "Mecanum");

            } else {

                robot.frontLeftMotor.setPower(leftDriveStickY);
                robot.frontRightMotor.setPower(rightDriveStickY);
                robot.backRightMotor.setPower(rightDriveStickY);
                robot.backLeftMotor.setPower(leftDriveStickY);
                telemetry.addData("Drive mode: ", "Tank");

            }



            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop

        }



    }



    public void getStickValues() {

        leftDriveStickX = gamepad1.left_stick_x;
        leftDriveStickY = gamepad1.left_stick_y != 0 ? -gamepad1.left_stick_y : gamepad1.left_stick_y;
        if( (leftDriveStickX != 0) || (leftDriveStickY != 0) ) {

            leftDriveStickEngaged = true;

        } else {

            leftDriveStickEngaged = false;

        }
        leftDriveStickAngle = Math.atan2(leftDriveStickY, leftDriveStickX);
        leftDriveStickHypot = Math.hypot(leftDriveStickX,leftDriveStickY);

        rightDriveStickX = gamepad1.right_stick_x;
        rightDriveStickY = gamepad1.right_stick_y != 0 ? -gamepad1.right_stick_y : gamepad1.right_stick_y;
        if( (rightDriveStickX != 0) || (rightDriveStickY != 0) ) {

            rightDriveStickEngaged = true;

        } else {

            rightDriveStickEngaged = false;

        }
        rightDriveStickAngle = Math.atan2(rightDriveStickY, rightDriveStickX);
        rightDriveStickHypot = Math.hypot(rightDriveStickX,rightDriveStickY);

    }



    public void readSensors() {

        telemetry.addData("Heading: ", robot.gyroSensor.getHeading());
        telemetry.addData("X: ", robot.gyroSensor.rawX());
        telemetry.addData("Y: ", robot.gyroSensor.rawY());
        telemetry.addData("Z: ", robot.gyroSensor.rawZ());
        /*telemetry.addData("RGB",
                robot.colorSensor.red() + ", "
                        + robot.colorSensor.green() + ", "
                        + robot.colorSensor.blue()
        );
        telemetry.addData("Range, ultrasonic", robot.rangeSensor.cmUltrasonic());*/

    }
}
