package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ClimbFront extends InstantCommand
{
    private boolean on;
    private static final boolean ON_BY_DEFAULT = false;
    public ClimbFront()
    {
        switch(Robot.m_ClimbingSubsystem.getBack())
        {
            case kForward:
                on = true;
                break;
            case kReverse:
                on = false;
                break;
            default:
                on = ON_BY_DEFAULT;
                break;
        }
        // System.out.println("front climber up");
        // Value val = on ? Value.kForward: Value.kReverse;
        // Robot.m_ClimbingSubsystem.setFrontLeg(val);
    }
    public ClimbFront(boolean on)
    {
        this.on = on;
    }
    protected void execute()
    {
        on = !on;
        
        System.out.println("front climber up: " + on);
        Value val = on ? Value.kForward: Value.kReverse;
        Robot.m_ClimbingSubsystem.setFrontLeg(val);
    }

}