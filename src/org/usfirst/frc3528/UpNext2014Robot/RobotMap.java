
package org.usfirst.frc3528.UpNext2014Robot;
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;




public class RobotMap {

    
    //public static CANJaguar mecanumDriveCANJaguar1;
    //public static CANJaguar mecanumDriveCANJaguar2;
    //public static CANJaguar mecanumDriveCANJaguar3;
    //public static CANJaguar mecanumDriveCANJaguar4;
    
    public static SpeedController frontLeftMotor;
    public static SpeedController backLeftMotor;
    public static SpeedController frontRightMotor;
    public static SpeedController backRightMotor;
    
    public static RobotDrive mecanumDriveRobotDrive;
    public static Gyro mecanumDriveGyro1;
    
    public static Compressor Compressor;
    public static Solenoid catapultSolenoid1;
    public static Solenoid catapultSolenoid2;
    public static Solenoid catapultSolenoid3;
    public static Solenoid catapultSolenoid4;
    public static Solenoid catapultSolenoid5;
    public static Solenoid catapultSolenoid6;
    public static Relay pickerUpperSpike1;
    public static SpeedController pickerUpperTalon;
    public static DigitalInput pickerUpperUpperLimit;
    public static DigitalInput pickerUpperLowerLimit;

    
   
    public static final double SENSITIVITY = .5;
    
    
    public static void init() {

        
        
        /*
        try { 
            mecanumDriveCANJaguar1 = new CANJaguar(2);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
	
        
        try { 
            mecanumDriveCANJaguar2 = new CANJaguar(3);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
	
        
        try { 
            mecanumDriveCANJaguar3 = new CANJaguar(4);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
	
        
        try { 
            mecanumDriveCANJaguar4 = new CANJaguar(5);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
	*/
        
        frontLeftMotor = new Jaguar(3);
        backLeftMotor = new Jaguar(4);
        frontRightMotor = new Jaguar(5);
        backRightMotor = new Jaguar(6);
        
        mecanumDriveRobotDrive = new RobotDrive(frontLeftMotor, backLeftMotor,
              frontRightMotor, backRightMotor);
	
        mecanumDriveRobotDrive.setSafetyEnabled(true);
 
        
        /*XXX: This stuff can be problematic. And We don't even have a gyro connected.
        mecanumDriveRobotDrive41.setExpiration(0.1);
        mecanumDriveRobotDrive41.setSensitivity(0.5);
        mecanumDriveRobotDrive41.setMaxOutput(1.0);
        mecanumDriveGyro1 = new Gyro(1, 1);
	LiveWindow.addSensor("MecanumDrive", "Gyro 1", mecanumDriveGyro1);
        mecanumDriveGyro1.setSensitivity(0.007);
        */
        
        
        Compressor = new Compressor(1, 1);
	
        
        catapultSolenoid1 = new Solenoid(1);
	LiveWindow.addActuator("Catapult", "Solenoid 1", catapultSolenoid1);
        
        catapultSolenoid2 = new Solenoid(2);
	LiveWindow.addActuator("Catapult", "Solenoid 2", catapultSolenoid2);
        
        catapultSolenoid3 = new Solenoid(3);
	LiveWindow.addActuator("Catapult", "Solenoid 3", catapultSolenoid3);
        
        catapultSolenoid4 = new Solenoid(4);
	LiveWindow.addActuator("Catapult", "Solenoid 4", catapultSolenoid4);
        
        catapultSolenoid5 = new Solenoid(5);
	LiveWindow.addActuator("Catapult", "Solenoid 5", catapultSolenoid5);
        
        catapultSolenoid6 = new Solenoid(6);
	LiveWindow.addActuator("Catapult", "Solenoid 6", catapultSolenoid6);
        
        pickerUpperSpike1 = new Relay(2);
	LiveWindow.addActuator("PickerUpper", "Spike 1", pickerUpperSpike1);
        
        pickerUpperTalon = new Talon(3);
	LiveWindow.addActuator("PickerUpper", "Talon", (Talon) pickerUpperTalon);
        
        
        // XXX Make sure these are unique inputs...I believe they are
        pickerUpperUpperLimit = new DigitalInput(2);
	LiveWindow.addSensor("PickerUpper", "UpperLimit", pickerUpperUpperLimit);
        
        pickerUpperLowerLimit = new DigitalInput(3);
	LiveWindow.addSensor("PickerUpper", "LowerLimit", pickerUpperLowerLimit);
        
   }
}
