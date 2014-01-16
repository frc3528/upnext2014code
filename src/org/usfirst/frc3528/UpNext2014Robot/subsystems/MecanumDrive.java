package org.usfirst.frc3528.UpNext2014Robot.subsystems;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc3528.UpNext2014Robot.Utils;


/**
 *
 */
public class MecanumDrive extends Subsystem {

    SpeedController frontLeftMotor = RobotMap.frontLeftMotor;   
    SpeedController backLeftMotor = RobotMap.backLeftMotor; 
    SpeedController frontRightMotor = RobotMap.frontRightMotor;
    SpeedController backRightMotor = RobotMap.backRightMotor;
    RobotDrive robotDrive = RobotMap.mecanumDriveRobotDrive;
    Gyro gyro1 = RobotMap.mecanumDriveGyro1;

    public MecanumDrive() {
        
        //super();
        
        //setJagLocations();
        
        robotDrive = new RobotDrive(frontLeftMotor,backLeftMotor,frontRightMotor,backRightMotor);


        //initializeJag(cANJaguar1);
        //initializeJag(cANJaguar2);
        //initializeJag(cANJaguar3);
        //initializeJag(cANJaguar4);       
                
                
   }
       
    public void driveWithJoystick(Joystick joystick) {
        
        //DEBUG: debugging drivetrain
        //System.out.println("working");
        
        driveWithJoystick(joystick.getX(), joystick.getY(), joystick.getZ(), 0);
   }
    
    public void driveWithJoystick(double x, double y, double rotation, double gyroAngle) {
        robotDrive.mecanumDrive_Cartesian(Utils.rampSpeed(x, RobotMap.SENSITIVITY), Utils.rampSpeed(y, RobotMap.SENSITIVITY), Utils.rampSpeed(-1 * rotation, RobotMap.SENSITIVITY), 0);
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
     }
    
    
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
