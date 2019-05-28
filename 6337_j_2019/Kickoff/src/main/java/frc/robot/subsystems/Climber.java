package frc.robot.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.RobotMap;

public class Climber extends //DebugSubsystem
Subsystem
{
    private final DoubleSolenoid frontLeg;
    private final DoubleSolenoid backLeg;
    private static final Value DEFAULT_VALUE = Value.kReverse;
    public Climber()
    {
        super();
        frontLeg = new DoubleSolenoid(RobotMap.CLIMBER_FRONT_PULL, RobotMap.CLIMBER_FRONT_PUSH);
        backLeg = new DoubleSolenoid(RobotMap.CLIMBER_BACK_PULL, RobotMap.CLIMBER_BACK_PUSH);

        //set default value to be in, not out.
        frontLeg.set(DEFAULT_VALUE);
        backLeg.set(DEFAULT_VALUE);
    }
    @Override
    protected void initDefaultCommand() {

    }
    public void setFrontLeg(Value val)
    {
        frontLeg.set(val);
    }
    public Value getFront()
    {
        return frontLeg.get();
    }
    public Value getBack()
    {
        return backLeg.get();
    }
    public void setBackLeg(Value val)
    {
        backLeg.set(val);
    }
    public String fromValue(Value v)
    {
        switch(v)
        {
            case kForward:
                return "on";
            case kReverse:
                return "off";
            case kOff:
                return "disabled";
            default:
                return "?";
        }
    }
    public static final HashMap<String,Value> lookup = new HashMap<>();
    static
    {
        lookup.put("on", Value.kForward);
        lookup.put("off", Value.kReverse);
        lookup.put("disabled", Value.kOff);
    }
    public String getValueFront()
    {
        return fromValue(frontLeg.get());
    }
    public void setValueFront(String inp)
    {
        frontLeg.set(lookup.get(inp));
    }

    public String getValueBack()
    {
        return fromValue(backLeg.get());
    }    
    public void setValueBack(String inp)
    {
        backLeg.set(lookup.get(inp));
    }
    // @Override
    // public void addInfoToSendable(SendableBuilder b) {
    //     addProperty(b, ".frontLeg.value", this::getValueFront, this::setValueFront);
    //     addProperty(b, ".backLeg.value", this::getValueBack, this::setValueBack);
    // }
}