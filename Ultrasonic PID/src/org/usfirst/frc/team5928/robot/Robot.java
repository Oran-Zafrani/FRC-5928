/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5928.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.hal.AllianceStationID;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a sample program to demonstrate the use of a PIDController with an
 * ultrasonic sensor to reach and maintain a set distance from an object.
 */
public class Robot extends IterativeRobot {
	// distance in inches the robot wants to stay from an object
	private static final double kHoldDistance = 12.0;

	// maximum distance in inches we expect the robot to see
	private static final double kMaxDistance = 24.0;

	// factor to convert sensor values to a distance in inches
	private static final double kValueToInches = 0.125;

	// proportional speed constant
	private static final double kP = 7.0;

	// integral speed constant
	private static final double kI = 0.018;

	// derivative speed constant
	private static final double kD = 1.5;

	private static final int kLeftMotorPort = 0;
	private static final int kRightMotorPort = 1;
	private static final int kUltrasonicPort = 0;

	private AnalogInput m_ultrasonic = new AnalogInput(kUltrasonicPort);
	private DifferentialDrive m_robotDrive
			= new DifferentialDrive(new Spark(kLeftMotorPort),
			new Spark(kRightMotorPort));
	private PIDController m_pidController
			= new PIDController(kP, kI, kD, m_ultrasonic, new MyPidOutput());
	

	/**
	 * Drives the robot a set distance from an object using PID control and the
	 * ultrasonic sensor.
	 */
	@Override
	public void teleopInit() {
		// Set expected range to 0-24 inches; e.g. at 24 inches from object go
		// full forward, at 0 inches from object go full backward.
		m_pidController.setInputRange(0, kMaxDistance * kValueToInches);
		// Set setpoint of the pid controller
		m_pidController.setSetpoint(kHoldDistance * kValueToInches);
		m_pidController.enable(); // begin PID control
		System.out.println(m_ultrasonic.getValue()*kValueToInches);
		SmartDashboard.putData(m_pidController);
		SmartDashboard.putData(m_robotDrive);
		LiveWindow.add(m_pidController);
		LiveWindow.add(m_robotDrive);
		LiveWindow.add(m_ultrasonic);
		LiveWindow.setEnabled(true);
		
		SendableBase ultra = m_ultrasonic;
		SendableBase drive = m_robotDrive;
		SendableBase PID = m_pidController;
		ultra.setName("Drive Train", "Distance");
		drive.setName("Drive Train", "Drive");
		PID.setName("Drive Train", "PID");
		
	}
		@Override
		public void teleopPeriodic(){
			
			
		}
	
	private class MyPidOutput implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			m_robotDrive.arcadeDrive(output, 0);
		}
	}
}
