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
 * @author TeamUpNext
 */
public class Jiggle extends CommandGroup {
    
    public Jiggle() {
        
        for (int i=0; i<= RobotMap.JIGGLE_COUNT; i++){
            addSequential(new JiggleForward());
            addSequential(new JiggleBackward());
        }
    }
}
