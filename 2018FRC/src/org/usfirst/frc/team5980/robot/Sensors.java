package org.usfirst.frc.team5980.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;

public class Sensors {
	boolean usingTalons = true;
	Encoder leftEncoder = new Encoder(0,1);
	Encoder rightEncoder = new Encoder(2,3);
	static AHRS navX;
	public double encoderCountsPerInch = 1;
	public double leftEncoderOffset;
	double rightEncoderOffset;
	float yawOffset = 0;
	
	public void SensorInput() {
		try {
			navX = new AHRS(SPI.Port.kMXP);
		}
		catch (RuntimeException ex) {
			DriverStation.reportError("Error Instantiating NavX: " + ex.getMessage(), true);
		}
	}
	
	public float getYaw() {
		float yaw = -navX.getYaw();
		while(yaw > 180) {
			yaw-=360;
		}
		while (yaw < -180) {
			yaw+=360;
		}
		return (yaw - yawOffset);
	}
	
	public double getLeftEncoder() {
		double encoderVal;
		if (usingTalons) {
			encoderVal = Robot.driveTrain.left1.getSelectedSensorPosition(0);
		}
		else encoderVal = leftEncoder.get();
		return (encoderVal-leftEncoderOffset); // - leftEncoderOffset;
	}
	
	public double getRightEncoder() {
		double encoderVal;
		if(usingTalons) {
			encoderVal = Robot.driveTrain.right1.getSelectedSensorPosition(0);
		}
		else encoderVal = rightEncoder.get();
		return -(encoderVal-rightEncoderOffset);
	}
	
	public void resetSensors() {
		leftEncoderOffset = leftEncoder.get();
		rightEncoderOffset = rightEncoder.get();
		//yawOffset = navX.getYaw();
	}
	
}
