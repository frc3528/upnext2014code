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
    public JoystickButton fire;
    public JoystickButton cock;
    public JoystickButton disengageWinch;
    public JoystickButton engageWinch;
    public JoystickButton pickerUpperWheelsForward;
    public JoystickButton pickerUpperWheelsReverse;
    public JoystickButton latch;
    public JoystickButton increaseSensitivity;
    public JoystickButton decreaseSensitivity;
    public JoystickButton driveWinch;
    
    
    public OI() {       
    
        // create joysticks
        driveStick = new Joystick(1);
    
        increaseSensitivity = new JoystickButton(driveStick, RobotMap.DRIVE_START_BUTTON);
        increaseSensitivity.whenPressed(new IncreaseSensitivity());
        
        decreaseSensitivity = new JoystickButton(driveStick, RobotMap.DRIVE_BACK_BUTTON);
        decreaseSensitivity.whenPressed(new DecreaseSensitivity());
        
        
        controlStick = new Joystick(2);

        fire = new JoystickButton(controlStick, RobotMap.CTRL_A_BUTTON);
        fire.whenPressed(new Fire());

        cock = new JoystickButton(controlStick, RobotMap.CTRL_B_BUTTON);
        cock.whenPressed(new Cock());
        
        disengageWinch = new JoystickButton(controlStick, RobotMap.CTRL_Y_BUTTON);
        disengageWinch.whenPressed(new DisengageWinch());
        
        engageWinch = new JoystickButton(controlStick, RobotMap.CTRL_X_BUTTON);
        engageWinch.whenPressed(new EngageWinch());
        
        pickerUpperWheelsForward = new JoystickButton(controlStick, RobotMap.CTRL_L_BUMPER);
        pickerUpperWheelsForward.whenPressed(new StartPickerUpperWithButton());
        
        pickerUpperWheelsReverse = new JoystickButton(controlStick, RobotMap.CTRL_R_BUMPER);
        pickerUpperWheelsReverse.whenPressed(new ReversePickerUpperWithButton());
        
        
        //testing v
        //latch = new JoystickButton(controlStick, RobotMap.CTRL_B_BUTTON);
        //latch.w
        //latch = new JhenPressed(new Latch());
        
        driveWinch = new JoystickButton(controlStick, RobotMap.CTRL_RIGHT_STICK_CLICK);
        driveWinch.whenPressed(new DriveWinchWithJoystick());
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
