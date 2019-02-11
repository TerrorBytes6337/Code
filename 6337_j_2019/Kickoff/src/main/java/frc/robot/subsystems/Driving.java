package frc.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.OI;
import frc.robot.RobotMap;
public class Driving extends Subsystem
{
    public final DifferentialDrive driver;
    private final Spark LEFT_CONTROLLER;
    private final Spark RIGHT_CONTROLLER;
    private static final Hand VERTICAL_HAND = Hand.kLeft;
    private static final Hand HORIZONTAL_HAND = Hand.kRight;
    public Driving()
    {
        LEFT_CONTROLLER = new Spark(RobotMap.PORT_LEFT_WHEEL_MOTOR);
        RIGHT_CONTROLLER = new Spark(RobotMap.PORT_RIGHT_WHEEL_MOTOR);
        driver = new DifferentialDrive(LEFT_CONTROLLER, RIGHT_CONTROLLER);
    }
    public Driving(boolean is4555)
    {
        LEFT_CONTROLLER = null;
        RIGHT_CONTROLLER = null;
        driver = null;
    }
    public Spark getLeftController()
    {
        return LEFT_CONTROLLER;
    }
    public Spark getRightController()
    {
        return RIGHT_CONTROLLER;
    }
    @Override
    protected void initDefaultCommand() {

    }
    public double getForwardXbox()
    {
        return OI.XBOX_CONTROLLER.getY(VERTICAL_HAND);
    }
    public double getRightXbox()
    {
        return OI.XBOX_CONTROLLER.getX(HORIZONTAL_HAND);
    }
}