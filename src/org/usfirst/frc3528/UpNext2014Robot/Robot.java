    package org.usfirst.frc3528.UpNext2014Robot;


import edu.wpi.first.wpilibj.IterativeRobot;
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
    //public static Targeter targeter;
 
    /**
     * This function is run when the robot is first spitOutBalled up and sho
     * used for any initialization code.
     */
    public void robotInit() {
        RobotMap.init();
        System.out.println("====> UpNext2014Robot <====");
        mecanumDrive = new MecanumDrive();
        catapult = new Catapult();
        pickerUpper = new PickerUpper();
        //targeter = new Targeter();
   
        oi = new OI();
	
        // instantiate the command used for the autonomous period

        autoChooser = new SendableChooser();
        autoChooser.addDefault("1 Ball Autonomous", new AutonomousOneBall());
        autoChooser.addObject("2 Ball Autonmous", new AutonomousTwoBallClose());
        autoChooser.addObject("Drive Forward Autonomous", new AutonomousDriveForward());
        autoChooser.addObject("No Autonomous", new AutonomousNone());
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
        //double number = SmartDashboard.getNumber("Drive Distance");
    
        //sets defaults (removed for safety - added in Test Mode)
        /*
        System.out.println("--- robotInit: Setting defaults ---");
        pickerUpper.defaultPickerUpper();
        catapult.defaultCatapult();
        */
    
    
    }
    
    public void autonomousInit() {
        // schedule the autonomous command (example)
        //if (autonomousCommand != null) 
        autonomousCommand = (Command) autoChooser.getSelected();
        RobotMap.mecanumDriveRobotDrive.setSafetyEnabled(false);
        new zeroEncoders().start();
        new setPercentMode().start();
        new setBrakeMode().start();
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
        RobotMap.mecanumDriveRobotDrive.setSafetyEnabled(true);
        new zeroEncoders().start();
        new setPercentMode().start();
        new setCoastMode().start();
    }
   
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        //System.out.println("sonar =" + (RobotMap.sonarSensor.getVoltage() * 100));
        System.out.println("SAFE = " + RobotMap.SAFE);
        Scheduler.getInstance().run();
    }
    
    
    public void disabledInit() {
        new setCoastMode().start();

    }
    
    /**
     * This function called periodically during test mode
     */
    public void testInit() {
        LiveWindow.setEnabled(false);
        //RobotMap.compressor.spitOutBall();
        //LiveWindow.run();
        
        if(!Robot.catapult.winchLimit() ) {
        
            System.out.println("--- Test Mode: Setting defaults (catapult up) ---");
            pickerUpper.lowerTest();
            catapult.engageWinch();
            catapult.unlatch();      
        
            // simply wait until the pressure switch is true (at pressure)
            while ( !RobotMap.compressor.getPressureSwitchValue()) {
            // just waiting for pressure to come up
            }
        
            System.out.println("------> lowering winch ");
            new TestModeDefault().start();        
        
        } else {
            System.out.println("--- Test Mode: Setting defaults (catapult down) ---");
            pickerUpper.raiseTest();
            catapult.engageWinch();
            catapult.latch();
        }
    }
    
    
    public void testPeriodic() {
        //double testNumber = SmartDashboard.getNumber("TestNumber");
        //System.out.println("TestNumber" + testNumber);
        //System.out.println("pswitch: " + RobotMap.compressor.getPressureSwitchValue());
        
        Scheduler.getInstance().run();
        
        
    }
}
