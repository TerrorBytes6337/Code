package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.PID.ElevatorPID;

public class LiftingSubsystem extends ElevatorPID
{
    private static LiftingSubsystem instance;
    public final Sublift subliftInstance;
    private boolean isAutonomous;
    private boolean readyToOperate = false;
    public boolean isReadyToOperate() {return readyToOperate;}
    public void setReadyToOperate(boolean val) 
    {
        readyToOperate = val;
    }
    public boolean isAuto()
    {
        return isAutonomous;
    }
    private void setAuto(boolean isAuto)
    {
        isAutonomous = isAuto;
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
        /*if(isAutonomous)
        {
            if(readyToOperate)
            {
                autonomousDown();
                readyToOperate = false;
            }
        }
        else
        {*/
            subliftInstance.setSpeed(speed);
        //}
    }
    public void sendReleaseSignal(double conteractSpeed)
    {
        readyToOperate = true;
    }
    public void sendDownSignal(double speed)
    {
        //go down
     /*   if(isAutonomous)
        {
            if(readyToOperate)
            {
                autonomousDown();
                readyToOperate = false;
            }
        }
        else
        {*/
            subliftInstance.setSpeed(speed);
        //}
    }
    @Override
    public void addInfoToSendable(SendableBuilder b)
    {
        super.addInfoToSendable(b);
        addProperty(b, ".isAuto", this::isAuto, this::setAuto);
        addProperty(b, ".controller.enabled", this.getPIDController()::isEnabled,
        this.getPIDController()::setEnabled);
        addProperty(b, ".needsToReleaseButton", this::isReadyToOperate, 
        this::setReadyToOperate);
        addProperty(b, ".teleopLiftSpeed", subliftInstance::getSpeed, subliftInstance::setSpeed);
    }
    // public void updateDisplayInfo()
    // {
    //     super.updateDisplayInfo();
    //     SmartDashboard.putBoolean("LiftingSubsystem.isAuto()", isAutonomous);
    //     SmartDashboard.putBoolean("LiftingSubsystem.getController().enabled", this.getPIDController().isEnabled());
    // }
    
}