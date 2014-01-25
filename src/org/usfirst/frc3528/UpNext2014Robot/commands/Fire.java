package org.usfirst.frc3528.UpNext2014Robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3528.UpNext2014Robot.Robot;
/**
 *
 */
public class  Fire extends Command {
    public Fire() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	

        requires(Robot.catapult);

    }
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.catapult.solenoidOn();

    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.catapult.solenoidOff();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
