// package frc.robot.subsystems;

// import edu.wpi.cscore.UsbCamera;
// import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.helper.GS;
// //TODO: [ENDGAME] Add multiple cameras support
// public class ReworkedCamera extends DebugSubsystem
// {
//     // public class CameraSpecifications
//     // {
//     //     private static final String FRAMERATE_STR = "CamFramerate",
//     //                                 SCALE_STR = "CamRezScale";
//     //     public double framerate = 60;
//     //     public double scale = 1;
//     //     public void putDataToDashboard()
//     //     {
//     //         SmartDashboard.putNumber(FRAMERATE_STR, framerate);
//     //         SmartDashboard.putNumber(SCALE_STR, scale + 1);
//     //     }
//     //     public void updateFromDashboard(final UsbCamera cam)
//     //     {
//     //         getDataFromDashboard();
//     //         applyToCamera(cam);
//     //     }
//     //     public void getDataFromDashboard()
//     //     {
//     //         framerate = SmartDashboard.getNumber(FRAMERATE_STR,60.0f);
//     //         scale = SmartDashboard.getNumber(SCALE_STR, 2) - 1;
//     //     }
//     //     public void applyToCamera(final UsbCamera cam)
//     //     {
//     //         cam.setFPS((int) framerate);
//     //         cam.setResolution(160 * (1<<(int) scale), 90 * (1<<(int) scale));
//     //     }
//     // }
//     private final UsbCamera mainCam;
//     private static GS<Integer> SCALER = new GS<Integer>();
//     private static GS<Integer> FRAMERATE = new GS<Integer>();
//     static
//     {
//         SCALER.set(0);
//         FRAMERATE.set(30);
//     }
//     // private CameraSpecifications camSpecs;
//     public ReworkedCamera()
//     {
//         super();
//         mainCam = CameraServer.getInstance().startAutomaticCapture();
//         // camSpecs = new CameraSpecifications();
//         // camSpecs.putDataToDashboard();
//         // camSpecs.updateFromDashboard(mainCam);
//         mainCam.setFPS(FRAMERATE.get());
//         mainCam.setResolution(160 * (1 <<SCALER.get()), 90 * (1 << SCALER.get()));
//     }
//     @Override
//     protected void initDefaultCommand() {

//     }
//     public void updateSpecs()
//     {
//         // camSpecs.updateFromDashboard(mainCam);
//     }

//     @Override
//     public void addInfoToSendable(SendableBuilder b) {
//         addProperty(b, ".targetFramerate", FRAMERATE::get, FRAMERATE::set);
//         addProperty(b, ".scaler", SCALER::get, SCALER::set);
// 	}

// }