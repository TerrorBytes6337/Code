package frc.robot;

//TODO: Once decided, change PWMSpeedController to specific component like Spark
public final class AcceleratableMotor
{
    public static final double DEFAULT_INCREMENT = 0.05;
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