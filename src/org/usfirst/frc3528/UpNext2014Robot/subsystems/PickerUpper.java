package org.usfirst.frc3528.UpNext2014Robot.subsystems;

import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


public class PickerUpper extends Subsystem {

    Relay spike1 = RobotMap.pickerUpperSpike1;
    Solenoid armUp = RobotMap.armUp;
    Solenoid armDown = RobotMap.armDown;
    //DigitalInput upperLimit = RobotMap.pickerUpperUpperLimit;
    //DigitalInput lowerLimit = RobotMap.pickerUpperLowerLimit;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    //lowerpickerupper command
    public void lower(){
        armDown.set(true);
        armUp.set(false);
    
    }
    
    //raisepickerupper command
    public void raise(){
        armUp.set(true);
        armDown.set(false);

    }
    
    //used by raise and lower pickerupper commands
    public void disableArm(){
        armUp.set(false);
        armDown.set(false);

    }
    
    //startpickerupper command
    public void start(){
        spike1.set(Relay.Value.kForward);
    }
    
    //stoppickerupper command
    public void stop(){
        spike1.set(Relay.Value.kOff);
    }
    
    
    public void initDefaultCommand() {
        setDefaultCommand(new TeleopMovePickerUpper());
    }
    
}
