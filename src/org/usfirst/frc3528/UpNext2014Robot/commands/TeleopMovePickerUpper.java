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
public class TeleopMovePickerUpper extends Command {
 
    boolean armOut = false;
    
    public TeleopMovePickerUpper() {
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.pickerUpper);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    //boolean safe = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
         double armin = Robot.oi.controlStick.getRawAxis(3);
         double armout =  Robot.oi.controlStick.getRawAxis(2);
         
         System.out.println("lefttrigger" + Robot.oi.controlStick.getRawAxis(2) + "------ righttrigger" + Robot.oi.controlStick.getRawAxis(3));
         
         
            if(armout > 0.75){
                Robot.pickerUpper.lower();
                armOut = true;
        }
            
            if(armOut){
                if(Robot.pickerUpper.armDown() == false){
                    RobotMap.SAFE = true; 
            } 
        }
                    
        
            if(armin > 0.75){
                Robot.pickerUpper.raise();
                armOut = false;
                RobotMap.SAFE = false;
        }
            if(armin == 0 || armout == 0){
                Robot.pickerUpper.stop();
                Robot.pickerUpper.disableArm();
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
