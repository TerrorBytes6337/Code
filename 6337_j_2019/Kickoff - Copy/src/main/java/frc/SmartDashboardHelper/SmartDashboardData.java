package frc.SmartDashboardHelper;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardData<T extends DashboardReadyData>
{
    public T data;
    private final String displayName;
    public SmartDashboardData(String name)
    {
        displayName = name;
    }
    public void updateData(T newValue)
    {
        data = newValue;
        SmartDashboard.putString(displayName, data.toString());
    }
    public Object getModifiedData()
    {
        return data.fromString(SmartDashboard.getString(displayName, ""));
    }
}