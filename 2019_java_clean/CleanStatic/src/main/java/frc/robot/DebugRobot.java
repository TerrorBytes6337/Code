package frc.robot;

import java.lang.reflect.Field;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Different from Robot that it shows all public fields into readonly ones
public class DebugRobot extends Robot
{
    public void showDebugOnlyDataSD()
    {
        try
        {
            SmartDashboard.updateValues();
        }
        catch (Exception e)
        {
            logE("Error while trying to show debug data to SmartDashboard.", e, "");
        }
        finally
        {

        }
    }
    private static void logE(String prefix, Exception e, String suffix)
    {
        System.err.println(prefix +". Message: "+e.getMessage() + "\nStackTrace: "+ e.getStackTrace());
    }
}