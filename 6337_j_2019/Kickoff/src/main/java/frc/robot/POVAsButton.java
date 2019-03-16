package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class POVAsButton extends Trigger
{
    public static int lastValue = -1;
    private static int newValue = -1;
    public final int triggerPosition;
    public static int totalButtons = 0;
    private static int nIteratingButton = 0;
    public static int getPosition(int degree)
    {
        // if(degree<22||degree>(360-23))
        // {
        //     return 0;
        // }
        // else if(degree <23 + 45)
        // {
        //     return 1;
        // }
        // else if(degree < 23+ 45+ 45)
        // {
        //     return 2;
        // }
        // else if(degree < 23 + (45*2))
        // {
        //     return 3;
        // }
        // else if(degree < 23 )
        if(degree < 0) return -1;
        if(degree<22||degree>(360-23))
        {
            return 0;
        }
        else
        {
            for(int i = 1; i < 7; i++)
            {
                if(degree < 23 + (45 * i))
                {
                    return i;
                }
            }
            return 7;
        }
    }
    public POVAsButton(int triggerPos)
    {
        triggerPosition = triggerPos;
        totalButtons ++;
    }
    @Override
    public boolean get() {
        if(nIteratingButton == 0)
        {
            lastValue = newValue;
            newValue = getPosition(OI.XBOX_CONTROLLER.getPOV());
            if(newValue == -1)
            {
                System.out.println("__POV -1");
            }
        }
        nIteratingButton = (nIteratingButton + 1)%totalButtons;
        boolean returnVal = triggerPosition == newValue;
        if(lastValue != newValue && returnVal)
        {
            System.out.println("!POV " + triggerPosition);
        }
        return returnVal;
    }

}