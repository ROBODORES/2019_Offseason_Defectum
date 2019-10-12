/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RaiseWithPreload extends Command {

  public RaiseWithPreload() {
    requires(Robot.m_arm);
    requires(Robot.m_wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_arm.setSetpoint(100.0); //level two setpoint
    Robot.m_wrist.setSetpoint(-94.0); //home position
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double tolerance = 5;
    double error = Math.abs(Robot.m_arm.getSetpoint()-Robot.m_arm.getPosition());
    double errorw = Math.abs(Robot.m_wrist.getSetpoint()-Robot.m_wrist.getPosition());

    //System.out.println(error);
    return (error <= tolerance);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
