/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3528.UpNext2014Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3528.UpNext2014Robot.Robot;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;

/**
 *
 * @author TeamUpNext
 */
public class LowerPickerUpper extends Command {
    
    
    public LowerPickerUpper() {
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.catapult);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //setTimeout(.075);
        Robot.pickerUpper.lower();
        if(Robot.pickerUpper.armDown() == false){
            RobotMap.SAFE = true;
            
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.pickerUpper.armDown();
    }

    // Called once after isFinished returns true
    protected void end() {
        //Robot.pickerUpper.disableArm();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
