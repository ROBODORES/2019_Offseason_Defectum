/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  VictorSPX liftMotor = null;
  DigitalInput liftHallEffect = null;
  int mode = 0;
  Boolean previousReturnVal = true;

  //Timer for bounce detection
  Timer bounceTimer = null;

  public Lift() {
    liftMotor = new VictorSPX(RobotMap.liftMotor);
    liftHallEffect = new DigitalInput(RobotMap.liftHallEffect);
    bounceTimer = new Timer();
    /*if (!limitTriggered()) {
      mode = 1;
    }*/

    if (liftHallEffect.get()) { //not triggered
      mode = 1;
      previousReturnVal = false;
    }
  }

  public void down() {
    double speed = 1.0;
    if (mode != 2) {
      liftMotor.set(ControlMode.PercentOutput, -speed);
    } else {
      stop();
    }
  }

  public void up() {
    double speed = 1.0;
    if (mode != 0) {
      liftMotor.set(ControlMode.PercentOutput, speed);
    } else {
      stop();
    }
  }

  public void stop() {
    liftMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public boolean limitTriggered() {
    /*boolean returnVal = false;
    if (!liftHallEffect.get()) { //if hall is triggered. 
      if (bounceTimer.get() >= 0.5) { //also rejects if timer is less than 0.5 seconds
        returnVal = true;
      }
    } else {
      if (previousReturnVal) {
        bounceTimer.reset();
      }
    }
    previousReturnVal = returnVal;*/

    /*boolean returnVal = !liftHallEffect.get();
    if (previousReturnVal != returnVal) {
      if (bounceTimer.get() < 0.5) {
        returnVal = previousReturnVal;
      }
    } else {
      bounceTimer.reset();
    }

    previousReturnVal = returnVal;*/

    //return returnVal;
    return !liftHallEffect.get();
  }

  public boolean topLimitTriggered() {
    boolean returnVal = false;

    switch (mode) {
      case 0:
      if (!limitTriggered()) {
        mode = 1;
      }
      break;
      case 1:
      if (limitTriggered()) {
        mode = 2;
      }
      break;
      case 2:
      returnVal = true;
      break;
    }
    return returnVal;
  }

  public boolean bottomLimitTriggered() {
    boolean returnVal = false;
    
    switch (mode) {
      case 0:
      returnVal = true;
      break;
      case 1:
      if (limitTriggered()) {
        mode = 0;
      }
      break;
      case 2:
      if (!limitTriggered()) {
        mode = 1;
      }
      break;
    }
    return returnVal;
  }

  public void manualUp() {
    double speed = 1.0;
    if (!limitTriggered()) {
      liftMotor.set(ControlMode.PercentOutput, speed);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
