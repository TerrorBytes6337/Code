package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.RobotMap;
import frc.robot.commands.PushPanel;

public class PanelPusher extends //DebugSubsystem
Subsystem
{
    private final DoubleSolenoid pushSolenoid;
    private static final Value DEFAULT_VALUE = Value.kReverse;
    //private final DoubleSolenoid pullSolenoid;
    
    private final Compressor compressor = new Compressor();
    public PanelPusher()
    {
        super();
        pushSolenoid = new DoubleSolenoid(RobotMap.PANEL_PUSH, RobotMap.PANEL_PULL);
        //pullSolenoid = new DoubleSolenoid(RobotMap.PANEL_PULL);
        pushSolenoid.set(DEFAULT_VALUE);
    }
    

    public void push(boolean push)
    {        
        //boolean pushSolenoidState;
        

        //pushSolenoid.set(); FIND SOMETHING TO PUT 


        //boolean pullSolenoidState;

        //pushSolenoidState = pushSolenoid.get();
        //pullSolenoidState = pullSolenoid.get();
        //pushSolenoidState = PushPanel.getPush();

        //System.out.println("in push method pull set to " + pullSolenoidState);
        
       // if(pushSolenoidState){
            //PushPanel.setPush(false);
           
            //pullSolenoid.set(true);
            //System.out.println("in push method push set to " + pushSolenoidState);
       // }
       // else{
            //PushPanel.setPush(true);
            //pushSolenoid.set(true);
            //pullSolenoid.set(false);
           // System.out.println("in push method push set to " + pushSolenoidState);
        //}
        //PushPanel.setPush(!pushSolenoidState);

        //pushSolenoid.set(!pushSolenoidState);
        //pullSolenoid.set(!pullSolenoidState);
    }
    public void disablePusher()
    {
        System.out.println("in disablePusher");
        //pushSolenoid.set(false);
        //pullSolenoid.set(true);
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