package org.usfirst.frc3528.UpNext2014Robot.subsystems;

import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


public class PickerUpper extends Subsystem {

    //Relay spike1 = RobotMap.pickerUpperSpike1;
    Talon pickUpTalon = RobotMap.pickerUpperTalon;
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
    
    public void lowerTest() {
        armDown.set(true);
        armUp.set(false);
        RobotMap.SAFE = true;
    }
    
    public void raiseTest() {
        armUp.set(true);
        armDown.set(false);
        RobotMap.SAFE = false;
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
    public void spitOutBall(){
        //spike1.set(Relay.Value.kForward);
        pickUpTalon.set(1.0);
    }
    
    public void pickUpBall(){
        //spike1.set(Relay.Value.kReverse);
        pickUpTalon.set(-1.0);
    }
    
    //stoppickerupper command
    public void stop(){
        //spike1.set(Relay.Value.kOff);
        pickUpTalon.set(0.0);
    }
    
    public void buttonForward(boolean button){
        if(button){
            this.spitOutBall();
        } else{
            this.stop();
        }
            
    }
    
    public void buttonReverse(boolean button){
        if(button){
            this.pickUpBall();
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
        //spike1.set(Relay.Value.kOff);
        pickUpTalon.set(0.0);
    }
    
    public void initDefaultCommand() {
        //setDefaultCommand(new TeleopMovePickerUpper());
    }
    
}
