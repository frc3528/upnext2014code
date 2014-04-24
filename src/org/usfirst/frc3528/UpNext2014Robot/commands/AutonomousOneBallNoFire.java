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
public class AutonomousOneBallNoFire extends CommandGroup {
    
    public AutonomousOneBallNoFire() {
        addSequential(new DriveByFeet(RobotMap.DRIVE_DISTANCE, RobotMap.DRIVE_TIMEOUT, RobotMap.DRIVE_POWER));
        addParallel(new PickUpBall(0.1));
        addSequential (new LowerPickerUpper());
        addSequential(new Wait(RobotMap.ONE_BALL_WAIT));
    }
}