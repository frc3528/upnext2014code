package org.usfirst.frc3528.UpNext2014Robot.subsystems;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Catapult extends Subsystem {

    Compressor compressor = RobotMap.Compressor;
    Solenoid solenoid1 = RobotMap.catapultSolenoid1;//push
    Solenoid solenoid2 = RobotMap.catapultSolenoid2;//pull
    //Solenoid solenoid3 = RobotMap.catapultSolenoid3;
    //Solenoid solenoid4 = RobotMap.catapultSolenoid4;
    //Solenoid solenoid5 = RobotMap.catapultSolenoid5;
    //Solenoid solenoid6 = RobotMap.catapultSolenoid6;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void push(){
        
        solenoid1.set(true);
        Timer.delay(0.01);
        solenoid1.set(false);

    
    } 
    
    public void pull(){
    
        solenoid2.set(true);
        Timer.delay(0.01);
        solenoid2.set(false);
        

    
    
    }
    
    
    
    
    public void initDefaultCommand() {

        //setDefaultCommand(new Fire());

	// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
