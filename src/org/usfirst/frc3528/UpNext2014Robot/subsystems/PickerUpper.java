
package org.usfirst.frc3528.UpNext2014Robot.subsystems;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class PickerUpper extends Subsystem {

    Relay spike1 = RobotMap.pickerUpperSpike1;
    Solenoid Armup = RobotMap.Armup;
    Solenoid Armdown = RobotMap.Armdown;
    //DigitalInput upperLimit = RobotMap.pickerUpperUpperLimit;
    //DigitalInput lowerLimit = RobotMap.pickerUpperLowerLimit;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void Lower(){
        Armdown.set(true);
        Armup.set(false);
    
    }
    
    public void Raise(){
        Armup.set(true);
        Armdown.set(false);

    }
        
    public void DisableArm(){
        Armup.set(false);
        Armdown.set(false);

    }
    
    public void Start(){
        spike1.set(Relay.Value.kOn);
    }
    
    public void Stop(){
        spike1.set(Relay.Value.kOff);
    }
    
    
    public void initDefaultCommand() {
        //setDefaultCommand(new LowerPickerUpper());

	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
