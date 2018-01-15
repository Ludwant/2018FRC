package org.usfirst.frc.team5980.robot.subsystems;

import org.usfirst.frc.team5980.robot.commands.ArcadeDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	TalonSRX left1 = new TalonSRX(0);
	TalonSRX left2 = new TalonSRX(1);
	TalonSRX right1 = new TalonSRX(2);
	TalonSRX right2 = new TalonSRX(3);
	
	public void setPower(double leftPower, double rightPower) {
		left1.set(ControlMode.PercentOutput, leftPower);
		left2.set(ControlMode.PercentOutput, leftPower);
		right1.set(ControlMode.PercentOutput, rightPower);
		right2.set(ControlMode.PercentOutput, rightPower);
	}
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeDrive());
    }
}

