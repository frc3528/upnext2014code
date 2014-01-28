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
    public JoystickButton aButton;
    public JoystickButton yButton;
    public JoystickButton xButton;
    public OI() {
    
        // create joysticks
        driveStick = new Joystick(1);
        controlStick = new Joystick(2);
        
        aButton = new JoystickButton(driveStick, 1);
        aButton.whenPressed(new DriveWinchWithJoystick());

        yButton = new JoystickButton(driveStick, 4);
        yButton.whenPressed(new EngageWinch());
        
        xButton = new JoystickButton(driveStick, 3);
        xButton.whenPressed(new DisengageWinch());
        
        
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
