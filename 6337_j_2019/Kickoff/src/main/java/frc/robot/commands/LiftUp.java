package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class LiftUp extends InstantCommand {

    protected double speed;

    public LiftUp(double speed) {
        this.speed = speed;
    }  

    protected void execute() {
        if(speed > 0){
            Robot.m_LiftingSubsystem.subliftInstance.setPositive(speed);
        }else if (speed < 0){
            Robot.m_LiftingSubsystem.subliftInstance.setNegative(speed);
        }
        else
        {
            Robot.m_LiftingSubsystem.subliftInstance.setSpeed(0);
        }
    }
}