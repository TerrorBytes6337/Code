package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class TriggerAsButton extends Trigger
{
    private static final double DEFAULT_TRIGGER_VALUE = 0.60;
    private final XboxController controller;
    private final Hand hand;
    public TriggerAsButton(final XboxController x, final Hand h)
    {
        controller = x;
        hand = h;
    }
    @Override
    public boolean get() {
        //TODO: remove this when finished debugging
        //System.out.println("Trigger of hand "+ hand.toString()+" has value:" +controller.getTriggerAxis(hand));
        return controller.getTriggerAxis(hand) > DEFAULT_TRIGGER_VALUE;
    }

}