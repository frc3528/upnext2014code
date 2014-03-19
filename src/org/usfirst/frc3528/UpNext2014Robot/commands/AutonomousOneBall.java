
package org.usfirst.frc3528.UpNext2014Robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
/**
 *
 */
public class  AutonomousOneBall extends CommandGroup {
    public AutonomousOneBall() {
        
        //addSequential(new CameraLightOn());
        //addSequential (new LowerPickerUpper());
        addSequential(new DriveByFeet(RobotMap.DRIVE_DISTANCE, RobotMap.DRIVE_TIMEOUT, RobotMap.DRIVE_POWER));
        //addParallel(new PickUpBall(0.1));
        addSequential (new LowerPickerUpper());
        addSequential(new Wait(RobotMap.ONE_BALL_WAIT));
        addSequential(new Fire());

    }
}
