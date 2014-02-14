package com.teamupnext.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3528.UpNext2014Robot.Robot;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;

/**
 *
 * @author Up Next!
 */
public class DriveForwardEncoder extends Command {

    private double m_distance = 0;//2.45;
    private static final double TIMEOUT = 4;
    private double m_power = .5;
    private double initialFrontRight = 0;
    private double initialFrontLeft = 0;
    private double initialBackRight = 0;
    private double initialBackLeft = 0;
    private double angle = 0;
    private boolean setFlag = false;

    public DriveForwardEncoder(double distance, double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.mecanumDrive);
        m_distance = distance;
        m_power = power;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.mecanumDrive.zeroEncoders(RobotMap.frontLeftMotor);
        Robot.mecanumDrive.zeroEncoders(RobotMap.backLeftMotor);
        Robot.mecanumDrive.zeroEncoders(RobotMap.frontRightMotor);
        Robot.mecanumDrive.zeroEncoders(RobotMap.backRightMotor);
        initialFrontRight = Robot.mecanumDrive.getPositionFrontRight();
        initialFrontLeft = Robot.mecanumDrive.getPositionFrontLeft();
        initialBackRight = Robot.mecanumDrive.getPositionBackRight();
        initialBackLeft = Robot.mecanumDrive.getPositionBackLeft();
        //driveTrain.setFrontRightPower(-.25);
        setTimeout(TIMEOUT);
        //.SetPositionMode();
        //driveTrain.setPositionFrontRight(DISTANCE);


    }

    // Called repeatedly (aprox. every 20ms)when this Command is scheduled to run
    protected void execute() {
        //System.out.println("---> top get angle");
        angle = Robot.mecanumDrive.getAngle();
        //System.out.println("---> bottom get angle");
        Robot.mecanumDrive.driveWithJoystick(0, -m_power, 0, Math.abs(angle) > 5 ? angle/360 : 0 );//inverted to deal with inverted y-axis
        //Logger.logMessage(LogLevel.INFO, LogLocation.DSTATION, "" + m_gyro.pidGet() );
        /*if(setFlag == false)
        {
        System.out.println("Setting power");
        driveTrain.setFrontRightPower(1);
        setFlag = true;
        }*/
        //driveTrain.setPositionFrontRight(DISTANCE);
        //System.out.println("Front Right position: " + driveTrain.getPositionFrontRight()); 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (((Robot.mecanumDrive.getPositionFrontRight() - initialFrontRight) >= m_distance) || isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
        //driveTrain.SetPercentMode();
        //driveTrain.setFrontRightPower(0);
        Robot.mecanumDrive.driveWithJoystick(0, 0, 0, 0);
        /*System.out.println("End");
        Utils.printToDriverStation("Final Front Right: " + (initialFrontRight - driveTrain.getPositionFrontRight()));
        Utils.printToDriverStation("Final Front Left: " + (initialFrontLeft - driveTrain.getPositionFrontLeft()));
        Utils.printToDriverStation("Final Back Right: " + (initialBackRight - driveTrain.getPositionBackRight()));
        Utils.printToDriverStation("Final Back Left: " + (initialBackLeft - driveTrain.getPositionBackLeft()));
        
        System.out.println("Final Front Right: " + (initialFrontRight - driveTrain.getPositionFrontRight()));
        System.out.println("Final Front Left: " + (initialFrontLeft - driveTrain.getPositionFrontLeft()));
        System.out.println("Final Back Right: " + (initialBackRight - driveTrain.getPositionBackRight()));
        System.out.println("Final Back Left: " + (initialBackLeft - driveTrain.getPositionBackLeft()));
         */
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}