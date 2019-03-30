package frc.robot.subsystems;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Sublift extends Subsystem {
    public final Spark liftMotor;
    private double negate = 0, positate = 0;
    public Sublift()
    {
        System.out.println("I'm in the sub");
        liftMotor = new Spark(RobotMap.LIFT_MOTOR);
    }

    public Sublift(final Spark motor)
    {
        liftMotor = motor;
    }
    public Spark liftGetter() {
        return liftMotor;
    }
    @Override
    protected void initDefaultCommand() {
    }
    public void setPositive(double i)
    {
        positate = i;
        negate = 0;

        processInput();
    }
    
    public void setNegative(double i)
    {
        positate = 0;
        negate = i;
        processInput();
    }

    public void setSpeed(double input)
    {
        if(input >= 0)
        {
            positate = input;
        }
        if(input <= 0)
        {
            negate = input;
        }
        processInput();
    }

    public void stop(){

        negate = 0;
        positate = 0;
        processInput();
    }

	private void processInput() {
        liftMotor.set((positate + negate));
    }

}
