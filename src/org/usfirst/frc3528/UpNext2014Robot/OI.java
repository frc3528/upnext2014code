package org.usfirst.frc3528.UpNext2014Robot;


import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physIcal operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    
    
    public Joystick driveStick;
    public Joystick controlStick;
    public Joystick testingStick;
    public JoystickButton fireWithoutJiggle;
    public JoystickButton cock;
    public JoystickButton disengageWinch;
    public JoystickButton engageWinch;
    public JoystickButton spitOutBall;
    public JoystickButton pickUpBall;
    public JoystickButton latch;
    public JoystickButton unLatch;
    public JoystickButton increaseSensitivity;
    public JoystickButton decreaseSensitivity;
    public JoystickButton driveWinch;
    public JoystickButton fireWithJiggle;
    public JoystickButton lightOn;
    public JoystickButton lightOff;
    public JoystickButton testing;

    
    public OI() {       
        
        // create joysticks
        driveStick = new Joystick(1);
    
        increaseSensitivity = new JoystickButton(driveStick, RobotMap.DRIVE_START_BUTTON);
        increaseSensitivity.whenPressed(new IncreaseSensitivity());
        
        decreaseSensitivity = new JoystickButton(driveStick, RobotMap.DRIVE_BACK_BUTTON);
        decreaseSensitivity.whenPressed(new DecreaseSensitivity());
        
        
        controlStick = new Joystick(2);

        fireWithoutJiggle = new JoystickButton(controlStick, RobotMap.CTRL_A_BUTTON);
        fireWithoutJiggle.whenPressed(new Fire());

        cock = new JoystickButton(controlStick, RobotMap.CTRL_B_BUTTON);
        cock.whenPressed(new Cock());
        
        fireWithJiggle = new JoystickButton(controlStick, RobotMap.CTRL_X_BUTTON);
        fireWithJiggle.whenPressed(new FireBackUp());

        spitOutBall = new JoystickButton(controlStick, RobotMap.CTRL_R_BUMPER);
        spitOutBall.whileHeld(new StartPickerUpperWithButton());
        
        pickUpBall = new JoystickButton(controlStick, RobotMap.CTRL_L_BUMPER);
        pickUpBall.whileHeld(new ReversePickerUpperWithButton());
        
       
        testingStick = new Joystick(3);
        
        latch = new JoystickButton(testingStick, RobotMap.CTRL_A_BUTTON);
        latch.whenPressed(new Latch());
        
        unLatch = new JoystickButton(testingStick, RobotMap.CTRL_B_BUTTON);
        unLatch.whenPressed(new UnLatch());
        
        disengageWinch = new JoystickButton(testingStick, RobotMap.CTRL_Y_BUTTON);
        disengageWinch.whenPressed(new DisengageWinch());
        
        engageWinch = new JoystickButton(testingStick, RobotMap.CTRL_X_BUTTON);
        engageWinch.whenPressed(new EngageWinch());
        
        driveWinch = new JoystickButton(testingStick, RobotMap.CTRL_RIGHT_STICK_CLICK);
        driveWinch.whenPressed(new DriveWinchWithJoystick());
        
        //lightOn = new JoystickButton(testingStick, RobotMap.CTRL_R_BUMPER);
        //lightOn.whenPressed(new CameraLightOn());
        
        //lightOff = new JoystickButton(testingStick, RobotMap.CTRL_L_BUMPER);
        //lightOff.whenPressed(new CameraLightOff());
        
        //testing = new JoystickButton(testingStick, RobotMap.CTRL_START_BUTTON);
        //testing.whenPressed(new AutonomousTesting());
        
        //SmartDashboard Buttons
        //SmartDashboard.putData("Autonomous", new Autonomous());
        //SmartDashboard.putData("DriveWithJoystick", new DriveWithJoystick());
        SmartDashboard.putData("Latch", new Latch());
        SmartDashboard.putData("Lower Arm", new LowerPickerUpper());
        SmartDashboard.putData("Raise Arm", new RaisePickerUpper());
        SmartDashboard.putData("Unlatch", new UnLatch());
        SmartDashboard.putData("Engage Winch", new EngageWinch());
        SmartDashboard.putData("Disengage Winch", new DisengageWinch());
        SmartDashboard.putData("Jiggle motor", new Jiggle());
        SmartDashboard.putData("Turn on Camera light", new CameraLightOn());
        SmartDashboard.putData("Turn off Camera light", new CameraLightOff());
    }
    
    
    public Joystick getDriveStick() {
        return driveStick;
    }

    
    public Joystick getControlStick() {
        return controlStick;
    }
    
}
