/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //#region built-in tutorials
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
  //#endregion
  
  //#region Ports
  public static final int PORT_XBOX_CONTROLLER = 0;
  public static final Hand LIFT_HAND = Hand.kRight;
  public static final int PANEL_PUSH_BUTTON_CLOSE = 10; //right joystick
  public static final int PANEL_PUSH_BUTTON_OPEN = 9; //left joystick
  public static final int CLIMBER_FRONT_BUTTON_NUM = 8; //Y button
  public static final int CLIMBER_BACK_BUTTON_NUM = 7; //A button
  public static final int LIFT_UP_BUTTON_NUM = 6; //Right side button bumper
  public static final int LIFT_DOWN_BUTTON_NUM = 5; //left side button bumper
  public static final int CARGO_HANDLER_IN = 3; //X button pull the ball in
  public static final int CARGO_HANDLER_OUT = 2; //B button shoot the ball out
  public static final int CARGO_HANDLER_UP = 1; //Y button, put the handler up
  public static final int CARGO_HANDLER_DOWN = 4; //A button, put the handler down
  //public static final int LIFT_DOWN_BUTTON_AXES = 3; //Right side trigger

  ////#endregion

  public static final XboxController XBOX_CONTROLLER;
  public static final Button PANEL_BUTTON_CLOSE;
  public static final Button PANEL_BUTTON_OPEN;
  public static final Button CLIMBER_FRONT_BUTTON;
  public static final Button CLIMBER_BACK_BUTTON;
  public static final Button LIFT_UP_BUTTON;
  public static final Button LIFT_DOWN_BUTTON;
  public static final Button CARGO_HANDLER_IN_BUTTON;
  public static final Button CARGO_HANDLER_OUT_BUTTON;
  public static final Button CARGO_HANDLER_UP_BUTTON;
  public static final Button CARGO_HANDLER_DOWN_BUTTON;
  //public static final TriggerAsButton LIFT_DOWN_BUTTON; //Technically treated like a button
  
  public static final TriggerAsButton AUTONOMOUS_LIFT_TOGGLE;

  // public static final POVAsButton EXTERN_0;
  // public static final POVAsButton EXTERN_1;
  // public static final POVAsButton EXTERN_2;
  // public static final POVAsButton EXTERN_3;
  // public static final POVAsButton EXTERN_4;
  // public static final POVAsButton EXTERN_5;
  // public static final POVAsButton EXTERN_6;
  // public static final POVAsButton EXTERN_7;
  static
  {
    XBOX_CONTROLLER = new XboxController(OI.PORT_XBOX_CONTROLLER);
    PANEL_BUTTON_CLOSE = new JoystickButton(XBOX_CONTROLLER, PANEL_PUSH_BUTTON_CLOSE);
    PANEL_BUTTON_OPEN = new JoystickButton(XBOX_CONTROLLER, PANEL_PUSH_BUTTON_OPEN);
    CLIMBER_FRONT_BUTTON = new JoystickButton(XBOX_CONTROLLER, CLIMBER_FRONT_BUTTON_NUM);
    CLIMBER_BACK_BUTTON = new JoystickButton(XBOX_CONTROLLER, CLIMBER_BACK_BUTTON_NUM);
    CARGO_HANDLER_IN_BUTTON = new JoystickButton(XBOX_CONTROLLER, CARGO_HANDLER_IN);
    CARGO_HANDLER_OUT_BUTTON = new JoystickButton(XBOX_CONTROLLER, CARGO_HANDLER_OUT);
    CARGO_HANDLER_UP_BUTTON = new JoystickButton(XBOX_CONTROLLER, CARGO_HANDLER_UP);
    CARGO_HANDLER_DOWN_BUTTON = new JoystickButton(XBOX_CONTROLLER, CARGO_HANDLER_DOWN);
    LIFT_UP_BUTTON = new JoystickButton(XBOX_CONTROLLER, LIFT_UP_BUTTON_NUM);
    LIFT_DOWN_BUTTON = new JoystickButton(XBOX_CONTROLLER, LIFT_DOWN_BUTTON_NUM);
    AUTONOMOUS_LIFT_TOGGLE = new TriggerAsButton(XBOX_CONTROLLER, Hand.kRight); //Right trigger
    // EXTERN_0 = new POVAsButton(0);
    // EXTERN_1 = new POVAsButton(1);
    // EXTERN_2 = new POVAsButton(2);
    // EXTERN_3 = new POVAsButton(3);
    // EXTERN_4 = new POVAsButton(4);
    // EXTERN_5 = new POVAsButton(5);
    // EXTERN_6 = new POVAsButton(6);
    // EXTERN_7 = new POVAsButton(7);
  }

}
