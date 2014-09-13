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
 * @author developer
 */
public class DriveByFeetSonar extends Command {
    
    private double distancefromwall;
    private double inches = 0;
    private double power = .5;
    private double timeout = 0;
    private double sonarDistance = 0;
    
    public DriveByFeetSonar(double distancefromwall, double timeout, double power) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.mecanumDrive);
        this.distancefromwall = distancefromwall;
        this.power = power;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
        //sonarDistance = Robot.mecanumDrive.getSonarPosition();
        
        setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("FEET = " + Robot.mecanumDrive.getSonarPosition());
        Robot.mecanumDrive.driveWithJoystick(0, -power, 0, 0); 

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((Robot.mecanumDrive.getSonarPosition() <= distancefromwall) || isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.mecanumDrive.driveWithJoystick(0, 0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
