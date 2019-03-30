package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.LiftingSubsystem;

public class ToggleAutonomousLiftUp extends InstantCommand
{
    public void execute()
    {
        LiftingSubsystem.getInstance().toggleAutonomousLift();
        //TODO: DEBUG
        System.out.println("ToggleAutonomousLiftUp: toggled " + (LiftingSubsystem.getInstance().isAuto()?"on" : "off"));
    }
}