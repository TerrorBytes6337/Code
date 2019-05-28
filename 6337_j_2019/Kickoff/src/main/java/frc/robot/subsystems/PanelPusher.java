package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.RobotMap;
import frc.robot.commands.PushPanel;

public class PanelPusher extends //DebugSubsystem
Subsystem
{
    private final Solenoid pushSolenoid;
    private final Solenoid pullSolenoid;
    private final Compressor compressor = new Compressor();
    public PanelPusher()
    {
        super();
        pushSolenoid = new Solenoid(RobotMap.PANEL_PUSH);
        pullSolenoid = new Solenoid(RobotMap.PANEL_PULL);
    }
    public void push(boolean push)
    {        
        pushSolenoid.set(push);
        pullSolenoid.set(!push);
    }
    public void disablePusher()
    {
        pushSolenoid.set(false);
        pullSolenoid.set(false);
    }
    // public boolean pushCheck()
    // {
    //     return pushSolenoid.get();
    // }
    public boolean compressorEnabled()
    {
        return compressor.enabled();
    }
    // public boolean checkInit()
    // {
    //     if((compressor!=null && pushSolenoid != null && pullSolenoid != null))
    //     {
    //         System.out.println("Check init true");
    //         return true;
    //     }
    //     System.out.println("PanelPushing Subsystem is null?");
    //     return false;
    // }
    @Override
    protected void initDefaultCommand() {

    }
    // @Override
    // public void addInfoToSendable(SendableBuilder b) {
    //     addProperty(b, ".pushing", PushPanel::getPush, PushPanel::setPush);

	// }

}