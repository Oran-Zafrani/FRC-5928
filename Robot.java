/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5928.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
WPI_TalonSRX canLB=new WPI_TalonSRX(1);
WPI_TalonSRX canRB=new WPI_TalonSRX(2);
WPI_TalonSRX canLF=new WPI_TalonSRX(3);
WPI_TalonSRX canRF=new WPI_TalonSRX(4);
WPI_TalonSRX left_intake = new WPI_TalonSRX(5), right_intake = new WPI_TalonSRX(6)
Joystick right = new Joystick(0);
//RobotDrive name =new RobotDrive(canLF, canLB, canRF, canRB);


SpeedControllerGroup Lside = new SpeedControllerGroup(canLB, canLF);
SpeedControllerGroup Rside = new SpeedControllerGroup(canRB, canRF);
Boolean flag = true;


DoubleSolenoid left_sol = new DoubleSolenoid(0, 1), right_sol = new DoubleSolenoid(2, 3);
Compressor comp = new Compressor();

DifferentialDrive robotDrive = new DifferentialDrive(Lside, Rside);
Timer t = new Timer();
String data=DriverStation.getInstance().getGameSpecificMessage();
	/**
	 * This function is run when the robot is first started up and should be)
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		comp.start();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		t.reset();
		t.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		if(right.getRawAxis(3)>-0.5&&right.getRawAxis(3)<0.5 && data.charAt(0) == 'L')
		{
			
		}
		else if(right.getRawAxis(3)>-0.5&&right.getRawAxis(3)<0.5 && data.charAt(0) == 'R')
		{
			
		}
	
		
		}
	

	
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {

	
	
		
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	public void openIntake(){
		
		if(flag){ // true if cube inside.
			left_intake.set(0.5);
			right_intake.set(-0.5);
			Timer.delay(0.5);
			left_intake.set(0);
			right_intake.set(0);
			
			left_sol.set(Value.kReverse);
			right_sol.set(Value.kReverse);
			flag = false;
		}
		else {
			
			left_intake.set(-0.5);
			right_intake.set(0.5);
			Timer.delay(0.5);
			left_sol.set(Value.kForward);
			right_sol.set(Value.kForward);
			Timer.delay(0.5);

			left_intake.set(0);
			right_intake.set(0);
			flag = true;
			
		}
	}
}
