package org.firstinspires.ftc.teamcode.ControlUtils;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.DriveUtils.EncoderDriver;
import org.firstinspires.ftc.teamcode.DriveUtils.TimedDriver;

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
    private DcMotor[] motorsRef;
    private ArrayList<Object[]> queue = new ArrayList();
    private Method[] validMethods;
    private String[] methodNames;

    public Scheduler(T driver) {

        this.driver = driver;

        //array of public methods for driver object
        validMethods = driver.getClass().getMethods();

        //get the names from validMethods[]
        for( int i = 0; i < validMethods.length; i++ ) {

            methodNames[i] = validMethods[i].getName();

        }

    }

    public void enqueue(Object[] methodInfo) throws NoSuchMethodException {

        String methodName = methodInfo[0].toString();

        //check for valid method
        if( Arrays.asList(methodNames).contains(methodName) ) {

            if( currentIndex == 0 ) {

                queue.add(0, methodInfo);

            } else {

                queue.add(nextIndex, methodInfo);
                nextIndex++;

            }

        } else {

            throw new NoSuchMethodException("Can't queue method: " + methodName);

        }

    }

    public void start() {

        while( currentIndex > -1 ) {

            runNext();
            queue.remove(currentIndex);
            currentIndex--;
            nextIndex--;

        }

    }

    public void runNext() {

        Object[] methodInfo = queue.get(currentIndex);
        String methodName = methodInfo[0].toString();
        Object[] methodArgs = Arrays.copyOfRange(methodInfo, 1, methodInfo.length);
        Method method;


        //get method and invoke it
        for( int i = 0; i < validMethods.length; i++ ) {

            if( validMethods[i].getName().equals(methodName) ) {

                method = validMethods[i];
                //invoke method
                try {

                    method.invoke(driver, methodArgs);

                } catch( InvocationTargetException e ) {

                    e.getMessage();
                    break;

                } catch( IllegalAccessException e ) {

                    e.getMessage();
                    break;

                }

                break;

            }

        }

    }

}