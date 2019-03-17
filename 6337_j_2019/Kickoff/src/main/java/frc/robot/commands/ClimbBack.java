package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ClimbBack extends InstantCommand
{
    private boolean on;
    public ClimbBack()
    {
        on = false;

        // System.out.println("back climber up");
        // Value val = on ? Value.kForward: Value.kReverse;
        // Robot.m_ClimbingSubsystem.setBackLeg(val);
    }
    public ClimbBack(boolean on)
    {
        this.on = on;
    }
    protected void execute()
    {
        on = !on;
        System.out.println("back climber up: " + on);
        Value val = on ? Value.kForward: Value.kReverse;
        Robot.m_ClimbingSubsystem.setBackLeg(val);
    }


}