package frc.buttons;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class TriggerButton extends Trigger
{
    private static final double DEFAULT_TRIGGER_VALUE = 0.60;
    private final XboxController controller;
    private final Hand hand;
    public TriggerButton(final XboxController x, final Hand h)
    {
        controller = x;
        hand = h;
    }
    @Override
    public boolean get() {
        return controller.getTriggerAxis(hand) > DEFAULT_TRIGGER_VALUE;
    }
}