package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class PanelPusher extends Subsystem
{
    private final Solenoid pushSolenoid;
    private final Solenoid pullSolenoid;
    private final Compressor compressor = new Compressor();
    public PanelPusher()
    {
        System.out.println("Compressor is null = " + (compressor == null));
        pushSolenoid = new Solenoid(RobotMap.PANEL_PUSH);
        System.out.println("PushSolenoid is null = " + (pushSolenoid == null));
        pullSolenoid = new Solenoid(RobotMap.PANEL_PULL);
        System.out.println("PullSolenoid is null = " + (pushSolenoid == null));
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
    public boolean checkInit()
    {
        if((compressor!=null && pushSolenoid != null && pullSolenoid != null))
        {
            System.out.println("Check init true");
            return true;
        }
        System.out.println("PanelPushing Subsystem is null?");
        return false;
    }
    @Override
    protected void initDefaultCommand() {

    }

}