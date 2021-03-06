package org.firstinspires.ftc.FTC2017_18.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.FTC2017_18.teamcode.HardwareFrame;

/**
 * Created by Maximos on 1/8/2018.
 */
@TeleOp(name="Mecanum Drive: Test", group="TeleOp")
public class ControlsTest extends LinearOpMode {



    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    HardwareFrame robot = new HardwareFrame();

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

        robot.init(hardwareMap);

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
        telemetry.update();
        runtime.reset();

        double targetHeading = robot.gyroSensor.getIntegratedZValue() - 90;
        double Kp = 0.5;
        double errorUnscaled = targetHeading - robot.gyroSensor.getIntegratedZValue();
        double errorScaled = errorUnscaled / Math.abs(90);
        // run until the end of the match (driver presses STOP)
        while((Math.abs(errorUnscaled) > 2.0) && opModeIsActive()) {

            robot.frontLeftMotor.setPower(-gamepad1.left_stick_y);
            robot.frontRightMotor.setPower(-gamepad1.right_stick_y);
            robot.backRightMotor.setPower(-gamepad1.right_stick_y);
            robot.backLeftMotor.setPower(-gamepad1.left_stick_y);
            telemetry.addData("Drive mode: ", "Tank");


            errorUnscaled = targetHeading - robot.gyroSensor.getIntegratedZValue();
            errorScaled = errorUnscaled / Math.abs(90);
            telemetry.addData("Power: ", errorScaled * Kp);

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
