/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetLEDs extends Command {
  public SetLEDs() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_LEDs);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_LEDs.setLEDS(Robot.m_LEDs.rainbow_rainbow_palette);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double mode = Robot.m_LEDs.both_colors_beats_per_minute;
    Robot.m_LEDs.setLEDS(mode);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_LEDs.setLEDS(Robot.m_LEDs.red);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
