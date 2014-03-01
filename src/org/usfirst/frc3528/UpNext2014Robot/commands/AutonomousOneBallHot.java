
package org.usfirst.frc3528.UpNext2014Robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 */
public class  AutonomousOneBallHot extends CommandGroup {
    public AutonomousOneBallHot() {
        
        addSequential(new CameraLightOn());
        addSequential (new LowerPickerUpper());
        
        //if () {
            
        //} else {
            //addSequential(new DriveByFeet(12 , 0.75));

        //}


    }
}
