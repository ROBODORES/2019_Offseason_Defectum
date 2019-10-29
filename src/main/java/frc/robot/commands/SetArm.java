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

    if (Robot.m_intakeArm.getPosition() > -300.0 && Robot.m_intakeArm.getPosition() < 300.0 && !isOOTW) { //if it's over the arm and not at the floor
      Robot.m_arm.setSetpoint(75.0); //home position kinda
      Robot.m_wrist.setSetpoint(-94.0);
      Robot.m_arm.enable();
      Robot.m_wrist.enable();
      System.out.println("Intake Arm Over The Arm, Moving To Home Position!");
      if (Robot.m_wrist.getPosition() < -70.0) { //if wrist is in the home position
        Robot.m_intakeArm.setSetpoint(-346.5); //to the floor
        Robot.m_intakeArm.enable();
        System.out.println("At Home Position, Moving to the Floor!");
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
      Robot.m_arm.setSetpoint(44.73);
      Robot.m_wrist.setSetpoint(5.0);
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  void levelOne() {
    if (hatchMode) { 
      Robot.m_arm.setSetpoint(57.5);
      Robot.m_wrist.setSetpoint(33);
    } else {
      Robot.m_arm.setSetpoint(74.83);
      Robot.m_wrist.setSetpoint(20.0); // -1
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  void levelTwo() {
    if (hatchMode) { 
      Robot.m_arm.setSetpoint(98.5);
      Robot.m_wrist.setSetpoint(-11.3);
    } else {
      Robot.m_arm.setSetpoint(114.5);
      Robot.m_wrist.setSetpoint(-9.4);
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  void cargoShip() {
    if (hatchMode) { 
      levelOne();
    } else {
      Robot.m_arm.setSetpoint(105.0);
      Robot.m_wrist.setSetpoint(-45.0);
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
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
    Robot.m_wrist.setSetpoint(-0.0);
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
    double tolerance = 5;
    double error = Math.abs(Robot.m_arm.getSetpoint()-Robot.m_arm.getPosition());
    double errorw = Math.abs(Robot.m_wrist.getSetpoint()-Robot.m_wrist.getPosition());

    //System.out.println(error);
    return ((error <= tolerance) && isOOTW);
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
    end(); //play war robots and battle cats lol
  }
}
