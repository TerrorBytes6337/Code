package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.AcceleratableMotor;
import frc.robot.Robot;

/**
 * Must be used after @InitDifferentialDrive
 */
public class ArcadeDrive extends Command
{
    private static final double TIMEOUT = 1; //1 second
    private static final double DEFAULT_INCREMENT = 0.30;
    private boolean stopCalled = false;
    private boolean accelerate;
    private double lastXSpeed = 0;
    //private double lastZRotation = 0;
    private double increment;
    private double lastTime;
    public ArcadeDrive()
    {
        // super(TIMEOUT);
        super();
        this.accelerate = false;
        lastTime = timeSinceInitialized();
    }
    public ArcadeDrive(boolean accelerate)
    {
        super();
        this.accelerate = accelerate;
        if(accelerate)
        {
            this.increment = DEFAULT_INCREMENT;
        }
        lastTime = timeSinceInitialized();
    }
    public ArcadeDrive(double accelerateIncrement)
    {
        super();
        this.increment = accelerateIncrement;
        this.accelerate = true;
        lastTime = timeSinceInitialized();
    }
    protected void execute()
    {        
        double xSpeed = Robot.m_DrivingSubsystem.getForwardXbox();
        double zRotation = Robot.m_DrivingSubsystem.getRightXbox();
        if(accelerate)
        {
            double thisTime = timeSinceInitialized();
            //Replace this with the accelerateNoTimeConfiguration if doesn't work
            double newSpeed = AcceleratableMotor.accelerateValue(lastXSpeed, increment*xSpeed, 1, thisTime-lastTime);
            //end replacable region
            lastTime = thisTime;
            Robot.m_DrivingSubsystem.driver.arcadeDrive(newSpeed, zRotation);
            lastXSpeed = newSpeed;
        }
        else
        {
            Robot.m_DrivingSubsystem.driver.arcadeDrive(xSpeed
            , zRotation);
        }
    }
    public boolean isFinished()
    {
        
        return 
        //this.isCanceled() || this.isCompleted() 
        // || this.isTimedOut() 
        //||
         stopCalled;
    }
    public void stopExecuting()
    {
        stopCalled = true;
    }

}