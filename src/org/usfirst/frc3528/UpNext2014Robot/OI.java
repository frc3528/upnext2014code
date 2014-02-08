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
    public JoystickButton Fire;
    public JoystickButton cock;
    public JoystickButton DisengageWinch;
    public JoystickButton EngageWinch;
    public JoystickButton PickerUpperWheelsForward;
    public JoystickButton PickerUpperWheelsReverse;
    public JoystickButton Latch;
    public JoystickButton IncreaseSensitivity;
    public JoystickButton DecreaseSensitivity;
    public JoystickButton DriveWinch;


    public OI() {       
    
        // create joysticks
        driveStick = new Joystick(1);

        IncreaseSensitivity = new JoystickButton(driveStick, RobotMap.DRIVE_START_BUTTON);
        IncreaseSensitivity.whenPressed(new IncreaseSensitivity());
        
        DecreaseSensitivity = new JoystickButton(driveStick, RobotMap.DRIVE_BACK_BUTTON);
        DecreaseSensitivity.whenPressed(new DecreaseSensitivity());
        
        
        controlStick = new Joystick(2);

        Fire = new JoystickButton(controlStick, RobotMap.CTRL_A_BUTTON);
        Fire.whenPressed(new Fire());

        cock = new JoystickButton(controlStick, RobotMap.CTRL_B_BUTTON);
        cock.whenPressed(new Cock());
        
        DisengageWinch = new JoystickButton(controlStick, RobotMap.CTRL_Y_BUTTON);
        DisengageWinch.whenPressed(new DisengageWinch());
        
        EngageWinch = new JoystickButton(controlStick, RobotMap.CTRL_X_BUTTON);
        EngageWinch.whenPressed(new EngageWinch());
        
        PickerUpperWheelsForward = new JoystickButton(controlStick, RobotMap.CTRL_L_BUMPER);
        PickerUpperWheelsForward.whenPressed(new StartPickerUpperWithButton());
        
        PickerUpperWheelsReverse = new JoystickButton(controlStick, RobotMap.CTRL_R_BUMPER);
        PickerUpperWheelsReverse.whenPressed(new ReversePickerUpperWithButton());
        
        
        //testing v
        //Latch = new JoystickButton(controlStick, RobotMap.CTRL_B_BUTTON);
        //Latch.whenPressed(new Latch());
        
        DriveWinch = new JoystickButton(controlStick, RobotMap.CTRL_RIGHT_STICK_CLICK);
        DriveWinch.whenPressed(new DriveWinchWithJoystick());
        //testing ^
        

           

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
        
    }
    
    
    public Joystick getDriveStick() {
        return driveStick;
    }

    
    public Joystick getControlStick() {
        return controlStick;
    }
    
}
