/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3528.UpNext2014Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3528.UpNext2014Robot.Robot;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;

/**
 *
 * @author TeamUpNext
 */
public class AutonomousTwoBall extends CommandGroup {
    
    public AutonomousTwoBall() {
        
        addSequential(new LowerPickerUpper());
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Fire());
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Cock());
        addSequential(new PickUpBall(1.0));        
        addSequential(new RaisePickerUpper());
        addSequential(new DriveByFeet(RobotMap.DRIVE_DISTANCE, RobotMap.DRIVE_TIMEOUT, RobotMap.DRIVE_POWER));
        //addSequential(new DriveByFeet(11, 0.90));
        addSequential(new LowerPickerUpper());
        addSequential(new Fire());
    }
}
