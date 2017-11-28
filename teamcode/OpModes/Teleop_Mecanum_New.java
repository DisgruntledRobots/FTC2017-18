package org.firstinspires.ftc.FTC2017_18.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.FTC2017_18.teamcode.HardwareFrame;

/**
 * Created by 5815-Disgruntled on 11/25/2017.
 */

public class Teleop_Mecanum_New extends LinearOpMode {



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

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Drive", gamepad1.left_stick_x);
            telemetry.update();

            if( gamepad1.left_stick_y != 0 ) {

                //do nothing

            } else if( gamepad1.right_stick_x != 0 ) {

                //lateralStrafe(gamepad1.right_stick_x);

            }

            telemetry.addData("Debug", "In mecanum drive");
            telemetry.update();

            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop

        }



    }
}
