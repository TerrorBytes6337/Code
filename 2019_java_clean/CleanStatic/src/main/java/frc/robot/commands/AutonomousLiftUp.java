package frc.robot.commands;

import frc.robot.subsystems.LiftingSubsystem;
import frc.robot.subsystems.PID.ElevatorPID;

public class AutonomousLiftUp extends LiftUp
{
    boolean changeAutoLevel;
    public AutonomousLiftUp(double speed, boolean changeSetPoint)
    {
        super(speed);
        changeAutoLevel = changeSetPoint;
    }

    @Override
    protected void execute()
    {
        if(LiftingSubsystem.getInstance().isAuto())
        {
            //New implementation for elevatorPID
            if(changeAutoLevel)
            {
                if(speed>0)
                {
                    //TODO: DEBUG
                    System.out.println("AutonomousLiftUp: Executing autonomousUp().");
                    LiftingSubsystem.getInstance().autonomousUp();
                }
                else if(speed < 0)
                {
                    //TODO: DEBUG
                    System.out.println("AutonomousLiftUp: Executing autonomousDown().");
                    LiftingSubsystem.getInstance().autonomousDown();
                }
            }
        }
        else
        {
            System.out.println("super.execute()");
            super.execute();
        }
    }
    @Override
    protected boolean isFinished(){
        return true;
    } 
}