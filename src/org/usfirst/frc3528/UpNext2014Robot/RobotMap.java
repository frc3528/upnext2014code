package org.usfirst.frc3528.UpNext2014Robot;


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.can.CANTimeoutException;


public class RobotMap {

    
    // ********** OBJECTS **********
    
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
    
    
    public static Talon catapultTalon;
    public static DigitalInput winchLimit;
    
    public static Solenoid engageWinch;
    public static Solenoid disengageWinch;
    public static Solenoid latch;
    public static Solenoid unlatch;
    public static Solenoid armDown;
    public static Solenoid armUp;
    
    
    public static Compressor Compressor;
  
    
    public static Relay pickerUpperSpike1;
    
    
    public static Relay targetRingLight;

    
    
    
    
    // ********** CONSTANTS **********
    
    //Drive Train Constants
    public static final int DRIVE_LEFT_FRONT_CAN = 2;
    public static final int DRIVE_LEFT_BACK_CAN = 3;
    public static final int DRIVE_RIGHT_FRONT_CAN = 4;
    public static final int DRIVE_RIGHT_BACK_CAN = 5;
    
    
    // PID
    public static final double DRIVETRAIN_KP = 10.0;
    public static final double DRIVETRAIN_KI = 0.01;
    public static final double DRIVETRAIN_KD = 0.01;
    
    
    // gyro
    public static final int DRIVEBASE_GYRO_CHANNEL = 2;
    public static final double DRIVEBASE_GYRO_SENSITIVITY = .007;    
    
    
    // Control stick constants
    public static final int CTRL_A_BUTTON = 1; // Fire button
    public static final int CTRL_B_BUTTON = 2; // Load button
    public static final int CTRL_X_BUTTON = 3; // Close clutch button
    public static final int CTRL_Y_BUTTON = 4; // Open clutch button
    public static final int CTRL_L_BUMPER = 5; // Start arm wheels
    public static final int CTRL_R_BUMPER = 6; // Stop arm wheels
    public static final int CTRL_BACK_BUTTON = 7; // End Advanced Mode
    public static final int CTRL_START_BUTTON = 8; // Start Advanced Mode
    public static final int CTRL_Z_AXIS = 3; // Raise/Lower arm
    public static final int CTRL_DPAD = 6; // Open/Close latch
    public static final int CTRL_LEFT_STICK_Y = 2; // Drive Winch
    public static final int CTRL_RIGHT_STICK_CLICK = 10; // Latch

    
    // Drive stick constants
    public static final int DRIVE_BACK_BUTTON = 7; // Decrease sensitivity
    public static final int DRIVE_START_BUTTON = 8; // Increase sensitivity
    public static final int DRIVE_LEFT_STICK_X = 1; // Drive X
    public static final int DRIVE_LEFT_STICK_Y = 2; // Drive Y
    public static final int DRIVE_Z_AXIS = 3; // Drive (Rotation)    
    public static double SENSITIVITY = .5; // Starting joystick sensitivity    
    
    
    // Solenoid channels
    public static final int WINCH_PUSH_SOLENOID_CHANNEL = 1;
    public static final int WINCH_PULL_SOLENOID_CHANNEL = 2;
    public static final int LATCH_PUSH_SOLENOID_CHANNEL = 3;
    public static final int LATCH_PULL_SOLENOID_CHANNEL = 4;
    public static final int ARM_PUSH_SOLENOID_CHANNEL = 5;
    public static final int ARM_PULL_SOLENOID_CHANNEL = 6;
    
    
    // Camera Stuff
    public static final String CAMERA_ADDRESS = "10.35.28.11";
    public static final int CAMERA_BRIGHTNESS = 25;
    public static final int CAMERA_COMPRESSION = 0;
    public static final int CAMERA_COLOR_LEVEL = 100;
    public static final int TARGETING_LIGHT_RELAY_CHANNEL = 5;
    
    
    // Targeting and Camera Thingamajigs
    public static final int Y_IMAGE_RES = 480;
    public static final double VIEW_ANGLE = 37.4;
    public static final double PI = 3.141592653;
    public static final int RECTANGULARITY_LIMIT = 40;
    public static final int ASPECT_RATIO_LIMIT = 55;
    public static final int TAPE_WIDTH_LIMIT = 50;
    public static final int VERTICAL_SCORE_LIMIT = 50;
    public static final int LR_SCORE_LIMIT = 50;
    public static final int AREA_MINIMUM = 150;
    public static final int MAX_PARTICLES = 8;
    
    
    //public static final double PI = 3.141592653;
    public static final double WHEEL_DIAMETER = 6;
    public static final double INCHES_PER_REV = (PI * WHEEL_DIAMETER);
    
    public static double DISTANCE_FEET = 10;
    public static double DISTANCE_INCHES = 18.84;
    
    
    // Jiggle!
    public static final double JIGGLE_POWER = 0.25;
    public static final double JIGGLE_DELAY = 0.005;
    public static final int JIGGLE_COUNT = 5;
    
    
    // Compressor
    public static final int COMPRESSOR_RELAY_CHANNEL = 1;
    public static final int PRESSURE_SWITCH_DIO_CHANNEL = 1;
    
    
    // Catapult
    public static final int WINCH_PWM_CHANNEL = 1;
    public static final int WINCH_LOW_LIMIT_CHANNEL = 2;
    

    
    
    
    // ********** CLASS METHODS **********
    
    public static void init() {    
        
        // Targeting
        targetingCamera = AxisCamera.getInstance(CAMERA_ADDRESS);
        targetRingLight = new Relay(TARGETING_LIGHT_RELAY_CHANNEL);
        
        
        try { 
            System.out.println("+++ Constructing CAN Bus +++");
            frontLeftMotor = new CANJaguar(DRIVE_LEFT_FRONT_CAN);
            backLeftMotor = new CANJaguar(DRIVE_LEFT_BACK_CAN);
            frontRightMotor = new CANJaguar(DRIVE_RIGHT_FRONT_CAN);
            backRightMotor = new CANJaguar(DRIVE_RIGHT_BACK_CAN);
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
           
        
        
        
        driveTrainGyro = new Gyro(DRIVEBASE_GYRO_CHANNEL);
	//LiveWindow.addSensor("MecanumDrive", "Gyro 1", mecanumDriveGyro1);
        driveTrainGyro.setSensitivity(DRIVEBASE_GYRO_SENSITIVITY);        
        
        
        Compressor = new Compressor(PRESSURE_SWITCH_DIO_CHANNEL, COMPRESSOR_RELAY_CHANNEL);
        Compressor.start();
        
        
        
        catapultTalon = new Talon(WINCH_PWM_CHANNEL);     
        winchLimit = new DigitalInput(WINCH_LOW_LIMIT_CHANNEL);
                
        
        engageWinch = new Solenoid(WINCH_PUSH_SOLENOID_CHANNEL); //Solenoid 1A
        disengageWinch = new Solenoid(WINCH_PULL_SOLENOID_CHANNEL); //Solenoid 1B
        latch = new Solenoid(LATCH_PUSH_SOLENOID_CHANNEL); //Solenoid 2A  
        unlatch = new Solenoid(LATCH_PULL_SOLENOID_CHANNEL); // Solenoid 2B
        armDown = new Solenoid(ARM_PUSH_SOLENOID_CHANNEL); // Solenoid 3&4A
        armUp = new Solenoid(ARM_PULL_SOLENOID_CHANNEL); // Solenoid 3&4B
        
        
        
        
        pickerUpperSpike1 = new Relay(3);
        
        
        // XXX Make sure these are unique inputs...I believe they are
        /*
        pickerUpperUpperLimit = new DigitalInput(2);
	LiveWindow.addSensor("PickerUpper", "UpperLimit", pickerUpperUpperLimit);
        
        pickerUpperLowerLimit = new DigitalInput(3);
	LiveWindow.addSensor("PickerUpper", "LowerLimit", pickerUpperLowerLimit);
        */
        
        
        // DEBUG
        System.out.println("dt: " + mecanumDriveRobotDrive.getExpiration());
        System.out.println("fl " + frontLeftMotor.getExpiration());
        System.out.println("bl: " + backLeftMotor.getExpiration());
        System.out.println("fr: " + frontRightMotor.getExpiration());
        System.out.println("br: " + backRightMotor.getExpiration());


    }
    
    

    private static void initializeJag(CANJaguar jag) {
        try {
            jag.enableControl(0);
            jag.configEncoderCodesPerRev(360);
            jag.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
        } catch (CANTimeoutException e) {
            System.out.println("Error enabling closed control on Jag " + e.getMessage());
        }
    }
    
    
}

