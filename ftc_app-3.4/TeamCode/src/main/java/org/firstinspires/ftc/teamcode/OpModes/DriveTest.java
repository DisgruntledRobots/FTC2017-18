package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ControlUtils.Scheduler;
import org.firstinspires.ftc.teamcode.DriveUtils.TimedDriver;
import org.firstinspires.ftc.teamcode.HardwareFrame;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by 5815-Disgruntled on 10/16/2017.
 */

@Autonomous(name="Test: Driving, abstract", group="Tests")
@Disabled
public class DriveTest extends LinearOpMode {

    private static DcMotor[] motors = new DcMotor[4];

    public HardwareFrame robot = new HardwareFrame();
    private ElapsedTime runtime = new ElapsedTime();

    private static final double DRIVE_SPEED = 0.6;
    private static final double TURN_SPEED = 0.5;

    /*
    * The purpose of this class is to enable writing the code for controlling motors
    * outside of the opmode while still being able to run them in the opModeIsActive()
    * control loop
    * */
    private class DriveTestScheduler implements Scheduler {

        public ArrayList<Object[]> queue = new ArrayList();
        private int nextIndex = 1;
        private int currentIndex = 0;
        private Class[] ctrParams = new Class[1];
        private DcMotor[] motorsRef;
        private TimedDriver drive;

        public DriveTestScheduler(DcMotor[] motors) {

            motorsRef = motors;
            drive = new TimedDriver(motors);

        }

        public void enqueue(Object[] methodInfo) {

            if( currentIndex == 0 ) {

                queue.add(0, methodInfo);

            } else {

                queue.add(nextIndex, methodInfo);
                nextIndex++;

            }

        }

        public void start() {

            while( currentIndex != -1 ) {

                run();

            }

        }

        public void run() {

            Object[] methodInfo = queue.get(currentIndex);
            String methodName = (String)methodInfo[0];
            Object[] methodArgs = Arrays.copyOfRange(methodInfo, 1, methodInfo.length);
            ArrayList<Object> methodArgsWrapper = new ArrayList<>(Arrays.asList(methodArgs));
            Method method;

            if( methodName == "linear" ) {

                methodArgsWrapper.add(runtime.seconds());

                try {

                    method = drive.getClass().getDeclaredMethod(methodName, new Class[] {
                            Double.class,
                            Double.class,
                            Double.class
                    });

                    runtime.reset();
                    while( opModeIsActive() ) {

                        methodArgsWrapper.toArray()[2] = runtime.seconds();
                        method.invoke(drive, methodArgsWrapper.toArray());

                    }

                } catch( NoSuchMethodException e ) {

                    telemetry.addData("User code threw an exception: ", "Cannot find drive.linear");
                    telemetry.update();

                } catch(IllegalAccessException e) {

                    telemetry.addData("User code threw an exception: ", "Cannot access drive.linear");
                    telemetry.update();

                } catch(InvocationTargetException e) {

                    telemetry.addData("User code threw an exception: ", "Something is wrong with drive");
                    telemetry.update();

                }

            }

            queue.remove(currentIndex);
            currentIndex--;
            nextIndex--;

        }

        /*public void test(DcMotor[] motors) {

            try {

                params[0] = DcMotor[].class;
                Class<?> DriverClass = Class.forName("org.firstinspires.ftc.teamcode.DriveUtils.TimedDriver");
                Object drive = DriverClass.getDeclaredConstructor(params).newInstance(new Object[] {motors});
                Method[] allMethods = DriverClass.getDeclaredMethods();
                for( int i = 0; i < allMethods.length; i++ ) {

                    telemetry.addData("Method: ", allMethods[i].getName());

                }
                telemetry.update();

            } catch( ClassNotFoundException e ) {

                telemetry.addData("User code threw exception1: ", e.getMessage());
                telemetry.update();

            } catch( IllegalAccessException e ) {

                telemetry.addData("User code threw excepton2: ", e.getMessage());
                telemetry.update();

            } catch( InstantiationException e ) {

                telemetry.addData("User code threw excepton3: ", e.getMessage());
                telemetry.update();

            } catch( NoSuchMethodException e ) {

                telemetry.addData("User code threw exception4: ", e.getMessage());
                telemetry.update();

            } catch( InvocationTargetException e ) {

                telemetry.addData("User code threw exception5: ", e.getMessage());
                telemetry.update();

            }


        }*/

    }

    @Override
    public void runOpMode() {

        //Initialize hardware and scheduler
        robot.init(hardwareMap);
        motors[0] = robot.frontLeftMotor;
        motors[1] = robot.frontRightMotor;
        motors[2] = robot.backLeftMotor;
        motors[3] = robot.backRightMotor;
        DriveTestScheduler scheduler = new DriveTestScheduler(motors);


        //Alert driver station that robot is ready to start
        telemetry.addData("Status: ", "Ready to start");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //schedule 5 seconds fwd
        scheduler.enqueue(new Object[] {
                "linear",
                //true,
                DRIVE_SPEED,
                45.0
        });

        scheduler.start();

        sleep(5000);
        //Indicate smooth ending to opmode
        telemetry.addData("Status: ", "Ending");
        telemetry.update();

    }

}
