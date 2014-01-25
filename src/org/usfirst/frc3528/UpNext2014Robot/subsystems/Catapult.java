package org.usfirst.frc3528.UpNext2014Robot.subsystems;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Catapult extends Subsystem {

    //Compressor compressor = RobotMap.Compressor;
    //Solenoid Push = RobotMap.catapultPush;
    //Solenoid Pull = RobotMap.catapultPull;
    SpeedController talon = RobotMap.catapultTalon;
    Relay clutchspike = RobotMap.clutchSpike;
    DigitalInput winchLimit = RobotMap.winchLimit;
        
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void drive(double righty){
        talon.set(righty);
        
    }
    
    
    public void stop(){
        talon.set(0);
    
    }

    
    public void solenoidOn(){
        clutchspike.set(Relay.Value.kForward);
    
    }
    
    
    public void solenoidOff(){
        clutchspike.set(Relay.Value.kOff);
        
    }    
    
    
    public boolean winchLimit(){
           return winchLimit.get();
    
    }

    
    public void initDefaultCommand() {

        //setDefaultCommand(new DriveWinchWithJoystick());

    }
}
