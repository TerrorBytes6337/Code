package frc.robot;

import java.util.Iterator;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilderImpl;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.CentralizedSubsystem;

public class DebugRobot extends Robot
{
    @Override
    public void smartDashboardInit()
    {
        super.smartDashboardInit();
        //Add all subsystems to smartdashboard
        Iterator<Subsystem> subsysIter = CentralizedSubsystem.allSubsys.iterator();
        SendableBuilderImpl builder = new SendableBuilderImpl();
        while(subsysIter.hasNext())
        {
            subsysIter.next().initSendable(builder);
            //Do something with the builder
        }

    }
}