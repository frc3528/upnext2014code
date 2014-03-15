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
        addParallel(new ReversePickerUpper(2.25));
        addSequential(new DriveByFeet(RobotMap.DRIVE_DISTANCE, RobotMap.DRIVE_TIMEOUT, RobotMap.DRIVE_POWER));
        addSequential(new Wait(RobotMap.WAIT_BETWEEN_FIRE));
        //addSequential(new Fire());
        //addSequential(new Wait(RobotMap.WAIT_BETWEEN_FIRE));
        //addSequential(new Cock());
        //addSequential(new ReversePickerUpper(1.0));
        //addSequential(new Wait(RobotMap.WAIT_BETWEEN_FIRE));
        //addSequential(new Fire());
        
    }
}
