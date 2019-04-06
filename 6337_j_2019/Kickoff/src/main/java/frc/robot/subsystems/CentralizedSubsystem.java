package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class CentralizedSubsystem extends Subsystem
{
    public static ArrayList<Subsystem> allSubsys = new ArrayList<Subsystem>();
    public CentralizedSubsystem()
    {
        allSubsys.add(this);
    }
}