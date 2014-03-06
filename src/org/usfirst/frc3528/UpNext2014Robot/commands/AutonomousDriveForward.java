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
public class AutonomousDriveForward extends CommandGroup {
    
    public AutonomousDriveForward() {
        
        addSequential(new DriveByFeet(RobotMap.DRIVE_DISTANCE, RobotMap.DRIVE_TIMEOUT, RobotMap.DRIVE_POWER));
    }
}
