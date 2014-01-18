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
    public JoystickButton bButton;

    public OI() {
    
        // create joysticks
        driveStick = new Joystick(1);
        controlStick = new Joystick(2);
        
        /*
        aButton = new JoystickButton(driveStick, 1);
        aButton.whenPressed(new Fire());


        bButton = new JoystickButton(driveStick, 2);
        bButton.whenPressed(new retract());
        */
        
        
// SmartDashboard Buttons
        SmartDashboard.putData("Autonomous", new Autonomous());
        SmartDashboard.putData("DriveWithJoystick", new DriveWithJoystick());
        //SmartDashboard.putData("Fire", new Fire());
    
    }
    
    
    public Joystick getDriveStick() {
        return driveStick;
    }

    
    public Joystick getControlStick() {
        return controlStick;
    }
    
}
