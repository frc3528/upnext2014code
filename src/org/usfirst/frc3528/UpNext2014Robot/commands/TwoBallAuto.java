/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3528.UpNext2014Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3528.UpNext2014Robot.Robot;

/**
 *
 * @author TeamUpNext
 */
public class TwoBallAuto extends CommandGroup {
    
    public TwoBallAuto() {
        
        addSequential(new LowerPickerUpper());
        addSequential(new Wait(0.6));
        addSequential(new FireWithoutJiggle());
        if (Robot.catapult.winchLimit() == true) {
           addSequential(new FireWithJiggle()); 
        }
        addSequential(new Wait(0.7));
        addSequential(new Cock());
        addSequential(new ReversePickerUpper());
        addSequential(new RaisePickerUpper());
        addParallel(new StopPickerUpper());
        addSequential(new DriveByFeet(12, 0.75));
        addSequential(new LowerPickerUpper());
        addSequential(new Wait(0.6));
        addSequential(new FireWithoutJiggle());
        if (Robot.catapult.winchLimit() == true) {
           addSequential(new FireWithJiggle()); 
         }
    }
}
