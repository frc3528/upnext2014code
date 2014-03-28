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
public class AutonomousTesting extends CommandGroup {
    
    public AutonomousTesting() {
        addSequential(new LowerPickerUpper());
        addParallel(new PickUpBall(2.25));
        addSequential(new DriveByFeet(RobotMap.DRIVE_DISTANCE, RobotMap.DRIVE_TIMEOUT, RobotMap.DRIVE_POWER));
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        //addParallel(new PickUpBall(0.15));
        addSequential(new Fire());
        //addParallel(new PickUpBall(0.15));
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Cock());
        addParallel(new PickUpBall(1.5));
        addSequential(new DriveByFeet(-0.4, RobotMap.DRIVE_TIMEOUT, -RobotMap.DRIVE_POWER));
        addSequential(new DriveByFeet(0.4, RobotMap.DRIVE_TIMEOUT, RobotMap.DRIVE_POWER));
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        //addSequential(new Fire());
        
    }
}
