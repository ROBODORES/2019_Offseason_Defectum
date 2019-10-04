/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CalibrateIntakeArm extends Command {
  public CalibrateIntakeArm() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_intakeArm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("going!");
    Robot.m_intakeArm.down();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Robot.m_intakeArm.hallEffectTriggered()) {
      System.out.println("stopping!");
    }
    return Robot.m_intakeArm.hallEffectTriggered();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_intakeArm.stop();
    Robot.m_intakeArm.resetEncoder();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_intakeArm.stop();
  }
}
