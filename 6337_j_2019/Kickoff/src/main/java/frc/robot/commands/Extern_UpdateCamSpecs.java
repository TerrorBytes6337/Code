// package frc.robot.commands;

// import edu.wpi.first.wpilibj.command.InstantCommand;
// import frc.robot.subsystems.ReworkedCamera;

// public class Extern_UpdateCamSpecs extends InstantCommand
// {
//     private final boolean reducedUpdate;
//     private static int timesCalled = 0;
//     private static final int callsPerUpdate = 10;
//     public Extern_UpdateCamSpecs()
//     {
//         reducedUpdate = false;
//     }
//     public Extern_UpdateCamSpecs(boolean reducedUpdate)
//     {
//         this.reducedUpdate = reducedUpdate;
//     }
//     public void execute()
//     {
//         System.out.println("Update cam called");
//         if(reducedUpdate)
//         {
//             if(timesCalled == 0)
//             {                
//                 System.out.println("Updating Cam Specs.");
//                 ReworkedCamera.getInstance().updateSpecs();
//             }
//             timesCalled = (timesCalled + 1) % callsPerUpdate;
//             return;
//         }
//         System.out.println("Updating Cam Specs.");
//         ReworkedCamera.getInstance().updateSpecs();
//     }
// }