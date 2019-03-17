package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//TODO: [ENDGAME] Add multiple cameras support
public class ReworkedCamera extends Subsystem
{
    // public class CameraSpecifications
    // {
    //     private static final String FRAMERATE_STR = "CamFramerate",
    //                                 SCALE_STR = "CamRezScale";
    //     public double framerate = 60;
    //     public double scale = 1;
    //     public void putDataToDashboard()
    //     {
    //         SmartDashboard.putNumber(FRAMERATE_STR, framerate);
    //         SmartDashboard.putNumber(SCALE_STR, scale + 1);
    //     }
    //     public void updateFromDashboard(final UsbCamera cam)
    //     {
    //         getDataFromDashboard();
    //         applyToCamera(cam);
    //     }
    //     public void getDataFromDashboard()
    //     {
    //         framerate = SmartDashboard.getNumber(FRAMERATE_STR,60.0f);
    //         scale = SmartDashboard.getNumber(SCALE_STR, 2) - 1;
    //     }
    //     public void applyToCamera(final UsbCamera cam)
    //     {
    //         cam.setFPS((int) framerate);
    //         cam.setResolution(160 * (1<<(int) scale), 90 * (1<<(int) scale));
    //     }
    // }
    private static ReworkedCamera instance;
    public synchronized static ReworkedCamera getInstance()
    {
        if(instance == null)
        {
            instance = new ReworkedCamera();
        }
        return instance;
    }
    private final UsbCamera mainCam;
    private static final int SCALER = 0;
    private static final int FRAMERATE = 60;
    // private CameraSpecifications camSpecs;
    public ReworkedCamera()
    {
        mainCam = CameraServer.getInstance().startAutomaticCapture();
        // camSpecs = new CameraSpecifications();
        // camSpecs.putDataToDashboard();
        // camSpecs.updateFromDashboard(mainCam);
        mainCam.setFPS(FRAMERATE);
        mainCam.setResolution(160 * (1 <<SCALER), 90 * (1 << SCALER));
    }
    @Override
    protected void initDefaultCommand() {

    }
    public void updateSpecs()
    {
        // camSpecs.updateFromDashboard(mainCam);
    }

}