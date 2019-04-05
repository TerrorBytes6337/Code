package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.PID.ElevatorPID;

public class LiftingSubsystem extends ElevatorPID
{
    private static LiftingSubsystem instance;
    public final Sublift subliftInstance;
    private boolean isAutonomous;
    private boolean readyToOperate = false;
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
        // if(isAutonomous)
        // {
        //     //TeleopLift init
        //     this.disable();
        // }
        // else
        // {
        //     //AutonomousLift init
        //     this.enable();
        // }
        isAutonomous = !isAutonomous;
        this.getPIDController().setEnabled(isAutonomous);
    }

    public void sendUpSignal(double speed)
    {
        //go down
        if(isAutonomous)
        {
            if(readyToOperate)
            {
                autonomousDown();
                readyToOperate = false;
            }
        }
        else
        {
            subliftInstance.setSpeed(speed);
        }
    }
    public void sendReleaseSignal(double conteractSpeed)
    {
        readyToOperate = true;
    }
    public void sendDownSignal(double speed)
    {
        //go down
        if(isAutonomous)
        {
            if(readyToOperate)
            {
                autonomousDown();
                readyToOperate = false;
            }
        }
        else
        {
            subliftInstance.setSpeed(speed);
        }
    }
    @Override
    public void updateDisplayInfo()
    {
        super.updateDisplayInfo();
        SmartDashboard.putBoolean("LiftingSubsystem.isAuto()", isAutonomous);
        SmartDashboard.putBoolean("LiftingSubsystem.getController().enabled", this.getPIDController().isEnabled());
    }
    
}