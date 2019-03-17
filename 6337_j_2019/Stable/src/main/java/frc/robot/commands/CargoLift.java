package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class CargoLift extends InstantCommand {
    private double speed;

    public CargoLift(double speed){
        super();
        this.speed = speed;
    }

    public void execute(){
        Robot.cHandler.cargoLift().set(speed);
        //Robot.cHandler.cargoRight().set(-1 * speed);
    }
}