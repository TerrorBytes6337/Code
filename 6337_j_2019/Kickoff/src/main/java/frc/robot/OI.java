/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedController;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Robot;
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  public static final int PORT_XBOX_CONTROLLER = 0;
  public static final Hand LIFT_HAND = Hand.kRight;
  public static final int PANEL_PUSH_BUTTON_NUM = 2; //B button
  public static final int CLIMBER_FRONT_BUTTON_NUM = 4; //Y button
  public static final int CLIMBER_BACK_BUTTON_NUM = 1; //A button
  public static final int CARGO_HANDLER_BUTTON_NUM = 3; //X button
  public static final int LIFT_UP_BUTTON_NUM = 6; //Right side button
  public static final int LIFT_DOWN_BUTTON_AXES = 3; //Right side trigger

  public static final XboxController XBOX_CONTROLLER;
  public static final Button PANEL_PUSH_BUTTON;
  public static final Button CLIMBER_FRONT_BUTTON;
  public static final Button CLIMBER_BACK_BUTTON;
  public static final Button CARGO_HANDLER_BUTTON;
  public static final Button LIFT_UP_BUTTON;
  public static final TriggerAsButton LIFT_DOWN_BUTTON; //Technically treated like a button
  static
  {
    XBOX_CONTROLLER = new XboxController(OI.PORT_XBOX_CONTROLLER);
    PANEL_PUSH_BUTTON = new JoystickButton(XBOX_CONTROLLER, PANEL_PUSH_BUTTON_NUM);
    CLIMBER_FRONT_BUTTON = new JoystickButton(XBOX_CONTROLLER, CLIMBER_FRONT_BUTTON_NUM);
    CLIMBER_BACK_BUTTON = new JoystickButton(XBOX_CONTROLLER, CLIMBER_BACK_BUTTON_NUM);
    CARGO_HANDLER_BUTTON = new JoystickButton(XBOX_CONTROLLER, CARGO_HANDLER_BUTTON_NUM);
    LIFT_UP_BUTTON = new JoystickButton(XBOX_CONTROLLER, LIFT_UP_BUTTON_NUM);
    LIFT_DOWN_BUTTON = new TriggerAsButton(XBOX_CONTROLLER, LIFT_HAND);
  }

}

