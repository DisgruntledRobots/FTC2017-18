package org.firstinspires.ftc.teamcode.ControlUtils;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by 5815-Disgruntled on 10/19/2017.
 */

public class Scheduler<T> {

    private int nextIndex = 1;
    private int currentIndex = 0;
    private T driver;
    private Telemetry telemetry;
    private ArrayList<Object[]> queue = new ArrayList();
    private Method[] validMethods;
    private String[] methodNames;

    public Scheduler(T driver, Telemetry telemetry) {

        this.driver = driver;
        this.telemetry = telemetry;

        //array of public methods for driver object
        validMethods = driver.getClass().getMethods();

        //get the names from validMethods[]
        for( int i = 0; i < validMethods.length; i++ ) {

            methodNames[i] = validMethods[i].getName();

        }

    }



    private boolean methodExists(String methodName) {

        if( Arrays.asList(methodNames).contains(methodName) ) {

            return true;

        } else {

            return false;

        }

    }



    private Method getMethod(String methodName) throws NoSuchMethodException {

        for( int i = 0; i < validMethods.length; i++ ) {

            if( validMethods[i].getName().equals(methodName) ) {

                return validMethods[i];

            }

        }

        //if we couldn't return a method object
        throw new NoSuchMethodException("Couldn't find a method in getMethod()");

    }



    private void runNext() {

        Object[] methodInfo = queue.get(currentIndex);
        String methodName = methodInfo[0].toString();
        Object[] methodArgs = Arrays.copyOfRange(methodInfo, 1, methodInfo.length);
        Method method;

        try {

            method = getMethod(methodName);
            method.invoke(driver, methodArgs);

        } catch( NoSuchMethodException e ) {

            telemetry.addData("Error: ", e.getMessage());

        } catch( InvocationTargetException e ) {

            e.getMessage();

        } catch( IllegalAccessException e ) {

            e.getMessage();

        }

    }



    public void enqueue(Object[] methodInfo) throws NoSuchMethodException {

        String methodName = methodInfo[0].toString();

        //check for valid method
        if( methodExists(methodName) ) {

            if( currentIndex == 0 ) {

                queue.add(0, methodInfo);

            } else {

                queue.add(nextIndex, methodInfo);
                nextIndex++;

            }

        } else {

            throw new NoSuchMethodException("Can't enqueue method: " + methodName);

        }

    }



    public void start() {

        while( currentIndex >= 0 ) {

            runNext();
            queue.remove(currentIndex);
            currentIndex--;
            nextIndex--;

        }

    }



}