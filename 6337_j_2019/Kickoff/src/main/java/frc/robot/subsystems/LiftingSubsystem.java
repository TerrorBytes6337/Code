package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.PID.ElevatorPID;

public class LiftingSubsystem extends ElevatorPID
{
    private static LiftingSubsystem instance;
    public Sublift subliftInstance;
    private boolean isAutonomous;
    public boolean isAuto()
    {
        return isAutonomous;
    }
    public static LiftingSubsystem getInstance()
    {
        if(instance == null)
        {
            instance = new LiftingSubsystem();
        }
        return instance;
    }
    public LiftingSubsystem()
    {
        super();
        subliftInstance = new Sublift(ElevatorPID.elevatorMotor);
        isAutonomous = false;
    }
    
    public void toggleAutonomousLift()
    {
        if(isAutonomous)
        {
            //TeleopLift init
            this.disable();
            isAutonomous = false;
        }
        else
        {
            //AutonomousLift init
            this.enable();
            isAutonomous = true;
        }
    }
    
    @Override
    protected void updateDisplayInfo()
    {
        super.updateDisplayInfo();
        SmartDashboard.putBoolean("LiftingSubsystem.isAuto()", isAutonomous);

    }
    
}