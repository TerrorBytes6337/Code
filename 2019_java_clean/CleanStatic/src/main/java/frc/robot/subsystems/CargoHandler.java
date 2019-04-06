package frc.robot.subsystems;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.RobotMap;

public class CargoHandler extends DebugSubsystem{
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

    @Override
    public void addInfoToSendable(SendableBuilder b) {
        addProperty(b, ".cargoLift.speed", cargoLiftController::get,
        cargoLiftController::set);
        addProperty(b, ".cargoBall.speed", cargoBallController::get,
        cargoBallController::set);
	}

}


