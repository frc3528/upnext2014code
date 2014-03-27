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
public class AutonomousThreeBall extends CommandGroup {
    
    public AutonomousThreeBall() {
        
        
        addSequential(new LowerPickerUpper());
        addSequential(new Fire());
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Cock());
        addParallel(new PickUpBall(1.0));
        addSequential(new DriveByFeet(-2, RobotMap.DRIVE_TIMEOUT, -0.5));
        addSequential(new DriveByFeet(RobotMap.DRIVE_DISTANCE, RobotMap.DRIVE_TIMEOUT, RobotMap.DRIVE_POWER));
        addSequential(new SpitOutBall(0.1));
        addSequential(new Fire());
        addSequential(new Wait(RobotMap.TWO_BALL_WAIT));
        addSequential(new Cock());
        addSequential(new PickUpBall(1.0));
        addSequential(new Fire());
        
        }
}
