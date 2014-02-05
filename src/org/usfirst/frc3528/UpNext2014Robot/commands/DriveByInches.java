/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3528.UpNext2014Robot.commands;

import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3528.UpNext2014Robot.Robot;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;

/**
 *
 * @author TeamUPNext
 */
public class DriveByInches extends Command {
    
    public DriveByInches() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.mecanumDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.mecanumDrive.driveByInches(RobotMap.frontLeftMotor, 18.84);
        Robot.mecanumDrive.driveByInches(RobotMap.frontRightMotor, 18.84);
        Robot.mecanumDrive.driveByInches(RobotMap.backLeftMotor, 18.84);
        Robot.mecanumDrive.driveByInches(RobotMap.backRightMotor, 18.84);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        try{
            System.out.println("FR = " + RobotMap.frontLeftMotor.getPosition());
         }catch (CANTimeoutException ex) {
            System.out.println("--- Error Printing Encoder ---");
                ex.printStackTrace();
             }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
