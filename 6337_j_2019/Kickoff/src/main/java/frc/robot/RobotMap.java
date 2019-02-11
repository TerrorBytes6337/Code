/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //PWM Ports

  //Driving Subsystem
  public static final int PORT_LEFT_WHEEL_MOTOR = 0;
  public static final int PORT_RIGHT_WHEEL_MOTOR = 1;

  //Lifting Subsystem
  public static final int LIFT_MOTOR = 2;

  //Cargo Handler Subsystem
  public static final int LEFT_CARGO_HANDLER = 3;
  public static final int RIGHT_CARGO_HANDLER = 4;
  
  //Solenoid

  //Panel Pushing Subsystem
  public static final int PANEL_PUSH = 0;
  public static final int PANEL_PULL = 1;
  public static final int CLIMBER_FRONT_PUSH = 2;
  public static final int CLIMBER_FRONT_PULL = 3;
  public static final int CLIMBER_BACK_PUSH = 4;
  public static final int CLIMBER_BACK_PULL = 5;

  //USB Ports

  public static final int MS_CAMERA_PORT = 0;
  public static final int PIXY_CAMERA_PORT = 1;
}
