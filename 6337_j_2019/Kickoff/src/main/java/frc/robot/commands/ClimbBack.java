package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbBack extends Command
{
    private boolean on;
    public ClimbBack()
    {
        on = false;

        // System.out.println("back climber up");
        // Value val = on ? Value.kForward: Value.kReverse;
        // Robot.m_ClimbingSubsystem.setBackLeg(val);
    }

    protected void execute()
    {
        if(on){
            on = false;
        }
        else{
            on = true;
        }
        
        System.out.println("back climber up: " + on);
        Value val = on ? Value.kForward: Value.kReverse;
        Robot.m_ClimbingSubsystem.setBackLeg(val);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}