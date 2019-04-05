package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class DebugSubsystem extends Subsystem
{
    public static int nInstance = 0;
    public DebugSubsystem()
    {
        super();
        nInstance++;
    }
}