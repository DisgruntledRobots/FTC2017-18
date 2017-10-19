package org.firstinspires.ftc.teamcode.ControlUtils;

/**
 * Created by 5815-Disgruntled on 10/19/2017.
 */

public interface Scheduler {

    public void enqueue(Object[] methodInfo);

    public void run();

}
