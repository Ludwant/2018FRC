package org.usfirst.frc.team5980.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;

public class Sensors {
	Encoder leftEncoder = new Encoder(0,1);
	Encoder rightEncoder = new Encoder(2,3);
	static AHRS navX;
	public double encoderCountsPerInch = 1;
	double leftEncoderOffset = 0;
	double rightEncoderOffset = 0;
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
		return leftEncoder.get() - leftEncoderOffset;
	}
	
	public double getRightEncoder() {
		return -(rightEncoder.get()-rightEncoderOffset);
	}
	
	public void resetSensors() {
		leftEncoderOffset = leftEncoder.get();
		rightEncoderOffset = rightEncoder.get();
		yawOffset = navX.getYaw();
	}
	
}
