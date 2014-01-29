package org.usfirst.frc3528.UpNext2014Robot.subsystems;
import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Catapult extends Subsystem {
    SpeedController winchTalon = RobotMap.catapultTalon;
    Relay clutchspike = RobotMap.clutchSpike;
    DigitalInput winchLimit = RobotMap.winchLimit;
    Solenoid engageWinch = RobotMap.engageWinch;
    Solenoid disengageWinch = RobotMap.disengageWinch;
    Solenoid latch = RobotMap.latch;
    Solenoid unlatch = RobotMap.unlatch;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void drive(double righty){
        winchTalon.set(righty);
        
    }
     
    public boolean winchLimit(){
           return winchLimit.get();
    
    }
    
    public void stop(){
        winchTalon.set(0);
    
    }

    
    public void engageWinch(){
        engageWinch.set(true);
        disengageWinch.set(false);
    }
    
    public void disengageWinch(){
        disengageWinch.set(true);
        engageWinch.set(false);
    }    
    
    public void freeWinch(){
        engageWinch.set(false);
        disengageWinch.set(false);
    
    }    
        
    
    public void latch(){
      latch.set(true);
      unlatch.set(false);
    }
    
   public void unlatch(){
      unlatch.set(true);
      latch.set(false);  
    }

    public void disablelatch(){
        latch.set(false);
        unlatch.set(false);
    }
    
    
    public void initDefaultCommand() {

        //setDefaultCommand(new DriveWinchWithJoystick());

    }
}
