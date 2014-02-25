package org.usfirst.frc3528.UpNext2014Robot.subsystems;

import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


public class PickerUpper extends Subsystem {

    Relay spike1 = RobotMap.pickerUpperSpike1;
    Solenoid armUp = RobotMap.armUp;
    Solenoid armDown = RobotMap.armDown;
    DigitalInput armLimit = RobotMap.armLimit;
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
    
    public void reverse(){
        spike1.set(Relay.Value.kReverse);
    }
    
    //stoppickerupper command
    public void stop(){
        spike1.set(Relay.Value.kOff);
    }
    
    public void buttonForward(boolean button){
        if(button){
            this.start();
        } else{
            this.stop();
        }
            
    }
    
    public void buttonReverse(boolean button){
        if(button){
            this.reverse();
        } else{
            this.stop();
        }
            
    }
    
    public boolean armDown(){
        return armLimit.get();
    }
    
    public void defaultPickerUpper(){
        armUp.set(true);
        armDown.set(false);
        spike1.set(Relay.Value.kOff);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new TeleopMovePickerUpper());
    }
    
}
