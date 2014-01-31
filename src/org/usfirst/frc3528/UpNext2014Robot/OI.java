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
    public JoystickButton yButton;
    public JoystickButton xButton;
    public JoystickButton lBumper;
    public JoystickButton rBumper;
    public OI() {       
    
        // create joysticks
        driveStick = new Joystick(1);

        
        
        
        controlStick = new Joystick(2);

        aButton = new JoystickButton(controlStick, RobotMap.A_BUTTON);
        aButton.whenPressed(new Fire());

        bButton = new JoystickButton(controlStick, RobotMap.B_BUTTON);
        bButton.whenPressed(new PrimeCatapult());
        
        yButton = new JoystickButton(controlStick, RobotMap.Y_BUTTON);
        yButton.whenPressed(new DisengageWinch());
        
        xButton = new JoystickButton(controlStick, RobotMap.X_BUTTON);
        xButton.whenPressed(new EngageWinch());

        lBumper = new JoystickButton(controlStick, RobotMap.L_BUMPER);
        lBumper.whenPressed(new StartPickerUpper());








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
