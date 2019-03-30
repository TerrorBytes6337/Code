package frc.robot.reporter;

public class ReportData<T>
{
    private T[] dataArr;
    private T referenceValue;
    private T tolerance;
    private static final int DEFAULT_ARRAY_LENGTH = 156;
    private int dataRecorded = 0;
    private T onePercentValue;
    public void recordValue(T value)
    {
        
    }
}