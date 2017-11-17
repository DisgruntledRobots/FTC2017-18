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
@TeleOp(name="Mecanum Drive", group="TeleOp")
public class Teleop_Mecanum extends LinearOpMode {

    
    
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    HardwareFrame robot = new HardwareFrame();

    private double multiplyer = 1;
    private double liftCoef = 1;
    public static double a = 17.35/2;
    public static double b = 12.5/2;

    @Override
    public void runOpMode() throws InterruptedException {
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot.init(hardwareMap);



        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */
        // leftMotor  = hardwareMap.dcMotor.get("left motor");
        // rightMotor = hardwareMap.dcMotor.get("right motor");

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        // rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Drive", gamepad1.left_stick_x);
            telemetry.update();



            // mecanum drive if right bumper is held, and tank drive if not
            if( gamepad1.right_bumper ) {

                robot.frontRightMotor.setPower(
                        (-gamepad1.left_stick_y - gamepad1.left_stick_x + -gamepad1.right_stick_x * (a + b))*multiplyer
                );

                robot.frontLeftMotor.setPower(
                        (-gamepad1.left_stick_y + gamepad1.left_stick_x - -gamepad1.right_stick_x * (a + b))*multiplyer
                );

                robot.backLeftMotor.setPower(
                        (-gamepad1.left_stick_y - gamepad1.left_stick_x - -gamepad1.right_stick_x * (a + b))*multiplyer
                );

                robot.backRightMotor.setPower(
                        (-gamepad1.left_stick_y + gamepad1.left_stick_x + -gamepad1.right_stick_x * (a + b))*multiplyer
                );
                telemetry.addData("Debug", "In mecanum drive");
                telemetry.update();

            } else {

                robot.frontLeftMotor.setPower((-gamepad1.left_stick_y)*multiplyer);
                robot.frontRightMotor.setPower((-gamepad1.right_stick_y)*multiplyer);
                robot.backRightMotor.setPower((-gamepad1.right_stick_y)*multiplyer);
                robot.backLeftMotor.setPower((-gamepad1.left_stick_y)*multiplyer);
                telemetry.addData("Debug", "In tank drive");
                telemetry.update();

            }

            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
        
        
        
    }

}
