package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class POVAsButton extends Trigger
{
    public static int lastValue = -1;
    public final int triggerPosition;
    public POVAsButton(int triggerPos)
    {
        triggerPosition = triggerPos;
    }
    @Override
    public boolean get() {
        int newValue = OI.XBOX_CONTROLLER.getPOV()/45;
        if(lastValue != newValue)
        {
            lastValue = newValue;
            return true;
        }
        else
        {
            return false;
        }
    }

}