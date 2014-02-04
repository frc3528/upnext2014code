package org.usfirst.frc3528.UpNext2014Robot.subsystems;

import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    Gyro gyro1 = RobotMap.driveTrainGyro;

    public MecanumDrive() {
        //gyro1 = new Gyro(RobotMap.GYRO_CHANNEL);
        gyro1.setSensitivity(RobotMap.GYRO_SENSITIVITY);
        gyro1.reset();
    }
    
    
   public void driveWithJoystick(Joystick joystick) {

        driveWithJoystick(joystick.getX(), joystick.getY(), joystick.getZ(), 0);
        
    }
    
   //main driving method 
   public void driveWithJoystick(double x, double y, double rotation, double gyroAngle) {
        robotDrive.mecanumDrive_Cartesian(Utils.rampSpeed(x, RobotMap.SENSITIVITY), Utils.rampSpeed(y, RobotMap.SENSITIVITY), Utils.rampSpeed(-1 * rotation, RobotMap.SENSITIVITY), 0);
        //robotDrive.mecanumDrive_Cartesian(x, y, rotation * -1, 0);
         /*
         try{
            System.out.println("FR = " + frontLeftMotor.getPosition());
         }catch (CANTimeoutException ex) {
            System.out.println("--- Error Printing Encoder ---");
                ex.printStackTrace();
             }
         */        
         System.out.println("Gyro angle: " + gyro1.getAngle());
         SmartDashboard.putNumber("Gyro", gyro1.getAngle());
         SmartDashboard.putNumber("Drivestick X", x);
         SmartDashboard.putNumber("Drivestick Y", -y);
         SmartDashboard.putNumber("Drivestick Rotation", -rotation);
         SmartDashboard.putNumber("Drive Sensitivity", RobotMap.SENSITIVITY);
    
    }

    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
    
    
    //readies jags for autonomous
    public void SetPositionMode(CANJaguar jag) {
        try{
            jag.changeControlMode(CANJaguar.ControlMode.kPosition);
            jag.setPID(RobotMap.p, RobotMap.i, RobotMap.d);
            jag.enableControl(0);
        }catch (Exception e) {
            System.out.println("Error setting jag into position mode: " + e.getMessage());
        }
    }

    
    //zeros the encoders for teleop 
    public void zeroEncoders(CANJaguar jag) {
        try{
            jag.changeControlMode(CANJaguar.ControlMode.kPosition);
            jag.enableControl(0);
        }catch (Exception e) {
            System.out.println("Error zeroing encoders: " + e.getMessage());
        
        }
    }

    
    //readies jags for teleop
    public void SetPercentMode(CANJaguar jag) {
        try{
            jag.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            jag.setPID(0, 0, 0);
            jag.disableControl();
        }catch (Exception e) {
            System.out.println("Error setting jag into position mode: " + e.getMessage());
        }
    }



   //tells a single jag to drive X inches  
    public void driveByInches(CANJaguar jag, double distance) {
        
        try{
            double fr = jag.getPosition();
            double newpos = fr + (distance / RobotMap.INCHES_PER_REV);
            jag.setX(newpos);
        }catch (CANTimeoutException ex) {
            System.out.println("--- Error running autonomous ---");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    //tells a single jag to drive X feet
    public void driveByFeet(CANJaguar jag, double distance) {
        distance = (distance * 12);
        driveByInches(jag, distance);

    }

    public void increaseSensitivity(){
        if(RobotMap.SENSITIVITY < 1){
            RobotMap.SENSITIVITY += .1;
        }
    }
}
