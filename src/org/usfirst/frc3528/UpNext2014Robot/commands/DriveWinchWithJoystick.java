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
public class DriveWinchWithJoystick extends Command {
    
    public DriveWinchWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.catapult);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double power = Robot.oi.testingStick.getRawAxis(5);
        Robot.catapult.drive(power);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return false;
        return Robot.catapult.winchLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.catapult.stop();
    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
