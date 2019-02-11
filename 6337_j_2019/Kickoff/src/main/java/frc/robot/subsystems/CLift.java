package frc.robot.subsystems;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CLift extends Subsystem{
    private PWMSpeedController cargoLiftController = new Spark(RobotMap.LEFT_CARGO_HANDLER);
    public PWMSpeedController cargoLeft(){
        return cargoLiftController;
    }

    @Override
    protected void initDefaultCommand() {

    }

    private PWMSpeedController cargoRightController = new Spark(RobotMap.RIGHT_CARGO_HANDLER);
    public PWMSpeedController cargoRight(){
        return cargoRightController;
    }

}


