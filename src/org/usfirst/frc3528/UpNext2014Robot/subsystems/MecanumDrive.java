package org.usfirst.frc3528.UpNext2014Robot.subsystems;

import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc3528.UpNext2014Robot.Utils;

/**
 *
 */
public class MecanumDrive extends Subsystem {

    CANJaguar frontLeftMotor = RobotMap.frontLeftMotor;
    CANJaguar backLeftMotor = RobotMap.backLeftMotor;
    CANJaguar frontRightMotor = RobotMap.frontRightMotor;
    CANJaguar backRightMotor = RobotMap.backRightMotor;
    RobotDrive robotDrive = RobotMap.mecanumDriveRobotDrive;
    Gyro gyro1 = RobotMap.mecanumDriveGyro1;
            
  /*    
  public MecanumDrive() throws CANTimeoutException {


        robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
    */
                 
   
    
    public void driveWithJoystick(Joystick joystick) {

        driveWithJoystick(joystick.getX(), joystick.getY(), joystick.getZ(), 0);
        
    }
    
    public void driveWithJoystick(double x, double y, double rotation, double gyroAngle) {
        robotDrive.mecanumDrive_Cartesian(Utils.rampSpeed(x, RobotMap.SENSITIVITY), Utils.rampSpeed(y, RobotMap.SENSITIVITY), Utils.rampSpeed(-1 * rotation, RobotMap.SENSITIVITY), 0);
        //robotDrive.mecanumDrive_Cartesian(x, y, rotation * -1, 0);

    
     /*
     try {
       System.out.println("BR = " + frontRightMotor.getPosition());
         } catch (CANTimeoutException ex) {
             System.out.println("--- Error Printing Encoder ---");
                ex.printStackTrace();
             }
    */
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
    
        
    
    
    
    
    
    
    public void setPositionFrontLeft(double distance) {
        try {
            frontLeftMotor.setX(distance);
        } catch (Exception e) {
            System.out.println("Error setting FrontRight Position: " + e.getMessage());
        }
    }    
    
    public void setPositionBackLeft(double distance) {
        try {
            backLeftMotor.setX(distance);
        } catch (Exception e) {
            System.out.println("Error setting FrontRight Position: " + e.getMessage());
        }
    }
    
    public void setPositionFrontRight(double distance) {
        try {
            frontRightMotor.setX(distance);
        } catch (Exception e) {
            System.out.println("Error setting FrontRight Position: " + e.getMessage());
        }
    }  
    
    public void setPositionBackRight(double distance) {
        try {
            backRightMotor.setX(distance);
        } catch (Exception e) {
            System.out.println("Error setting FrontRight Position: " + e.getMessage());
        }
    }
    
   
    
    
    
    
    
    
    
     
    public void zeroEncoders() {
        try {
            System.out.println("--- Working ---");
            frontRightMotor.disableControl();
            frontLeftMotor.disableControl();
            backRightMotor.disableControl();
            backLeftMotor.disableControl();
            frontRightMotor.enableControl(0);
            frontLeftMotor.enableControl(0);
            backRightMotor.enableControl(0);
            backLeftMotor.enableControl(0);
        } catch (Exception e) {
            System.out.println("Error zeroing encoders: " + e.getMessage());
        }
   
    }
    
    
    
    
    public void SetPositionMode() {
        try {
            backLeftMotor.changeControlMode(CANJaguar.ControlMode.kPosition);
            backRightMotor.changeControlMode(CANJaguar.ControlMode.kPosition);
            frontLeftMotor.changeControlMode(CANJaguar.ControlMode.kPosition);
            frontRightMotor.changeControlMode(CANJaguar.ControlMode.kPosition);

        } catch (Exception e) {
            System.out.println("Error setting jag into position mode: " + e.getMessage());
        }
    }
}
