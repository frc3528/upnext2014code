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
public class TeleopMovePickerUpper extends Command {
 
    
    public TeleopMovePickerUpper() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.pickerUpper);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
         double Arm = Robot.oi.controlStick.getRawAxis(3);
            if(Arm > 0.5){
                Robot.pickerUpper.Lower();
        }
            if(Arm < -0.5){
                Robot.pickerUpper.Raise();
        }
            if(Arm == 0){
                Robot.pickerUpper.DisableArm();
        }
    
            
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
