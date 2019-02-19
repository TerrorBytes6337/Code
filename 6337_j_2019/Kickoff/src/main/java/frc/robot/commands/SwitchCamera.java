package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Camera;

public class SwitchCamera extends Command
{
    private final Camera cam;
	public SwitchCamera()
    {
        cam = Camera.getInstance();
        requires(cam);
    }
    public void execute()
    {
        System.out.println("Switching Camera");
        cam.switchCamera();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }

}