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
public class Cock extends CommandGroup {
    
    public Cock() {
        addSequential(new LowerPickerUpper());
        addSequential(new EngageWinch());
        addSequential(new RunWinch());
        addSequential(new Latch());
        

    }
}
