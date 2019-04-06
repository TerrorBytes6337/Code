package frc.robot.subsystems.PID;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.subsystems.CentralizedSubsystem;

public abstract class CentralizedPIDSubsystem extends PIDSubsystem
{
    public CentralizedPIDSubsystem(String name, double p, double i, double d, double f)
    {
        super(name,p,i,d,f);
        CentralizedSubsystem.allSubsys.add(this);
    }
    public CentralizedPIDSubsystem(double p, double i, double d, double f)
    {
        super(p,i,d,f);
        CentralizedSubsystem.allSubsys.add(this);
    }    
    public CentralizedPIDSubsystem(double p, double i, double d)
    {
        super(p,i,d);
        CentralizedSubsystem.allSubsys.add(this);
    }
}