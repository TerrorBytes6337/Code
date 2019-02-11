package frc.robot.subsystems;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Sublift extends Subsystem {
    private PWMSpeedController liftMotor = new Spark(RobotMap.LIFT_MOTOR);
    public PWMSpeedController liftGetter() {
        return liftMotor;
    }
    @Override
    protected void initDefaultCommand() {
        
}

}
