package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftUp extends Command {
    private double speed;
    public LiftUp(double speed) {
        this.speed = speed;
    }  
    @Override
    protected boolean isFinished() {
        return stopFinish;
    }
    protected void execute() {
        Robot.m_LiftingSubsystem.setSpeed(speed);
    }
    private boolean stopFinish = false;
    public void stopExecute() {
        Robot.m_LiftingSubsystem.setSpeed(0);
        stopFinish = true;
    }
}