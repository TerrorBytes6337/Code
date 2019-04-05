package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class TriggerAsButton extends Trigger
{
    private static final double DEFAULT_TRIGGER_VALUE = 0.60;
    private final XboxController controller;
    private final Hand hand;
    // //TODO: remove after debug
    // private boolean lastGet = false;
    public TriggerAsButton(final XboxController x, final Hand h)
    {
        controller = x;
        hand = h;
    }
    @Override
    public boolean get() {
        boolean returnVal = controller.getTriggerAxis(hand) > DEFAULT_TRIGGER_VALUE;
        //TODO: remove after debug
        //System.out.println(hand == Hand.kLeft? "!Left trigger!": "!Right trigger!");
        // if(returnVal && lastGet == false)
        // {   
        //     //TODO: remove this when finished debugging
        //     System.out.println(hand == Hand.kLeft? "!Left trigger!": "!Right trigger!");
        // }
        // //TODO: remove after debug
        // if(lastGet != returnVal)
        // {
        //     String status = returnVal? "!":"_";
        //     String name = hand == Hand.kLeft? "Left trigger": "Right trigger";
        //     System.out.println(status + name + status);
        // }
        // lastGet = returnVal;
        return returnVal;
    }

}