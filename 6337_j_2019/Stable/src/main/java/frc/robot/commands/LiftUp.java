package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftUp extends Command {

    private double speed;
    private boolean stopFinish = false;

    public LiftUp(double speed) {
        this.speed = speed;
    }  

    @Override
    protected boolean isFinished() {
        stopExecute();
        return stopFinish;
    }

    protected void execute() {
        if(speed > 0){
            Robot.m_LiftingSubsystem.setPositive(speed);
        }else if (speed < 0){
            Robot.m_LiftingSubsystem.setNegative(speed);
        }    
    }
    

    public void stopExecute() {
        if(speed == 0){
            Robot.m_LiftingSubsystem.stop();
            stopFinish = true;
        }
    }
}