/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.subsystems.PID.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;
  
  public static Driving m_DrivingSubsystem;
  // public static Sublift m_LiftingSubsystem;
  public static LiftingSubsystem m_LiftingSubsystem;
  public static CargoHandler cHandler;
  //public static CameraSubsystem m_CameraSubsystem;
  public static PanelPusher m_PanelSubsystem;
  public static Climber m_ClimbingSubsystem;
  //private static final Timer ROBOT_TIMER = new Timer();
  private double time;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    smartDashboardInit();
    SubsystemInit();

    MapUtilityButtons();
    AddUtilityCommands();
    System.out.println("End of robot Init");
  }
  
  private void AddUtilityCommands() {
    // Scheduler.getInstance().add(new Extern_UpdateCamSpecs(true));
  }

  private static void MapUtilityButtons()
  {
    // System.out.println("Mapping Camera Managing buttons");
    // OI.SWITCH_CAMERA_BUTTON.whenActive(new SwitchCamera());
    OI.AUTONOMOUS_LIFT_TOGGLE.whenActive(new ToggleAutonomousLiftUp());
    //Other debug buttons here
    // OI.EXTERN_0.whenActive(new Extern_UpdateCamSpecs());
  }
  //Assign commands to button events (only in teleop)
  private static void MapActionButtons()
  {    
    ClimbBack oClimbBack = new ClimbBack();
    ClimbFront oClimbFront = new ClimbFront();

    System.out.println("Mapping panel push button");
    OI.PANEL_BUTTON_CLOSE.whenPressed(new PushPanel(true));
    OI.PANEL_BUTTON_OPEN.whenReleased(new PushPanel(false));

    System.out.println("Mapping Climbing buttons");
    //TODO: while or when active?
    OI.CLIMBER_BACK_BUTTON.whenActive(oClimbBack);
    // OI.CLIMBER_BACK_BUTTON.whenReleased(new ClimbBack(false));
    OI.CLIMBER_FRONT_BUTTON.whenActive(oClimbFront);
    // OI.CLIMBER_FRONT_BUTTON.whenReleased(new ClimbFront(false));

    System.out.println("Mapping Lifting buttons");
    OI.LIFT_UP_BUTTON.whileHeld(new AutonomousLiftUp(1, true));
    OI.LIFT_UP_BUTTON.whenReleased(new AutonomousLiftUp(0.15, false));
    OI.LIFT_DOWN_BUTTON.whileHeld(new AutonomousLiftUp(-0.5, true));
    OI.LIFT_DOWN_BUTTON.whenReleased(new AutonomousLiftUp(0.15, false));

    System.out.println("Mapping Cargo Handler buttons");
    OI.CARGO_HANDLER_DOWN_BUTTON.whileActive(new CargoLift(0.5));
    OI.CARGO_HANDLER_DOWN_BUTTON.whenReleased(new CargoLift(0.1));
    OI.CARGO_HANDLER_UP_BUTTON.whileActive(new CargoLift(-0.5));
    OI.CARGO_HANDLER_UP_BUTTON.whenReleased(new CargoLift(0.1));

    OI.CARGO_HANDLER_IN_BUTTON.whileActive(new CargoGetBall(1));
    OI.CARGO_HANDLER_IN_BUTTON.whenReleased(new CargoGetBall(0));
    OI.CARGO_HANDLER_OUT_BUTTON.whileActive(new CargoGetBall(-0.5));
    OI.CARGO_HANDLER_OUT_BUTTON.whenReleased(new CargoGetBall(0));

    System.out.println("Adding arcade drive command to scheduler");
    Scheduler.getInstance().add(new ArcadeDrive(0.3,1));
  }
  
  private static void SubsystemInit()
  {
    System.out.println("LiftingSubsystem init");
    m_LiftingSubsystem = LiftingSubsystem.getInstance();

    System.out.println("CargoHandler init");
    cHandler = new CargoHandler();
    System.out.println("Driving system init");
    m_DrivingSubsystem = new Driving();
    // System.out.println("Camera subsystem init");
    // m_CameraSubsystem = new CameraSubsystem(RobotMap.PIXY_CAMERA_PORT, RobotMap.MS_CAMERA_PORT);
    // These stays
    System.out.println("PanelPusher init");
    m_PanelSubsystem = new PanelPusher();
    System.out.println("Climber init");
    m_ClimbingSubsystem = new Climber();

    System.out.println("Camera init");
    ReworkedCamera.getInstance();
    //CameraServer.getInstance().startAutomaticCapture();
    // Camera.getInstance();
    
  }
  private void smartDashboardInit() {
    // m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // // chooser.addOption("My Auto", new MyAutoCommand());
    // SmartDashboard.putData("Auto mode", m_chooser);


  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    //Update every 1 sec
    if(Timer.getFPGATimestamp() - time > 1.0)
    {
      ElevatorPID.getInstance().fetchValueFromSmartDashboard();
      time = Timer.getFPGATimestamp();
    }
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    System.out.println("Disabled init"); 
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.start();
    // }
    teleopInit();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // // This makes sure that the autonomous stops running when
    // // teleop starts running. If you want the autonomous to
    // // continue until interrupted by another command, remove
    // // this line or comment it out.
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }
    MapActionButtons();
    MapUtilityButtons();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}
