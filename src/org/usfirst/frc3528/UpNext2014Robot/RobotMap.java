package org.usfirst.frc3528.UpNext2014Robot;


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


public class RobotMap {

    
    //public static CANJaguar mecanumDriveCANJaguar1;
    //public static CANJaguar mecanumDriveCANJaguar2;
    //public static CANJaguar mecanumDriveCANJaguar3;
    //public static CANJaguar mecanumDriveCANJaguar4;
    
    public static AxisCamera targetingCamera;
    
    public static CANJaguar frontLeftMotor;
    public static CANJaguar backLeftMotor;
    public static CANJaguar frontRightMotor;
    public static CANJaguar backRightMotor;
    
    public static RobotDrive mecanumDriveRobotDrive;
    
    public static Gyro driveTrainGyro;
    
    public static Encoder FrontLeftEncoder;
    public static Encoder BackLeftEncoder;
    public static Encoder FrontRightEncoder;
    public static Encoder BackRightEncoder;

    
    public static SpeedController catapultTalon;
    public static DigitalInput winchLimit;
    
    public static Solenoid engageWinch;
    public static Solenoid disengageWinch;
    public static Solenoid latch;
    public static Solenoid unlatch;
    
    
    public static Compressor Compressor;
  
    
    public static Relay pickerUpperSpike1;
    public static Solenoid Armup;
    public static Solenoid Armdown;
    
    //public static DigitalInput pickerUpperUpperLimit;
    //public static DigitalInput pickerUpperLowerLimit;
    
    
    public static final String cameraAddress = "10.35.28.11";
    public static final int cameraBrightness = 25;
    public static final int cameraCompression = 0;
    public static final int cameraColorLevel = 0;
    
    
    public static final double SENSITIVITY = .5;
    
    public static final double p = 10.0;
    public static final double i = 0.01;
    public static final double d = 0.01;
    
    public static final double PI = 3.141592653;
    public static final double WHEEL_DIAMETER = 6;
    public static final double INCHES_PER_REV = (PI * WHEEL_DIAMETER);
    
    public static void init() {        
        
    
        
        targetingCamera = AxisCamera.getInstance(cameraAddress);
        
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
               
        initializeJag(frontLeftMotor);
        initializeJag(backLeftMotor);
        initializeJag(frontRightMotor);
        initializeJag(backRightMotor);  
        
        
        
        // Constructing Mechanum Drive and setting parameters
        mecanumDriveRobotDrive = new RobotDrive(frontLeftMotor, backLeftMotor,
              frontRightMotor, backRightMotor);
	
        mecanumDriveRobotDrive.setSafetyEnabled(true);        
        
        mecanumDriveRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        mecanumDriveRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        mecanumDriveRobotDrive.setExpiration(0.1);
        mecanumDriveRobotDrive.setSensitivity(0.5);
        mecanumDriveRobotDrive.setMaxOutput(1.0);
           
        
       
         
        driveTrainGyro = new Gyro(1);
	//LiveWindow.addSensor("MecanumDrive", "Gyro 1", mecanumDriveGyro1);
        driveTrainGyro.setSensitivity(0.007);        
        
        
        Compressor = new Compressor(1, 1);
        Compressor.start();
        
        
        
        
        catapultTalon = new Talon(1);
        winchLimit = new DigitalInput(2);
                
        
        engageWinch = new Solenoid(1);
        disengageWinch = new Solenoid(2);
        latch = new Solenoid(3);   
        unlatch = new Solenoid(4);
	       
        /*
        catapultSolenoid5 = new Solenoid(5);
	LiveWindow.addActuator("Catapult", "Solenoid 5", catapultSolenoid5);
        
        catapultSolenoid6 = new Solenoid(6);
	LiveWindow.addActuator("Catapult", "Solenoid 6", catapultSolenoid6);
        */
        
        
        
        
        pickerUpperSpike1 = new Relay(3);
	Armup = new Solenoid(5);
        Armdown = new Solenoid(6);
        
        
        
        
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

  private static void initializeJag(CANJaguar jag){
      try{
          jag.enableControl(0);
          jag.configEncoderCodesPerRev(360);
          jag.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
      }catch(Exception e){
          System.out.println("Error enabling closed control on Jag " + e.getMessage());
       }
    }
}

