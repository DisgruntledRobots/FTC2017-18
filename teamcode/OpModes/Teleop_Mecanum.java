package org.firstinspires.ftc.FTC2017_18.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.FTC2017_18.teamcode.HardwareFrame;

/**
 * Created by Maximos on 10/27/2017.
 */
@TeleOp(name="Mecanum Drive: Main", group="TeleOp")
public class Teleop_Mecanum extends LinearOpMode {

    
    
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

    boolean altControls = false;

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if( gamepad2.start ) {

                altControls = !altControls;

            }

            telemetry.addData("Status", "Run Time: " + runtime.toString());

            getStickValues();

            //cartesian coordinates and angle of left joystick on gamepad 1
            telemetry.addData("Left coords: ", "X: " + leftDriveStickX + ", Y: " + leftDriveStickY);
            telemetry.addData("Left angle: ", leftDriveStickAngle);

            //cartesian coordinates and angle of right joystick on gamepad 1
            telemetry.addData("Right coords: ", "X: " + rightDriveStickX + ", Y: " + rightDriveStickY);
            telemetry.addData("Right angle: ", rightDriveStickAngle);

            //if right bumper held, go into regular mecanum drive
            //if no bumpers are held, go into tank drive
            if( gamepad1.right_bumper ) {

                double multiplyer = 1;
                double a = 17.35/2;
                double b = 12.5/2;

                telemetry.addData("Front Right: ", (leftDriveStickY - leftDriveStickX + rightDriveStickX * (a + b)));
                robot.frontRightMotor.setPower(
                        (leftDriveStickY - leftDriveStickX + rightDriveStickX * (a + b))
                );

                telemetry.addData("Front Left: ", (leftDriveStickY + leftDriveStickX - rightDriveStickX * (a + b)));
                robot.frontLeftMotor.setPower(
                        (leftDriveStickY + leftDriveStickX - rightDriveStickX * (a + b))
                );

                telemetry.addData("Back Left: ", (leftDriveStickY - leftDriveStickX - rightDriveStickX * (a + b)));
                robot.backLeftMotor.setPower(
                        (leftDriveStickY - leftDriveStickX - rightDriveStickX * (a + b))
                );


                telemetry.addData("Back Right: ", (leftDriveStickY + leftDriveStickX + rightDriveStickX * (a + b)));
                robot.backRightMotor.setPower(
                        (leftDriveStickY + leftDriveStickX + rightDriveStickX * (a + b))
                );

                telemetry.addData("Drive mode: ", "Mecanum");

            } else {

                robot.frontLeftMotor.setPower(leftDriveStickY);
                robot.frontRightMotor.setPower(rightDriveStickY);
                robot.backRightMotor.setPower(rightDriveStickY);
                robot.backLeftMotor.setPower(leftDriveStickY);
                telemetry.addData("Drive mode: ", "Tank");

            }

            if( !altControls ) {

                robot.leftRoller.setPower(gamepad2.right_stick_y);
                robot.rightRoller.setPower(-gamepad2.left_stick_y);
                robot.blockTray.setPower(0.65 * (gamepad2.left_trigger - gamepad2.right_trigger));

            } else {

                robot.rightRoller.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
                robot.leftRoller.setPower(-gamepad2.right_trigger + gamepad2.left_trigger);
                robot.blockTray.setPower(0.65 * gamepad2.left_stick_y);

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
