package org.usfirst.frc3528.UpNext2014Robot.subsystems;

import org.usfirst.frc3528.UpNext2014Robot.RobotMap;
import org.usfirst.frc3528.UpNext2014Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3528.UpNext2014Robot.Utils;



public class MecanumDrive extends Subsystem {

    CANJaguar frontLeftMotor = RobotMap.frontLeftMotor;
    CANJaguar backLeftMotor = RobotMap.backLeftMotor;
    CANJaguar frontRightMotor = RobotMap.frontRightMotor;
    CANJaguar backRightMotor = RobotMap.backRightMotor;
    RobotDrive robotDrive = RobotMap.mecanumDriveRobotDrive;
    Gyro gyro1 = RobotMap.driveTrainGyro;

    public MecanumDrive() {
        gyro1.reset();
    }
    
    
   public void driveWithJoystick(Joystick joystick) {

        driveWithJoystick(joystick.getX(), joystick.getY(), joystick.getZ(), 0);
        
    }
    
   //main driving method 
   public void driveWithJoystick(double x, double y, double rotation, double gyroAngle) {
       //System.out.println("rotation : " + rotation); 
       robotDrive.mecanumDrive_Cartesian(0, Utils.rampSpeed(y, RobotMap.SENSITIVITY), Utils.rampSpeed(1 * rotation, RobotMap.SENSITIVITY), 0); //Flightstick code
        //robotDrive.mecanumDrive_Cartesian(Utils.rampSpeed(x, RobotMap.SENSITIVITY), Utils.rampSpeed(y, RobotMap.SENSITIVITY), Utils.rampSpeed(-1 * rotation, RobotMap.SENSITIVITY), 0); //Xbox controller code
        //robotDrive.mecanumDrive_Cartesian(x, y, rotation * -1, 0);
       
         /*
         try{
            System.out.println("Fl = " + frontLeftMotor.getPosition());
         }catch (CANTimeoutException ex) {
            System.out.println("--- Error Printing Encoder ---");
                ex.printStackTrace();
             }
          */      
         
        //System.out.println("Gyro angle: " + gyro1.getAngle());
        
        //SmartDashboard.putNumber("Gyro", gyro1.getAngle());
        SmartDashboard.putString("Gyro Position", "Gyro Position");
        SmartDashboard.putNumber("Drivestick X", x);
        SmartDashboard.putString("Drivestick X value", "Drivestick X value");
        SmartDashboard.putNumber("Drivestick Y", -y);
        SmartDashboard.putString("Drivestick Y value", "Drivestick Y value");
        SmartDashboard.putNumber("Drivestick Rotation", -rotation);
        SmartDashboard.putString("Robot Rotation", "Robot Rotation");
        SmartDashboard.putNumber("Drive Sensitivity", RobotMap.SENSITIVITY);
        SmartDashboard.putString("Drivestick Sensitivity", "Drivestick Sensitivity");
        SmartDashboard.putString("Live Camera Feed", "Live Camera Feed");
    }

    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
    
    
    //readies jags for autonomous
    public void SetPositionMode(CANJaguar jag) {
        try{
            jag.changeControlMode(CANJaguar.ControlMode.kPosition);
            jag.setPID(RobotMap.DRIVETRAIN_KP, RobotMap.DRIVETRAIN_KI, RobotMap.DRIVETRAIN_KD);
            jag.enableControl(0);
        }catch (CANTimeoutException e) {
            System.out.println("Error setting jag into position mode: " + e.getMessage());
        }
    }
    
    
    //zeros the encoders for teleop 
    public void zeroEncoders(CANJaguar jag) {
        try{
            jag.changeControlMode(CANJaguar.ControlMode.kPosition);
            jag.enableControl(0);
        }catch (CANTimeoutException e) {
            System.out.println("Error zeroing encoders: " + e.getMessage());
        
        }
    }

    
    //readies jags for teleop
    public void SetPercentMode(CANJaguar jag) {
        try{
            jag.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
            //jag.setPID(0, 0, 0);
            jag.disableControl();
        }catch (CANTimeoutException e) {
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
    
    public void encoderInchesPerRev(CANJaguar jag, double distance){
        double inches = (distance * 12);
        double inchesPerRev = (inches / RobotMap.INCHES_PER_REV);
    }
    
    public double getPositionFrontRight() {
        try {
            return -frontRightMotor.getPosition();
        } catch (Exception e) {
            System.out.println("Error getting jag position: " + e.getMessage());

            return 0;
        }
    }

    public double getPositionFrontLeft() {
        try {
            return frontLeftMotor.getPosition();
        } catch (Exception e) {
            System.out.println("Error getting jag position: " + e.getMessage());

            return 0;
        }
    }

    public double getPositionBackRight() {
        try {
            return -backRightMotor.getPosition();//negative because motors are inverted
        } catch (Exception e) {
            System.out.println("Error getting jag position: " + e.getMessage());

            return 0;
        }
    }

    public double getPositionBackLeft() {
        try {
            return backLeftMotor.getPosition();
        } catch (Exception e) {
            System.out.println("Error getting jag position: " + e.getMessage());

            return 0;
        }
    }
    
       public double getAngle() {
           return gyro1.getAngle();
    }
    
       
       
       
    public void increaseSensitivity() {
        if(RobotMap.SENSITIVITY < .9) {
            RobotMap.SENSITIVITY += .1;
        }
    }

    public void decreaseSensitivity() {
        if(RobotMap.SENSITIVITY > .2) {
            RobotMap.SENSITIVITY -= .1;
        }
    }

    public void brakeMode(CANJaguar jag){
        try{
        //jag.setSafetyEnabled(false);
        jag.configNeutralMode(CANJaguar.NeutralMode.kBrake);
        } catch (Exception e) {
          System.out.println("Error enabling brake mode");
        }
    }

    public void coastMode(CANJaguar jag){
       try{
        //jag.setSafetyEnabled(true);
        jag.configNeutralMode(CANJaguar.NeutralMode.kCoast);
        } catch (Exception e) {
          System.out.println("Error enabling coast mode");
        } 
    }
}
