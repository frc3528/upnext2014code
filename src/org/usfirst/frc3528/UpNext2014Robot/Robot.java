package org.usfirst.frc3528.UpNext2014Robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import org.usfirst.frc3528.UpNext2014Robot.subsystems.*;


public class Robot extends IterativeRobot {
    
    Command autonomousCommand;
    public static OI oi;
 
    public static MecanumDrive mecanumDrive;
    public static Catapult catapult;
    public static PickerUpper pickerUpper;
 
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	RobotMap.init();
 
        mecanumDrive = new MecanumDrive();
        //catapult = new Catapult();
        pickerUpper = new PickerUpper();
   
        oi = new OI();
	
        // instantiate the command used for the autonomous period
           autonomousCommand = new Autonomous();
   
    }
    
    
    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
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
    }
    
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    
    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
