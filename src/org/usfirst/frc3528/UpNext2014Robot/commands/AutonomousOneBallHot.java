
package org.usfirst.frc3528.UpNext2014Robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
/**
 *
 */
public class  AutonomousOneBallHot extends CommandGroup {
    public AutonomousOneBallHot() {
        
        //addSequential(new CameraLightOn());
        //addSequential (new LowerPickerUpper());
        addSequential(new DriveByFeet(13, 0.75));
        addSequential (new LowerPickerUpper());
        addSequential(new Wait(RobotMap.WAIT_BETWEEN_FIRE));
        addSequential(new Fire());

    }
}
