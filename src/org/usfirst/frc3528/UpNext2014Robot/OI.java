package org.usfirst.frc3528.UpNext2014Robot;


import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    
    
    public Joystick driveStick;
    public Joystick controlStick;
    public JoystickButton Fire;
    public JoystickButton PrimeCatapult;
    public JoystickButton DisengageWinch;
    public JoystickButton EngageWinch;
    public JoystickButton StartPickerUpperWheels;
    public JoystickButton StopPickerUpperWheels;
    public JoystickButton Latch;
    public JoystickButton IncreaseSensitivity;
    public JoystickButton DecreaseSensitivity;


    public OI() {       
    
        // create joysticks
        driveStick = new Joystick(1);

        IncreaseSensitivity = new JoystickButton(driveStick, RobotMap.DRIVE_START_BUTTON);
        IncreaseSensitivity.whenPressed(new IncreaseSensitivity());
        
        
        
        controlStick = new Joystick(2);

        Fire = new JoystickButton(controlStick, RobotMap.CTRL_A_BUTTON);
        Fire.whenPressed(new Fire());

        PrimeCatapult = new JoystickButton(controlStick, RobotMap.CTRL_B_BUTTON);
        PrimeCatapult.whenPressed(new PrimeCatapult());
        
        DisengageWinch = new JoystickButton(controlStick, RobotMap.CTRL_Y_BUTTON);
        DisengageWinch.whenPressed(new DisengageWinch());
        
        EngageWinch = new JoystickButton(controlStick, RobotMap.CTRL_X_BUTTON);
        EngageWinch.whenPressed(new EngageWinch());

        StartPickerUpperWheels = new JoystickButton(controlStick, RobotMap.CTRL_L_BUMPER);
        StartPickerUpperWheels.whenPressed(new StartPickerUpper());

        StopPickerUpperWheels = new JoystickButton(controlStick, RobotMap.CTRL_R_BUMPER);
        StopPickerUpperWheels.whenPressed(new StopPickerUpper());
        
        Latch = new JoystickButton(controlStick, RobotMap.CTRL_LEFT_STICK_CLICK);
        Latch.whenPressed(new Latch());

           

        //SmartDashboard Buttons
        SmartDashboard.putData("Autonomous", new Autonomous());
        SmartDashboard.putData("DriveWithJoystick", new DriveWithJoystick());
        //SmartDashboard.putData("Fire", new solenoidOn());
        //SmartDashboard.putData("Retract", new DriveWinchWithJoystick());
        //SmartDashboard.putData("Fire", new Fire());
    
    }
    
    
    public Joystick getDriveStick() {
        return driveStick;
    }

    
    public Joystick getControlStick() {
        return controlStick;
    }
    
}
