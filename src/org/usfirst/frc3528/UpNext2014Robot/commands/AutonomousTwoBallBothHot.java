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
public class AutonomousTwoBallBothHot extends CommandGroup {
    
    public AutonomousTwoBallBothHot() {
        
        /*
        if (  == true) {
         addSequential(new DriveSideways(2, 0.5));
        } else {
         addSequential(new DriveSideways(-2, 0.5));    
        }
        */
        
        addSequential(new LowerPickerUpper());
        addSequential(new Wait(0.3));
        addSequential(new Fire());
        addSequential(new Wait(0.3));
        addSequential(new Cock());
        addSequential(new ReversePickerUpper(1.5));
        addSequential(new Wait(0.3));
        addSequential(new Fire());
        addSequential(new DriveByFeet(12, RobotMap.DRIVE_POWER));

        
        
        //addSequential(new RaisePickerUpper());
        //addParallel(new StopPickerUpper());
        //addSequential(new DriveByFeet(12, 0.75));
        //addSequential(new LowerPickerUpper());
        //addSequential(new Wait(0.6));
        //addSequential(new Fire());
        
    }
}
