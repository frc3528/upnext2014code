
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
        addSequential(new DriveByFeet(RobotMap.DRIVE_DISTANCE, RobotMap.DRIVE_TIMEOUT, RobotMap.DRIVE_POWER));
        addSequential (new LowerPickerUpper());
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Fire());

    }
}
