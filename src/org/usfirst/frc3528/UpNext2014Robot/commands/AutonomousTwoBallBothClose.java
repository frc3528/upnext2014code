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
public class AutonomousTwoBallBothClose extends CommandGroup {
    
    public AutonomousTwoBallBothClose() {
        addSequential(new LowerPickerUpper());
        addSequential(new PickUpBall(1.0));
        addSequential(new DriveByFeet(RobotMap.DRIVE_DISTANCE, RobotMap.DRIVE_TIMEOUT, RobotMap.DRIVE_POWER));
        addSequential(new SpitOutBall(1.0));
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Fire());
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Cock());
        addSequential(new PickUpBall(1.2));
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Fire());
        
    }
}
