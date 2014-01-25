package org.usfirst.frc3528.UpNext2014Robot.commands;


import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3528.UpNext2014Robot.Robot;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;


/**
 *
 */
public class  DriveWithJoystick extends Command {

    public DriveWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	

        requires(Robot.mecanumDrive);

    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
       
    }
    
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    
        Robot.mecanumDrive.driveWithJoystick(Robot.oi.driveStick);
        //Robot.mecanumDrive.getPositionBackRight();
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
