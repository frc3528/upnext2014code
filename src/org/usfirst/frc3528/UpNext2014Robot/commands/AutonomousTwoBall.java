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
        //addSequential(new ReversePickerUpper(0.1));
        addSequential(new Wait(RobotMap.WAIT_BETWEEN_FIRE));
        addSequential(new Fire());
        addSequential(new Wait(RobotMap.WAIT_BETWEEN_FIRE));
        addSequential(new Cock());
        addSequential(new ReversePickerUpper(1.0));
        addSequential(new RaisePickerUpper());
        addSequential(new DriveByFeet(13, RobotMap.DRIVE_POWER));
        //addSequential(new DriveByFeet(11, 0.90));
        addSequential(new LowerPickerUpper());
        addSequential(new Fire());
    }
}
