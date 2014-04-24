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
public class DriveByFeet extends Command {
    
    double distance;
    private double encoderCounts = 0;
    private double inches = 0;
    private double power = .5;
    private double initialFrontRight = 0;
    private double initialFrontLeft = 0;
    private double initialBackRight = 0;
    private double initialBackLeft = 0;
    private double angle = 0;
    private double timeout = 0;
    
    
    public DriveByFeet(double distance, double timeout, double power) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.mecanumDrive);
        this.distance = distance;
        this.power = power;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        /*
        Robot.mecanumDrive.driveByFeet(RobotMap.frontLeftMotor, distance);
        Robot.mecanumDrive.driveByFeet(RobotMap.frontRightMotor, -distance);
        Robot.mecanumDrive.driveByFeet(RobotMap.backLeftMotor, distance);
        Robot.mecanumDrive.driveByFeet(RobotMap.backRightMotor, -distance);
        */
     
        inches = (distance * 12);
        encoderCounts = (inches / RobotMap.INCHES_PER_REV);
    
        initialFrontRight = Robot.mecanumDrive.getPositionFrontRight();
        initialFrontLeft = Robot.mecanumDrive.getPositionFrontLeft();
        initialBackRight = Robot.mecanumDrive.getPositionBackRight();
        initialBackLeft = Robot.mecanumDrive.getPositionBackLeft();
    
        setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        angle = Robot.mecanumDrive.getAngle();
        //Robot.mecanumDrive.driveWithJoystick(0, -power, 0, 0); //Math.abs(angle) > 5 ? angle/360 : 0 );
        Robot.mecanumDrive.driveWithJoystick(-power, 0);
        
        try{
            System.out.println("FL = " + RobotMap.frontLeftMotor.getPosition());
         }catch (CANTimeoutException ex) {
            System.out.println("--- Error Printing Encoder ---");
                ex.printStackTrace();
             }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (((Robot.mecanumDrive.getPositionFrontLeft() - initialFrontLeft) >= encoderCounts) || isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.mecanumDrive.driveWithJoystick(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
