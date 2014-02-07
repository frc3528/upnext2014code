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
public class Fire extends CommandGroup {
    
    public Fire() {
        addSequential(new UnLatch());
        addParallel(new Jiggle());
        addSequential(new DisengageWinch());

    }
}
