/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetArm extends Command {
  public static int intakeLevelHeight = 0;
  public static int LevelOneHeight = 1;
  public static int LevelTwoHeight = 2;
  public static int cargoShipHeight = 3;
  public static int LevelThreeHeight = 4;
  public static int setForLift = 5;
  public static int returnHome = 6;

  int mode;
  boolean hatchMode;
  boolean isOOTW = false; //is out of the way

  boolean nohit = false;

  public SetArm(int mode) {
    // Use requires() here to declare subsystem dependencies
    this.mode = mode;
    hatchMode = false;

    requires(Robot.m_arm);
    requires(Robot.m_wrist);
    requires(Robot.m_intake);
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

    isOOTW = false;

    if (Robot.m_arm.getPosition() >= 30.0) nohit = true;

    if (Robot.m_intakeArm.getPosition() < 0.0) {
      Robot.m_intakeArm.setSetpoint(-346.5);
      Robot.m_intakeArm.enable();
      if (Robot.m_intakeArm.getPosition() < -300.0) {
        isOOTW = true;
      }
    } else {
      isOOTW = true;
    }

    Robot.m_intake.setMandible(hatchMode); //set the mandible solenoid

    if (isOOTW) {
      switch (mode) {
        case 0:
        intakeHeight();
        break;
        case 1:
        levelOne();
        break;
        case 2:
        levelTwo();
        break;
        case 3:
        cargoShip();
        break;
        case 4:
        levelThree();
        break;
        case 5:
        setForLift();
        break;
        case 6:
        home();
        break;
      }
    }
  }

  void intakeHeight() {
    if (hatchMode) {
      levelOne();
    } else {
      Robot.m_arm.setSetpoint(40.0);
      Robot.m_wrist.setSetpoint(32.0);
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  void levelOne() {
    if (hatchMode) { 
      Robot.m_arm.setSetpoint(51.5);
      Robot.m_wrist.setSetpoint(39.0);
    } else {
      Robot.m_arm.setSetpoint(66.5);
      Robot.m_wrist.setSetpoint(23.5);
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  void levelTwo() {
    if (hatchMode) { 
      Robot.m_arm.setSetpoint(97.0);
      Robot.m_wrist.setSetpoint(-7.0);
    } else {
      Robot.m_arm.setSetpoint(110.14);
      Robot.m_wrist.setSetpoint(-20);
    }
    Robot.m_arm.enable();
    if (nohit) Robot.m_wrist.enable();
  }

  void cargoShip() {
    if (hatchMode) { 
      levelOne();
    } else {
      Robot.m_arm.setSetpoint(90.0);
      Robot.m_wrist.setSetpoint(-90.0);
    }
    Robot.m_arm.enable();
    if (nohit) Robot.m_wrist.enable();
  }

  void levelThree() {
    /*if (hatchMode) { //don't go to level3
      Robot.m_arm.setSetpoint(120.0);
      Robot.m_wrist.setSetpoint(65.0);
    } else {
      Robot.m_arm.setSetpoint(130.0);
      Robot.m_wrist.setSetpoint(185.0);
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();*/
  }

  void setForLift() {
    Robot.m_arm.setSetpoint(106.0);
    Robot.m_wrist.setSetpoint(-94.0);
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  void home() {
    Robot.m_arm.setSetpoint(55.0);
    Robot.m_wrist.setSetpoint(-94.0);
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double tolerance = 10;
    double error = Math.abs(Robot.m_arm.getSetpoint()-Robot.m_arm.getPosition());
    double errorw = Math.abs(Robot.m_wrist.getSetpoint()-Robot.m_wrist.getPosition());

    System.out.println(errorw);
    return ((error <= tolerance) && isOOTW);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
    nohit = true; //safety net maybe
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
