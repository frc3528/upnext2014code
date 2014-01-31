
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
    
    //lowerpickerupper command
    public void Lower(){
        Armdown.set(true);
        Armup.set(false);
    
    }
    
    //raisepickerupper command
    public void Raise(){
        Armup.set(true);
        Armdown.set(false);

    }
    
    //used by raise and lower pickerupper commands
    public void DisableArm(){
        Armup.set(false);
        Armdown.set(false);

    }
    
    //startpickerupper command
    public void Start(){
        spike1.set(Relay.Value.kForward);
    }
    
    //stoppickerupper command
    public void Stop(){
        spike1.set(Relay.Value.kOff);
    }
    
    
    public void initDefaultCommand() {
        //setDefaultCommand(new LowerPickerUpper());

	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
