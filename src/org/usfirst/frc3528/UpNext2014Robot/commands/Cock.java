/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3528.UpNext2014Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author TeamUpNext
 */
public class Cock extends CommandGroup {
    
    public Cock() {
        //addSequential(new EngageWinch());
        addSequential(new LowerPickerUpper());
        addParallel(new EngageWinch());
        addSequential(new DriveWinchWithJoystick());
        addSequential(new Latch());
        //addSequential(new DisengageWinch());

    }
}
