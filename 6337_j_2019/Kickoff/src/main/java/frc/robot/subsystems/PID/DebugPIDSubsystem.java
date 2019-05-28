package frc.robot.subsystems.PID;

import java.util.HashMap;
import java.util.function.*;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder.BooleanConsumer;
import frc.helper.GS;

public abstract class DebugPIDSubsystem extends CentralizedPIDSubsystem
{
    public DebugPIDSubsystem(String name, double p, double i, double d, double f)
    {
        super(name, p, i, d, f);
    }
    public abstract void addInfoToSendable(SendableBuilder builder);
    @Override
    public final void initSendable(SendableBuilder builder)
    {
        super.initSendable(builder);
        addInfoToSendable(builder);
    }
    //#region COMPILER HACK
    public void addProperty(SendableBuilder b, String name, BooleanSupplier bSup, BooleanConsumer bCon)
    {
        b.addBooleanProperty(name, bSup, bCon);
    }
    public void addProperty(SendableBuilder b, String name, DoubleSupplier sup, DoubleConsumer con)
    {
        b.addDoubleProperty(name, sup, con);
    }
    // public void addProperty(SendableBuilder b, String name, IntSupplier sup, IntConsumer con)
    // {
    //     // DoubleSupplier dSup = (DoubleSupplier) sup;
    //     // DoubleConsumer dCon = (DoubleConsumer) con;
    //     // b.addDoubleProperty(name, dSup, dCon);
    // }    
    public void addProperty(SendableBuilder b, String name, Supplier<String> sup, Consumer<String> con)
    {
        b.addStringProperty(name, sup, con);
    }
    public void addProperty(SendableBuilder b, String name, GS<double[]> gsDouble)
    {
        b.addDoubleArrayProperty(name, gsDouble::get, gsDouble::set);
    }
    //#endregion
}