package org.usfirst.frc3528.UpNext2014Robot.subsystems;

import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Catapult extends Subsystem {

    Talon winchTalon = RobotMap.catapultTalon;
    DigitalInput winchLimit = RobotMap.winchLimit;
    Solenoid engageWinch = RobotMap.engageWinch;
    Solenoid disengageWinch = RobotMap.disengageWinch;
    Solenoid latch = RobotMap.latch;
    Solenoid unlatch = RobotMap.unlatch;

    //drivewinch command
    public void drive(double power) {
        winchTalon.set(power);

    }
    
    /*
    public void jiggleMotor() {
        System.out.println("jiggling");
        winchTalon.setSafetyEnabled(false);
        for (int i = 0; i <= RobotMap.JIGGLE_COUNT; i++ ){
            winchTalon.set(RobotMap.JIGGLE_POWER);
            Timer.delay(RobotMap.JIGGLE_DELAY);
            winchTalon.set(-RobotMap.JIGGLE_POWER);
            Timer.delay(RobotMap.JIGGLE_DELAY);
         }
            winchTalon.set(0);
    }
    */
    
    //drivewinch command
    public boolean winchLimit() {
        return winchLimit.get();

    }

    //drivewinch command
    public void stop() {
        winchTalon.set(0);

    }

//enagagewinch command
    public void engageWinch() {
        engageWinch.set(true);
        disengageWinch.set(false);
    }

    //disengagewinch command
    public void disengageWinch() {
        disengageWinch.set(true);
        engageWinch.set(false);
    }

    //used by engage and disengage
    public void freeWinch() {
        engageWinch.set(false);
        disengageWinch.set(false);
    }

// latch command
    public void latch() {
        latch.set(true);
        unlatch.set(false);
    }

    //fire command 
    public void unlatch() {
        unlatch.set(true);
        latch.set(false);
    }

//used by fire and unlatch
    public void disablelatch() {
        latch.set(false);
        unlatch.set(false);
    }

    public void defaultCatapult(){
        disengageWinch.set(true);
        engageWinch.set(false);
        
        unlatch.set(true);
        latch.set(false);
    
    }
    
    public void initDefaultCommand() {
        //setDefaultCommand(new RaisePickerUpper());
    }

}
