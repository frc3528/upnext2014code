package org.usfirst.frc3528.UpNext2014Robot;


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


public class RobotMap {

    
    //public static CANJaguar mecanumDriveCANJaguar1;
    //public static CANJaguar mecanumDriveCANJaguar2;
    //public static CANJaguar mecanumDriveCANJaguar3;
    //public static CANJaguar mecanumDriveCANJaguar4;
    
    public static CANJaguar frontLeftMotor;
    public static CANJaguar backLeftMotor;
    public static CANJaguar frontRightMotor;
    public static CANJaguar backRightMotor;
    public static RobotDrive mecanumDriveRobotDrive;
    public static Gyro mecanumDriveGyro1;
    public static Encoder FrontLeftEncoder;
    public static Encoder BackLeftEncoder;
    public static Encoder FrontRightEncoder;
    public static Encoder BackRightEncoder;

    
    public static SpeedController catapultTalon;
    
    
    /*
    public static Solenoid catapultPush;
    public static Solenoid catapultPull;
    public static Compressor Compressor;
    public static Solenoid catapultSolenoid3;
    public static Solenoid catapultSolenoid4;
    public static Solenoid catapultSolenoid5;
    public static Solenoid catapultSolenoid6;
    */
    
    public static Relay pickerUpperSpike1;
    public static SpeedController pickerUpperTalon1;
    public static SpeedController pickerUpperTalon2;
    
    //public static DigitalInput pickerUpperUpperLimit;
    //public static DigitalInput pickerUpperLowerLimit;
    
    
    public static final String cameraAddress = "10.35.28.11";
    public static final int cameraBrightness = 25;
    public static final int cameraCompression = 0;
    public static final int cameraColorLevel = 0;
    
    
    public static final double SENSITIVITY = .5;
    
    
    
    public static void init() {        
        
    
        
        try { 
            System.out.println("+++ Constructing CAN Bus +++");
            frontLeftMotor = new CANJaguar(2);
            backLeftMotor = new CANJaguar(3);
            frontRightMotor = new CANJaguar(4);
            backRightMotor = new CANJaguar(5);
        } catch (CANTimeoutException ex) {
            System.out.println("--- Error Constructing CAN Bus ---");
            ex.printStackTrace();
        }        
               
        
        
        // Constructing Mechanum Drive and setting parameters
        mecanumDriveRobotDrive = new RobotDrive(frontLeftMotor, backLeftMotor,
              frontRightMotor, backRightMotor);
	
        mecanumDriveRobotDrive.setSafetyEnabled(true);        
        
        mecanumDriveRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        mecanumDriveRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        mecanumDriveRobotDrive.setExpiration(0.1);
        mecanumDriveRobotDrive.setSensitivity(0.5);
        mecanumDriveRobotDrive.setMaxOutput(1.0);
           
        
       
         /*
        mecanumDriveGyro1 = new Gyro(1);
	LiveWindow.addSensor("MecanumDrive", "Gyro 1", mecanumDriveGyro1);
        mecanumDriveGyro1.setSensitivity(0.007);        
        
        Compressor = new Compressor(1, 1);
        Compressor.start();
        */
        
        
        
        catapultTalon = new Talon(1);
        
        /*
        catapultPush = new Solenoid(1);
	LiveWindow.addActuator("Catapult", "Solenoid 1", catapultPush);
        
        catapultPull = new Solenoid(2);
	LiveWindow.addActuator("Catapult", "Solenoid 2", catapultPull);
        
        catapultSolenoid3 = new Solenoid(3);
	LiveWindow.addActuator("Catapult", "Solenoid 3", catapultSolenoid3);
        
        catapultSolenoid4 = new Solenoid(4);
	LiveWindow.addActuator("Catapult", "Solenoid 4", catapultSolenoid4);
        
        catapultSolenoid5 = new Solenoid(5);
	LiveWindow.addActuator("Catapult", "Solenoid 5", catapultSolenoid5);
        
        catapultSolenoid6 = new Solenoid(6);
	LiveWindow.addActuator("Catapult", "Solenoid 6", catapultSolenoid6);
        */
        
        
        
        
        pickerUpperSpike1 = new Relay(2);
	LiveWindow.addActuator("PickerUpper", "Spike 1", pickerUpperSpike1);
        
        pickerUpperTalon1 = new Talon(3);
        pickerUpperTalon2 = new Talon(4);
        LiveWindow.addActuator("PickerUpper", "Talon", (Talon) pickerUpperTalon1);
        
        
        
        
        // XXX Make sure these are unique inputs...I believe they are
        /*
        pickerUpperUpperLimit = new DigitalInput(2);
	LiveWindow.addSensor("PickerUpper", "UpperLimit", pickerUpperUpperLimit);
        
        pickerUpperLowerLimit = new DigitalInput(3);
	LiveWindow.addSensor("PickerUpper", "LowerLimit", pickerUpperLowerLimit);
        */
        
        
        System.out.println("dt: " + mecanumDriveRobotDrive.getExpiration());
        System.out.println("fl " + frontLeftMotor.getExpiration());
        System.out.println("bl: " + backLeftMotor.getExpiration());
        System.out.println("fr: " + frontRightMotor.getExpiration());
        System.out.println("br: " + backRightMotor.getExpiration());


    }

  private void initializeJag(CANJaguar jag){
        //RobotMap.BackRightEncoder.start();
        try {
            jag.enableControl();
            jag.configEncoderCodesPerRev(360);
            jag.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
     } catch(Exception e){
          System.out.println("Error enabling closed control on Jag " + e.getMessage());
          
          
          
       }
    
        initializeJag(frontLeftMotor);
        initializeJag(backLeftMotor);
        initializeJag(frontRightMotor);
        initializeJag(backRightMotor);      
        
    }
}

