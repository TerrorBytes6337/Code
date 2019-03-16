package frc.robot.subsystems.PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class ElevatorPID extends PIDSubsystem 
{
	
	
	private static double minInput = 0.0;
	private static double maxInput = 3850.0;
	
	private static double minOutput = -0.8;
	private static double maxOutput = 0.5;
	private static double absoluteTolerance = 4.0;
	
	public double highTreshold = 2500.0;
	
	private static double[] setPoints = new double[6];
	private static int currentSetPointIndex = 0; 
	
	private static double p = -0.008;
	private static double i = 0.0;
	private static double d = 0.0;
	private static double f = 0.0;
	private static final boolean REVERSE_DIRECTION = false;
	private static ElevatorPID instance;
	private static final Spark elevatorMotor = new Spark(RobotMap.LIFT_MOTOR);
	private static final Encoder elevatorEncoder = new Encoder(RobotMap.LIFT_ENCODER1, RobotMap.LIFT_ENCODER2, REVERSE_DIRECTION, Encoder.EncodingType.k4X);
	
	public ElevatorPID() {
		super("ElevatorPID",p,i,d,f);
		initialize();
	}
	
	public void resetEncoder() {
		elevatorEncoder.reset();
	}

	public static ElevatorPID getInstance() {
		if (instance == null) {
			instance = new ElevatorPID();
		}
		return instance;
	}
	
	private void initialize() {
		
		elevatorMotor.setSafetyEnabled(false);
		elevatorEncoder.setDistancePerPulse(1);
		elevatorEncoder.reset();
		
		this.setAbsoluteTolerance(absoluteTolerance);
		this.getPIDController().setContinuous(false);
		this.setSetpoint(setPoints[0]);
		this.setInputRange(minInput, maxInput);
		this.setOutputRange(minOutput, maxOutput);
		this.enable();
		
		addInfoToDashBoard();
	}
	
	public void initDefaultCommand() {
	}

    protected double returnPIDInput() {
    	addInfoToDashBoard();
    	return elevatorEncoder.getDistance(); // returns the sensor value that is providing the feedback for the system
    }

    protected void usePIDOutput(double output) {
    	elevatorMotor.pidWrite(output); // this is where the computed output value from the PIDController is applied to the motor
    	addInfoToDashBoard();
	}
	
	public void up() {
		currentSetPointIndex = (currentSetPointIndex < 3)? currentSetPointIndex + 1: 3;
		this.setSetpoint(setPoints[currentSetPointIndex]);
		addInfoToDashBoard();
	}

	public void down() {
		currentSetPointIndex = (currentSetPointIndex > 0)? currentSetPointIndex - 1: 0;
		this.setSetpoint(setPoints[currentSetPointIndex]);
		addInfoToDashBoard();
	}

	public void autonomousUp()
	{
		if(currentSetPointIndex < setPoints.length-1)
		{
			this.setSetpoint(setPoints[++currentSetPointIndex]);
			addInfoToDashBoard();
		}
		else
		{
			System.err.println("Attempted to go beyond setpoint "+(setPoints.length-1));
		}
	}
	public void autonomousDown()
	{
		if(currentSetPointIndex > 0)
		{
			this.setSetpoint(setPoints[--currentSetPointIndex]);
			addInfoToDashBoard();
		}
		else
		{
			System.err.println("Attempted to go below setpoint 0");
		}
	}
	public boolean isComplete() {
		return this.onTarget();
	}
	
	public void teleopUp(double speed) {
		this.disablePID();
		elevatorMotor.set((REVERSE_DIRECTION? -speed:speed));
		addInfoToDashBoard();
	}
	
	private void disablePID() {
		if(this.getPIDController().isEnabled()) {
			this.disable();
		}
	}
	
	public double getCurrentElevatorHeight() {
		return elevatorEncoder.getDistance(); 
	}
	
 
	public void stop() {
		elevatorMotor.stopMotor();
		addInfoToDashBoard();
	}
	
    private void addInfoToDashBoard(){
    	SmartDashboard.putData("elevatorEncoder", elevatorEncoder);
    }
}