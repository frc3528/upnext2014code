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
public class AutonomousDriveForward extends CommandGroup {
    
    public AutonomousDriveForward() {
        
        addSequential(new DriveByFeet(12, 0.75));
    }
}
