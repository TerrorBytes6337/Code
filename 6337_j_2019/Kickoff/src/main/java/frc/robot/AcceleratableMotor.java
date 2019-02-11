package frc.robot;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;

//TODO: Once decided, change PWMSpeedController to specific component like Spark
public class AcceleratableMotor extends Spark
{
    private double inc;
    private static final double DEFAULT_INCREMENT = 0.05;
    public AcceleratableMotor(final int channel)
    {
        super(channel);
        inc = DEFAULT_INCREMENT;
    }
    public AcceleratableMotor(final int channel, double increment)
    {
        super(channel);
        inc = Math.abs(increment);
    }
    public void set(double desiredSpeed)
    {
        /*
        POLISH THIS
        double nextSpeed = Math.Abs(currentSpeed) + inc;
        nextSpeed *= Math.Signum(currentSpeed);
        if(desiredSpeed - nextSpeed > 0)
        {
            //if the next speed does not go over desiredSpeed
            this.setSpeed(nextSpeed);
            currentSpeed = nextSpeed;
        }
        else
        {
            this.setSpeed(desiredSpeed);
            currentSpeed = desiredSpeed;
        }
        */
        // Interpolate the current speed to the desired speed
        setSpeed(accelerateNoTime(get(),desiredSpeed));
    }
    private double accelerateNoTime(double currentVal, double desiredVal)
    {
        return accelerateNoTime(currentVal, desiredVal, 1.0, inc);
    }
    public static double accelerateNoTime(double currentValue, double desiredValue, double maxAbsValue, double increment)
    {
        double result = currentValue + Math.signum(desiredValue - currentValue)*increment;
        if(result > maxAbsValue)
        {
            return maxAbsValue;
        }
        else if(result < -maxAbsValue)
        {
            return maxAbsValue;
        }
        else
        {
            return result;
        }
    }
    /**
     * 
     * @param currentVelocity: has direction factor
     * @param acceleration: has direction factor
     * @param maxSpeed: only positive values
     * @return
     */
    public static double accelerateValue(double currentVelocity, double acceleration, double maxSpeed, double timeInSec)
    {
        double result = currentVelocity + acceleration * timeInSec;
        if(result > maxSpeed)
        {
            return maxSpeed;
        }
        else if(result < -maxSpeed)
        {
            return -maxSpeed;
        }
        else
        {
            return result;
        }
    }
}