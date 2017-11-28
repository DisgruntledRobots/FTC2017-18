package org.firstinspires.ftc.FTC2017_18.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.FTC2017_18.teamcode.HardwareFrame;

/**
 * Created by 5815-Disgruntled on 11/25/2017.
 */
@TeleOp(name="Mecanum Test", group="TeleOp")
public class Teleop_Mecanum_New extends LinearOpMode {



    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    HardwareFrame robot = new HardwareFrame();
    public float leftDriveStickX;
    public float leftDriveStickY;
    public double leftDriveStickAngle;
    public float rightDriveStickX;
    public float rightDriveStickY;
    public double rightDriveStickAngle;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            leftDriveStickX = gamepad1.left_stick_x;
            leftDriveStickY = gamepad1.left_stick_y != 0 ? -gamepad1.left_stick_y : gamepad1.left_stick_y;
            leftDriveStickAngle = Math.toDegrees(Math.atan2(leftDriveStickY, leftDriveStickX));

            rightDriveStickX = gamepad1.right_stick_x;
            rightDriveStickY = gamepad1.right_stick_y != 0 ? -gamepad1.right_stick_y : gamepad1.right_stick_y;
            rightDriveStickAngle = Math.toDegrees(Math.atan2(rightDriveStickY, rightDriveStickX));

            telemetry.addData("Status", "Run Time: " + runtime.toString());

            //cartesian coordinates and angle of left joystick on gamepad 1
            telemetry.addData("Left coords: ", "X: " + leftDriveStickX + ", Y: " + leftDriveStickY);
            telemetry.addData("Left angle: ", leftDriveStickAngle);

            //cartesian coordinates and angle of right joystick on gamepad 1
            telemetry.addData("Right coords: ", "X: " + rightDriveStickX + ", Y: " + rightDriveStickY);
            telemetry.addData("Right angle: ", rightDriveStickAngle);

            telemetry.addData("Heading: ", robot.gyroSensor.getHeading());

            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop

        }



    }
}
