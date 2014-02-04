    package org.usfirst.frc3528.UpNext2014Robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import org.usfirst.frc3528.UpNext2014Robot.subsystems.*;


public class Robot extends IterativeRobot {
    
    Command autonomousCommand;
    SendableChooser autoChooser;
    public static OI oi;
 
    public static MecanumDrive mecanumDrive;
    public static Catapult catapult;
    public static PickerUpper pickerUpper;
    public static Targeter targeter;
 
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	RobotMap.init();
        
            mecanumDrive = new MecanumDrive();
            catapult = new Catapult();
            pickerUpper = new PickerUpper();
            //targeter = new Targeter();
   
        oi = new OI();
	
        // instantiate the command used for the autonomous period
           autonomousCommand = new DriveByInches();
           
           autoChooser = new SendableChooser();
           autoChooser.addDefault("Default program", new Autonomous());
           autoChooser.addObject("Experimental auto", new Autonomous());
           SmartDashboard.putData("Autonomous mode chooser", autoChooser);
    }
    
    
    public void autonomousInit() {
        // schedule the autonomous command (example)
        //if (autonomousCommand != null) 
        autonomousCommand = (Command) autoChooser.getSelected();
        RobotMap.mecanumDriveRobotDrive.setSafetyEnabled(false);
        new zeroEncoders().start();
        new setPositionMode().start();
        autonomousCommand.start();
          }
    
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    
    
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        new zeroEncoders().start();
        new setPercentMode().start();
    
    }
   
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        //System.out.println(RobotMap.SENSITIVITY);
        Scheduler.getInstance().run();
    }
    
    
    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
