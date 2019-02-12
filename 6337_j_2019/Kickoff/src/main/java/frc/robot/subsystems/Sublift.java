package frc.robot.subsystems;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Sublift extends Subsystem {
    private PWMSpeedController liftMotor = new Spark(RobotMap.LIFT_MOTOR);
    private double negate = 0, positate = 0;
    public PWMSpeedController liftGetter() {
        return liftMotor;
    }
    @Override
    protected void initDefaultCommand() {
    }
    public void setSpeed(double input)
    {
        if(input >= 0)
        {
            positate = input;
        }
        if(input <= 0)
        {
            negate = input;
        }
        liftMotor.set((positate+negate)/2);
    }

}
