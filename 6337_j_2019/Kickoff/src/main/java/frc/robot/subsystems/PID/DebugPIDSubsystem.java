package frc.robot.subsystems.PID;

import java.util.HashMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public abstract class DebugPIDSubsystem extends PIDSubsystem
{
    public static final HashMap<Integer, Integer> identifierNInstancesPairs = new HashMap<>();
    public DebugPIDSubsystem(String name, double p, double i, double d, double f)
    {
        super(name, p, i, d, f);
    }
}