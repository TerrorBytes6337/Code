package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSubsystem extends Subsystem
{
    private static final String PIXYCAM_NAME = "pixy";
    private static final String MSCAM_NAME = "microsoft";
    private UsbCamera pixyCam;
    private UsbCamera msCam;

    public CameraSubsystem(int pixyPort, int msCamPort)
    {
        //pixyCam = new UsbCamera(PIXYCAM_NAME, pixyPort);
        msCam = new UsbCamera(MSCAM_NAME, msCamPort);
        CameraServer.getInstance().startAutomaticCapture(msCam);
        //Handle pixycam

    }
    @Override
    protected void initDefaultCommand() {
    }
}