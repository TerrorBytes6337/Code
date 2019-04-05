package frc.robot.subsystems.PID;

import java.util.Arrays;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;


//TODO: IMPLEMENT GOOD TELEOP/AUTONOMOUS
public class ElevatorPID extends DebugPIDSubsystem 
{	
	private static double minInput = 0.0;
	private static double maxInput = 3850.0;
	
	private SD_Number minOutput = new SD_Number(-0.8,"ElevatorPID.minOutput");
	private SD_Number maxOutput = new SD_Number(0.5,"ElevatorPID.maxOutput");
	private SD_Number absoluteTolerance = new SD_Number(4.0, "ElevatorPID.absoluteTolerance");
	
	//public static double highThreshold = 2500.0;
	
	//private static double[] setPoints = new double[6];
	private SD_NumArray setPoints = new SD_NumArray(new double[6], "ElevatorPID.setPoints");
	private static int currentSetPointIndex = 0;
	
	// public static double p = -0.008;
	// public static double i = 0.0;
	// public static double d = 0.0;
	// public static double f = 0.0;
	public SD_PIDInfo PIDInfo = new SD_PIDInfo(0,0,0,0);
	public static boolean REVERSE_DIRECTION = false;
	private static ElevatorPID instance;
	protected static final Spark elevatorMotor = new Spark(RobotMap.LIFT_MOTOR);

	//Encoder info
	private static final Encoder elevatorEncoder = new Encoder(RobotMap.LIFT_ENCODER1, RobotMap.LIFT_ENCODER2, REVERSE_DIRECTION, Encoder.EncodingType.k4X);
	private double PPR = 2048; //Pulses per revolution
	public void setPPR(double newPPR)
	{
		PPR = newPPR;
		updateLPP();
	}
	private SD_Number motorRadius = new SD_Number(1.5,"ElevatorPID.motorRadius");
	public void setMotorRadius(double motorRadius)
	{
		this.motorRadius = new SD_Number(motorRadius, "ElevatorPID.motorRadius");
		updateLPP();
	}
	private static double lengthPerPulse; //LPP
	public static double getLengthPerPulse()
	{
		return lengthPerPulse;
	}
	public void updateLPP()
	{
		lengthPerPulse = (motorRadius.value * Math.PI)/PPR;
		elevatorEncoder.setDistancePerPulse(lengthPerPulse);
	}

	public ElevatorPID() {
		super("ElevatorPID",0,0,0,0);
		initialize();
		updateLPP();
		updatePIDF();
	}

	public void updatePIDF()
	{
		getPIDController().setPID(PIDInfo.p, PIDInfo.i, PIDInfo.d, PIDInfo.f);
		System.out.println("PIDF system updated.");
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
		
		//TODO: set this to true when finished testing
		elevatorMotor.setSafetyEnabled(false);
		elevatorEncoder.reset();
		
		this.setAbsoluteTolerance(absoluteTolerance.value);
		this.getPIDController().setContinuous(false);
		this.setSetpoint(setPoints.value[0]);
		this.setInputRange(minInput, maxInput);
		this.setOutputRange(minOutput.value, maxOutput.value);
		this.enable();
		
		updateDisplayInfo();
	}
	
	public void initDefaultCommand() {
	}

    protected double returnPIDInput() {
    	updateDisplayInfo();
    	return elevatorEncoder.getDistance(); // returns the sensor value that is providing the feedback for the system
    }

    protected void usePIDOutput(double output) {
    	elevatorMotor.pidWrite(output); // this is where the computed output value from the PIDController is applied to the motor
    	updateDisplayInfo();
	}
	
	public void up() {
		currentSetPointIndex = (currentSetPointIndex < 3)? currentSetPointIndex + 1: 3;
		this.setSetpoint(setPoints.value[currentSetPointIndex]);
		updateDisplayInfo();
	}

	public void down() {
		currentSetPointIndex = (currentSetPointIndex > 0)? currentSetPointIndex - 1: 0;
		this.setSetpoint(setPoints.value[currentSetPointIndex]);
		updateDisplayInfo();
	}

	public void autonomousUp()
	{
		if(currentSetPointIndex < setPoints.value.length-1)
		{
			this.setSetpoint(setPoints.value[++currentSetPointIndex]);
			updateDisplayInfo();
		}
		else
		{
			System.err.println("Attempted to go beyond setpoint "+(setPoints.value.length-1));
		}
	}
	public void autonomousDown()
	{
		if(currentSetPointIndex > 0)
		{
			this.setSetpoint(setPoints.value[--currentSetPointIndex]);
			updateDisplayInfo();
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
		updateDisplayInfo();
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
		updateDisplayInfo();
	}
	public boolean goToNearestSetPoint()
	{
		if(setPoints == null) return false;
		double h = getCurrentElevatorHeight();
		double minDistance = Math.abs(h - setPoints.value[0]);
		int minI = 0;
		for(int i = 1; i < setPoints.value.length; i++)
		{
			double distance = Math.abs(h - setPoints.value[i]);
			if(distance<minDistance)
			{
				minI = i;
				minDistance = distance;
			}
		}
		setSetpoint(setPoints.value[minI]);
		return true;
	}
//SmartDashboard
	class SD_NumArray
	{
		public double[] value;
		public String name;
		public int lastLength;
		public SD_NumArray(double[] array, String name)
		{
			value = array;
			Arrays.sort(value);
			this.name = name;
			lastLength = value.length;
		}
		public void updateToSD()
		{
			int i = 0;
			SmartDashboard.putNumber(name+".length", value.length);
			//update value
			for(; i < value.length; i++)
			{
				SmartDashboard.putNumber(name+"["+i+"]", value[i]);
			}
			++i;
			//delete unnecessary values
			for(; i < lastLength; i++)
			{
				SmartDashboard.delete(name+"[" + i + "]");
			}
			//System.out.println(name+"updated values to SD.");
		}

		public void getInfoFromSD()
		{
			//lastLength = value.length;
			int newArrayLength = (int) (SmartDashboard.getNumber(name+".length", value.length));
			if(newArrayLength != value.length)
			{
				lastLength = value.length;
				value = new double[newArrayLength];
			}
			for(int i = 0; i < lastLength && i < value.length; i++)
			{
				value[i] = SmartDashboard.getNumber(name+"[" + i + "]", 0);
			}
			Arrays.sort(value);
			updateToSD();
			//System.out.println(name+" updated.");
		}
	}

	class SD_PIDInfo
	{
		public double p, i, d, f;
		public String name = "ElevatorPID.PIDF";
		public SD_PIDInfo(double p, double i, double d, double f)
		{
			this.p = p;
			this.i = i;
			this.d = d;
			this.f = f;
		}
		public void updateToSD()
		{
			SmartDashboard.putNumber(name+".p", p);
			SmartDashboard.putNumber(name+".i", i);
			SmartDashboard.putNumber(name+".d", d);
			SmartDashboard.putNumber(name+".f", f);
			// System.out.println(name+"updated values to SD.");
		}
		public void getInfoFromSD()
		{
			p = SmartDashboard.getNumber(name+".p", 0);
			i = SmartDashboard.getNumber(name+".i", 0);
			d = SmartDashboard.getNumber(name+".d", 0);
			f = SmartDashboard.getNumber(name+".f", 0);
			updateToSD();
			// System.out.println(name+" updated.");
		}
	}
	class SD_Number
	{
		public double value;
		public String name;
		public SD_Number(double v, String n)
		{
			value = v;
			name = n;
		}
		public void updateToSD()
		{
			SmartDashboard.putNumber(name, value);
			//System.out.println(name + " updated values to SD");
		}
		public void getInfoFromSD()
		{
			value = SmartDashboard.getNumber(name, 0);
			updateToSD();
			//System.out.println(name+" updated.");
		}
	}
    public void updateDisplayInfo(){
		//SmartDashboard.putData("elevatorEncoder", elevatorEncoder);

		// //These are called by getInfoFromSD()
		// setPoints.updateToSD();
		// PIDInfo.updateToSD();
		// minOutput.updateToSD();
		// maxOutput.updateToSD();
		// absoluteTolerance.updateToSD();
		SmartDashboard.putString("ElevatorPID.currentSetPoint", "["+currentSetPointIndex+"]: "+setPoints.value[currentSetPointIndex]);
		SmartDashboard.putNumber("ElevatorPID.getCurrentElevatorHeight()", getCurrentElevatorHeight());
		SmartDashboard.putNumber("ElevatorPID.minInput", minInput);
		SmartDashboard.putNumber("ElevatorPID.maxInput", maxInput);
	}
	
	public void fetchValueFromSmartDashboard()
	{
		//SetPoints update

		PIDInfo.getInfoFromSD();
		updatePIDF();

		minInput = setPoints.value[0];
		maxInput = setPoints.value[setPoints.value.length-1];
		this.setInputRange(minInput, maxInput);
		//System.out.println("ElevatorPID: Input range updated.");

		minOutput.getInfoFromSD();
		maxOutput.getInfoFromSD();
		this.setOutputRange(minOutput.value, maxOutput.value);
		//System.out.println("ElevatorPID: Output range updated");

		absoluteTolerance.getInfoFromSD();
		this.setAbsoluteTolerance(absoluteTolerance.value);
		//System.out.println("ElevatorPID: Absolute Tolerance updated.");

		motorRadius.getInfoFromSD();
		this.setMotorRadius(motorRadius.value);
		//System.out.println("ElevatorPID: Motor Radius updated.");

		setPoints.getInfoFromSD();
		//goToNearestSetPoint();
		updateDisplayInfo();
		System.out.println("ElevatorPID: values fetched");
	}
}