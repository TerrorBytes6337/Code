package frc.robot.subsystems.PID;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDInfo
{
    public double p, i, d, f;
    public final String name;
    public PIDInfo(double p, double i, double d, double f, String name)
    {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.name = name;
    }
    public void getInfoFromDashboard()
    {
        p = getNum("p");
        i = getNum("i");
        d = getNum("d");
        f = getNum("f");
    }
    public void updateInfoToSmartDashboard()
    {
        putNum(p, "p");
        putNum(i, "i");
        putNum(d, "d");
        putNum(f, "f");
    }
    private void putNum(double val, String valName)
    {
        SmartDashboard.putNumber(name+valName, val);
    }
    private double getNum(String valName)
    {
        return SmartDashboard.getNumber(name+valName, 0d);
    }
}