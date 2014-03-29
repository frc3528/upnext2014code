 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3528.UpNext2014Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;

/**
 *
 * @author developer
 */
public class AutonomousTwoBallClose extends CommandGroup {
    
    public AutonomousTwoBallClose() {
        addSequential(new LowerPickerUpper());
        addParallel(new PickUpBall(2.6));
        addSequential(new DriveByFeet(RobotMap.DRIVE_DISTANCE, RobotMap.DRIVE_TIMEOUT, RobotMap.DRIVE_POWER));
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Fire());
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Cock());
        addSequential(new PickUpBall(1.4));
        addSequential(new RaisePickerUpper());
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new LowerPickerUpper());
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Fire());
        
    }
}
