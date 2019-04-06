package frc.robot.subsystems.PID;

import java.util.Arrays;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.helper.GS;
import frc.robot.Robot;
import frc.robot.RobotMap;


//TODO: IMPLEMENT GOOD TELEOP/AUTONOMOUS
public class ElevatorPID extends DebugPIDSubsystem 
{	
	private static GS<Double> minInput = new GS<Double>();
	private static GS<Double> maxInput = new GS<Double>();
	
	private GS<Double> minOutput = new GS<Double>();
	private GS<Double> maxOutput = new GS<Double>();
	private GS<Double> absoluteTolerance = new GS<Double>();
	
	//public static GS<Double> highThreshold = 2500.0;
	
	//private static double[] setPoints = new double[6];
	private GS<double[]> setPoints = new GS<double[]>();
	private static GS<Integer> currentSetPointIndex = new GS<Integer>();
	
	public static GS<Double> p = new GS<Double>();
	public static GS<Double> i = new GS<Double>();
	public static GS<Double> d = new GS<Double>();
	public static GS<Double> f = new GS<Double>();

	public static boolean REVERSE_DIRECTION = false;
	protected static final Spark elevatorMotor = new Spark(RobotMap.LIFT_MOTOR);

	//Encoder info
	private static final Encoder elevatorEncoder = new Encoder(RobotMap.LIFT_ENCODER1, RobotMap.LIFT_ENCODER2, REVERSE_DIRECTION, Encoder.EncodingType.k4X);
	private double PPR = 2048; //Pulses per revolution
	public void setPPR(double newPPR)
	{
		PPR = newPPR;
		updateLPP();
	}
	public double getPPR() {return PPR;}
	private double motorRadius = 1.5;
	public void setMotorRadius(double motorRadius)
	{
		this.motorRadius = motorRadius;
		updateLPP();
	}
	public double getMotorRadius()
	{
		return motorRadius;
	}
	private static double lengthPerPulse; //LPP
	public static double getLengthPerPulse()
	{
		return lengthPerPulse;
	}
	public void updateLPP()
	{
		//TODO: Since we are dividing by 2048 (exponent of 2)
		// we can hack it to [long] and bitwise shift it for a potential performance 
		//improve.
		lengthPerPulse = (motorRadius * Math.PI)/PPR; 
		elevatorEncoder.setDistancePerPulse(lengthPerPulse);
	}
	public void setDefaultValues()
	{
		minInput.set(0d);
		maxOutput.set(3850d);
		minOutput.set(-0.8);
		maxOutput.set(0.5);
		absoluteTolerance.set(4.0);
		setPoints.set(new double[6]);
		currentSetPointIndex.set(0);
		p.set(-0.008);
		i.set(0d);
		d.set(0d);
		f.set(0d);
	}
	public ElevatorPID() {
		super("ElevatorPID",0,0,0,0);
		initialize();
		updateLPP();
		updatePIDF();
	}

	public void updatePIDF()
	{
		// getPIDController().setPID(PIDInfo.p, PIDInfo.i, PIDInfo.d, PIDInfo.f);
		getPIDController().setPID(p.get(),i.get(),d.get(),f.get());
		System.out.println("PIDF system updated.");
	}

	public void resetEncoder() {
		elevatorEncoder.reset();
	}
	
	private void initialize() {
		
		//TODO: set this to true when finished testing
		elevatorMotor.setSafetyEnabled(false);
		elevatorEncoder.reset();
		
		// this.setAbsoluteTolerance(absoluteTolerance.value);
		// this.getPIDController().setContinuous(false);
		// this.setSetpoint(setPoints.value[0]);
		// this.setInputRange(minInput, maxInput);
		// this.setOutputRange(minOutput.value, maxOutput.value);
		// this.enable();

		this.setAbsoluteTolerance(absoluteTolerance.get());
		this.getPIDController().setContinuous(false);
		this.setSetpoint(setPoints.get()[0]);
		this.setInputRange(minInput.get(), maxInput.get());
		this.setOutputRange(minOutput.get(), maxOutput.get());
		this.enable();
		
		//updateDisplayInfo();
	}
	
	public void initDefaultCommand() {
	}

    protected double returnPIDInput() {
    	//updateDisplayInfo();
    	return elevatorEncoder.getDistance(); // returns the sensor value that is providing the feedback for the system
    }

    protected void usePIDOutput(double output) {
    	elevatorMotor.pidWrite(output); // this is where the computed output value from the PIDController is applied to the motor
    	//updateDisplayInfo();
	}
	
	// public void up() {
	// 	currentSetPointIndex = (currentSetPointIndex < 3)? currentSetPointIndex + 1: 3;
	// 	this.setSetpoint(setPoints.get()[currentSetPointIndex]);
	// 	//updateDisplayInfo();
	// }

	// public void down() {
	// 	currentSetPointIndex = (currentSetPointIndex > 0)? currentSetPointIndex - 1: 0;
	// 	this.setSetpoint(setPoints.get()[currentSetPointIndex]);
	// 	//updateDisplayInfo();
	// }

	public void autonomousUp()
	{
		if(currentSetPointIndex.get() < (setPoints.get().length-1))
		{
			currentSetPointIndex.set(currentSetPointIndex.get()+1);
			this.setSetpoint(setPoints.get()[currentSetPointIndex.get()]);
			//updateDisplayInfo();
		}
		else
		{
			System.err.println("Attempted to go beyond setpoint "+(setPoints.get().length-1));
		}
	}
	public void autonomousDown()
	{
		if(currentSetPointIndex.get() > 0)
		{
			currentSetPointIndex.set(currentSetPointIndex.get()-1);
			this.setSetpoint(setPoints.get()[currentSetPointIndex.get()]);
			//updateDisplayInfo();
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
		//updateDisplayInfo();
	}
	
	private void disablePID() {
		if(this.getPIDController().isEnabled()) {
			this.disable();
		}
	}
	
	//TODO: this might not reflect the actual current elevator height
	public double getCurrentElevatorHeight() {
		return elevatorEncoder.getDistance(); 
	}
	
 
	public void stop() {
		elevatorMotor.stopMotor();
		//updateDisplayInfo();
	}
	public boolean goToNearestSetPoint()
	{
		if(setPoints == null) return false;
		double h = getCurrentElevatorHeight();
		double minDistance = Math.abs(h - setPoints.get()[0]);
		int minI = 0;
		for(int i = 1; i < setPoints.get().length; i++)
		{
			double distance = Math.abs(h - setPoints.get()[i]);
			if(distance<minDistance)
			{
				minI = i;
				minDistance = distance;
			}
		}
		setSetpoint(setPoints.get()[minI]);
		return true;
	}
	@Override
	public void addInfoToSendable(SendableBuilder b) {
		addProperty(b, ".minInput", minInput::get, minInput::set);
		addProperty(b, ".maxInput", maxInput::get, maxInput::set);
		addProperty(b, ".minOutput", minOutput::get, minOutput::set);
		addProperty(b, ".maxOutput", maxOutput::get, maxOutput::set);
		addProperty(b, ".absoluteTolerance", absoluteTolerance::get, absoluteTolerance::set);
		addProperty(b, ".setPoints", setPoints);
		addProperty(b, ".currentSetPointIndex", currentSetPointIndex::get, currentSetPointIndex::set);
		addProperty(b, ".motorRadius", this::getMotorRadius, this::setMotorRadius);
		addProperty(b, ".p", p::get, p::set);
		addProperty(b, ".i", i::get, i::set);
		addProperty(b, ".d", d::get, d::set);
		addProperty(b, ".f", f::get, f::set);
	}
	//#region old code
// //SmartDashboard
// 	class SD_NumArray
// 	{
// 		public double[] value;
// 		public String name;
// 		public int lastLength;
// 		public SD_NumArray(double[] array, String name)
// 		{
// 			value = array;
// 			Arrays.sort(value);
// 			this.name = name;
// 			lastLength = value.length;
// 		}
// 		public void updateToSD()
// 		{
// 			int i = 0;
// 			SmartDashboard.putNumber(name+".length", value.length);
// 			//update value
// 			for(; i < value.length; i++)
// 			{
// 				SmartDashboard.putNumber(name+"["+i+"]", value[i]);
// 			}
// 			++i;
// 			//delete unnecessary values
// 			for(; i < lastLength; i++)
// 			{
// 				SmartDashboard.delete(name+"[" + i + "]");
// 			}
// 			//System.out.println(name+"updated values to SD.");
	// }

// 		public void getInfoFromSD()
// 		{
// 			//lastLength = value.length;
// 			int newArrayLength = (int) (SmartDashboard.getNumber(name+".length", value.length));
// 			if(newArrayLength != value.length)
// 			{
// 				lastLength = value.length;
// 				value = new double[newArrayLength];
// 			}
// 			for(int i = 0; i < lastLength && i < value.length; i++)
// 			{
// 				value[i] = SmartDashboard.getNumber(name+"[" + i + "]", 0);
// 			}
// 			Arrays.sort(value);
// 			updateToSD();
// 			//System.out.println(name+" updated.");
// 		}
// 	}

// 	class SD_PIDInfo
// 	{
// 		public double p, i, d, f;
// 		public String name = "ElevatorPID.PIDF";
// 		public SD_PIDInfo(double p, double i, double d, double f)
// 		{
// 			this.p = p;
// 			this.i = i;
// 			this.d = d;
// 			this.f = f;
// 		}
// 		public void updateToSD()
// 		{
// 			SmartDashboard.putNumber(name+".p", p);
// 			SmartDashboard.putNumber(name+".i", i);
// 			SmartDashboard.putNumber(name+".d", d);
// 			SmartDashboard.putNumber(name+".f", f);
// 			// System.out.println(name+"updated values to SD.");
// 		}
// 		public void getInfoFromSD()
// 		{
// 			p = SmartDashboard.getNumber(name+".p", 0);
// 			i = SmartDashboard.getNumber(name+".i", 0);
// 			d = SmartDashboard.getNumber(name+".d", 0);
// 			f = SmartDashboard.getNumber(name+".f", 0);
// 			updateToSD();
// 			// System.out.println(name+" updated.");
// 		}
// 	}
// 	class SD_Number
// 	{
// 		public double value;
// 		public String name;
// 		public SD_Number(double v, String n)
// 		{
// 			value = v;
// 			name = n;
// 		}
// 		public void updateToSD()
// 		{
// 			SmartDashboard.putNumber(name, value);
// 			//System.out.println(name + " updated values to SD");
// 		}
// 		public void getInfoFromSD()
// 		{
// 			value = SmartDashboard.getNumber(name, 0);
// 			updateToSD();
// 			//System.out.println(name+" updated.");
// 		}
// 	}
//     public void //updateDisplayInfo(){
// 		//SmartDashboard.putData("elevatorEncoder", elevatorEncoder);

// 		// //These are called by getInfoFromSD()
// 		// setPoints.updateToSD();
// 		// PIDInfo.updateToSD();
// 		// minOutput.updateToSD();
// 		// setPoints.updateToSD();
// 		// absoluteTolerance.updateToSD();
// 		SmartDashboard.putString("ElevatorPID.currentSetPoint", "["+currentSetPointIndex+"]: "+setPoints.value[currentSetPointIndex]);
// 		SmartDashboard.putNumber("ElevatorPID.getCurrentElevatorHeight()", getCurrentElevatorHeight());
// 		SmartDashboard.putNumber("ElevatorPID.minInput", minInput);
// 		SmartDashboard.putNumber("ElevatorPID.maxInput", maxInput);
// 	}
	
// 	public void fetchValueFromSmartDashboard()
// 	{
// 		//SetPoints update

// 		PIDInfo.getInfoFromSD();
// 		updatePIDF();

// 		minInput = setPoints.value[0];
// 		maxInput = setPoints.value[setPoints.value.length-1];
// 		this.setInputRange(minInput, maxInput);
// 		//System.out.println("ElevatorPID: Input range updated.");

// 		minOutput.getInfoFromSD();
// 		maxOutput.getInfoFromSD();
// 		this.setOutputRange(minOutput.value, maxOutput.value);
// 		//System.out.println("ElevatorPID: Output range updated");

// 		absoluteTolerance.getInfoFromSD();
// 		this.setAbsoluteTolerance(absoluteTolerance.value);
// 		//System.out.println("ElevatorPID: Absolute Tolerance updated.");

// 		motorRadius.getInfoFromSD();
// 		this.setMotorRadius(motorRadius.value);
// 		//System.out.println("ElevatorPID: Motor Radius updated.");

// 		setPoints.getInfoFromSD();
// 		//goToNearestSetPoint();
// 		//updateDisplayInfo();
// 		System.out.println("ElevatorPID: values fetched");
// 	}
//#endregion
}