package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbFront extends Command
{
    public ClimbFront(boolean on)
    {
        Value val = on ? Value.kForward: Value.kReverse;
        Robot.m_ClimbingSubsystem.setFrontLeg(val);
    }
    @Override
    protected boolean isFinished() {
        return true;
    }

}