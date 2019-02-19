package frc.robot.subsystems;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class CargoHandler extends Subsystem{
    private PWMSpeedController cargoLiftController = new Spark(RobotMap.CARGO_HANDLER_UP_DOWN);
    public PWMSpeedController cargoLift(){
        return cargoLiftController;
    }

    @Override
    protected void initDefaultCommand() {

    }

    private PWMSpeedController cargoBallController = new Spark(RobotMap.CARGO_HANDLER_IN_OUT);
    public PWMSpeedController cargoGetBall(){
        return cargoBallController;
    }

}


