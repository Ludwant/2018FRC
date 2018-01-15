package org.usfirst.frc.team5980.robot.commands;

import org.usfirst.frc.team5980.robot.EGRPID;
import org.usfirst.frc.team5980.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateToHeading extends Command {
	double targetHeading;
	double maxSpeed;
	double speed = 0;
	long stopTime;
	EGRPID rotatePID = new EGRPID(.1, 0, 0);
	
    public RotateToHeading(double speed, double heading) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        this.targetHeading = heading;
        this.maxSpeed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	stopTime = System.currentTimeMillis() + 3000;
    	rotatePID.setTarget(targetHeading);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double correction = rotatePID.getCorrection(Robot.sensors.getYaw());
    	if (correction>1) correction = 1;
    	if (correction<-1) correction = -1;
    	if(speed < maxSpeed) speed+=.04;
    	Robot.driveTrain.setPower(-(speed*correction), (speed*correction));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(targetHeading-Robot.sensors.getYaw()) < 2 || System.currentTimeMillis() > stopTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setPower(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
