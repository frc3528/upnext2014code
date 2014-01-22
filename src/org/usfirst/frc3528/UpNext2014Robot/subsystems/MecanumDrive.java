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
    Encoder backRightEncoder = RobotMap.BackRightEncoder;
/*      public MecanumDrive() throws CANTimeoutException {

        //super();
        //setJagLocations();
        

        robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

        //initializeJag(cANJaguar1);
        //initializeJag(cANJaguar2);
        //initializeJag(cANJaguar3);
        //initializeJag(cANJaguar4);                
    }
*/
    public void driveWithJoystick(Joystick joystick) {

        driveWithJoystick(joystick.getX(), joystick.getY(), joystick.getZ(), 0);
        
    }

    public void driveWithJoystick(double x, double y, double rotation, double gyroAngle) {
        robotDrive.mecanumDrive_Cartesian(Utils.rampSpeed(x, RobotMap.SENSITIVITY), Utils.rampSpeed(y, RobotMap.SENSITIVITY), Utils.rampSpeed(-1 * rotation, RobotMap.SENSITIVITY), 0);
        //robotDrive.mecanumDrive_Cartesian(x, y, rotation * -1, 0);

    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }

   /* 
    private void initializeJag(CANJaguar jag){
        //backRightEncoder.start();
        try {
            jag.enableControl();
            jag.configEncoderCodesPerRev(360);
            jag.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
     } catch(Exception e){
          System.out.println("Error enabling closed control on Jag " + e.getMessage());
          
          
          
       }
    }*/
}