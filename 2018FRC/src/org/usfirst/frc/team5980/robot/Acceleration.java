package org.usfirst.frc.team5980.robot;

public class Acceleration {
	double targetSpeed, currentSpeed, increment;
	public Acceleration(double targetSpeed, double initialSpeed, double increment) {
		this.targetSpeed = targetSpeed;
		currentSpeed = initialSpeed;
		this.increment = increment;
	}
	
	public double getSpeed() {
		currentSpeed += increment;
		if(increment < 0 && currentSpeed < targetSpeed) {
			currentSpeed = targetSpeed;
		}
		if(increment > 0 && currentSpeed > targetSpeed) {
			currentSpeed = targetSpeed;
		}
		return currentSpeed;
	}
	
}
