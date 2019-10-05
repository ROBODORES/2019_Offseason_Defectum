/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class liftTwo extends Command {
  public liftTwo() {
    requires(Robot.m_intakeArm);
    requires(Robot.m_lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_intakeArm.disable();
    Robot.m_lift.stop();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_intakeArm.downForClimb();
    Robot.m_lift.down();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double downPosition = -366.5;
    return (Robot.m_intakeArm.getPosition() <= downPosition) || Robot.m_lift.topLimitTriggered();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_intakeArm.stop();
    Robot.m_intakeArm.setSetpoint(-366.5);
    Robot.m_intakeArm.enable();
    Robot.m_lift.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_intakeArm.stop();
    Robot.m_lift.stop();
  }
}
