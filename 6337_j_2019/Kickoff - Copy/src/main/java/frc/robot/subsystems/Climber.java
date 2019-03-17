package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Climber extends Subsystem
{
    private final DoubleSolenoid frontLeg;
    private final DoubleSolenoid backLeg;
    public Climber()
    {
        frontLeg = new DoubleSolenoid(RobotMap.CLIMBER_FRONT_PULL, RobotMap.CLIMBER_FRONT_PUSH);
        backLeg = new DoubleSolenoid(RobotMap.CLIMBER_BACK_PULL, RobotMap.CLIMBER_BACK_PUSH);
    }
    @Override
    protected void initDefaultCommand() {

    }
    public void setFrontLeg(Value val)
    {
        frontLeg.set(val);
    }
    public void setBackLeg(Value val)
    {
        backLeg.set(val);
    }
}