/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetIntakeArm extends Command {
  public static int outOfTheAway = 0;
  public static int intake = 1;
  public static int stowedAway = 2;
  public static int toTheFloor = 3;
  public static int climbReady = 4;
  public static int climbFinished = 5;

  int mode;
  boolean hatchMode;

  public SetIntakeArm(int mode) {
    // Use requires() here to declare subsystem dependencies

    this.mode = mode;
    hatchMode = false;

    requires(Robot.m_intakeArm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    hatchMode = Robot.m_oi.toggleSwitch.getRawButton(1);

    switch (mode) {
      case 0: //out of the way
      if (hatchMode) {
        stowedAwayPosition();
      } else {
        toTheFloorPosition();
      }
      break;
      case 1: //intake position
      if (hatchMode) {
        stowedAwayPosition();
      } else {
        intakePosition();
      }
      break;
      case 2: //stow away position
      stowedAwayPosition();
      break;
      case 3: //to the floor
      toTheFloorPosition();
      break;
      case 4:
      readyForClimb();
      break;
      case 5:
        climbFinish();
      break;
    }
  }

  void stowedAwayPosition() {
    Robot.m_intakeArm.setSetpoint(40.0);
    Robot.m_intakeArm.enable();
  }

  void intakePosition() {
    Robot.m_intakeArm.setSetpoint(-55.0);
    Robot.m_intakeArm.enable();
  }

  void toTheFloorPosition() {
    Robot.m_intakeArm.setSetpoint(-90); //-90.0);
    Robot.m_intakeArm.enable();
  }

  void readyForClimb() {
    Robot.m_intakeArm.setSetpoint(-20.0);
    Robot.m_intakeArm.enable();
  }

  void climbFinish() {
    Robot.m_intakeArm.setSetpoint(0);
    Robot.m_intakeArm.enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double tolerance = 6;
    double error = Math.abs(Robot.m_intakeArm.getSetpoint()-Robot.m_intakeArm.getPosition());

    return error <= tolerance;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_intakeArm.enable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
