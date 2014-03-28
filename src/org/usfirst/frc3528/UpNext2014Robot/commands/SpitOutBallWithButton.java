/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3528.UpNext2014Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3528.UpNext2014Robot.Robot;

/**
 *
 * @author TeamUpNext
 */
public class SpitOutBallWithButton extends Command {
    
    public SpitOutBallWithButton() {
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.pickerUpper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //Robot.pickerUpper.spitOutBall();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Robot.pickerUpper.buttonForward(Robot.oi.pickerUpperWheelsForward.get());
        Robot.pickerUpper.spitOutBall();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; 
    }

    // Called once after isFinished returns true
    protected void end() {
        //Robot.pickerUpper.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.pickerUpper.stop();
    }
}
