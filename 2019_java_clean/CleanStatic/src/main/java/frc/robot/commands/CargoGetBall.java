package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CargoGetBall extends Command {
    private double speed;

    public CargoGetBall(double speed){
        super();
        this.speed = speed;
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    public void execute(){
        Robot.m_cargoHandlerSubsystem.cargoGetBall().set(speed);
        //Robot.cHandler.cargoRight().set(-1 * speed);
    }
}