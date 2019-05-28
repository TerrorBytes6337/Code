// package frc.robot.subsystems;

// import edu.wpi.cscore.UsbCamera;
// import edu.wpi.cscore.VideoSink;
// import edu.wpi.cscore.VideoSource.ConnectionStrategy;
// import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.wpilibj.command.Subsystem;

// public class Camera extends Subsystem
// {
//     private final UsbCamera camHandler;
//     // = CameraServer.getInstance().startAutomaticCapture("HandlerCam", 0);
//     private final UsbCamera camDrive;
//     // = CameraServer.getInstance().startAutomaticCapture("DriveCam", 1);
//     private final VideoSink sink;
//     private boolean currentlyShowingDrive;
//     public boolean isCurrentlyShowingDrive(){return currentlyShowingDrive;}
//     private static Camera instance;

//     private Camera(){
//         camHandler = CameraServer.getInstance().startAutomaticCapture(1);
//         camDrive = CameraServer.getInstance().startAutomaticCapture(0);
//         sink = CameraServer.getInstance().getServer(); //USB Camera 0 as source
//         System.out.println("sink: "+sink.getName()+"; source: "+ sink.getSource().getName());
//         init();
//     }

//     public static Camera getInstance()
//     {
//         if(instance == null){
//             instance = new Camera();
//         }

//         return instance;
//     }
//     public UsbCamera getDefaultCamera()
//     {
//         return camDrive;
//     }
//     private void init(){

//         camHandler.setResolution(640, 480);
//         camDrive.setResolution(640, 480);

//         camHandler.setFPS(30);
//         camDrive.setFPS(30);

//         camHandler.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
//         camDrive.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
        
//         showCameraDrive();
//     }

//     @Override
//     public void initDefaultCommand(){
//         //do nothing
//     }

//     public void switchCamera()
//     {
//         if(currentlyShowingDrive)
//         {
//             showCameraHandler();
//         }
//         else
//         {
//             showCameraDrive();
//         }
//     }
//     private void showCameraHandler(){
//         sink.setSource(camHandler);
//         currentlyShowingDrive = false;
//     }

//     private void showCameraDrive(){
//         sink.setSource(camDrive);
//         currentlyShowingDrive = true;
//     }

// }


