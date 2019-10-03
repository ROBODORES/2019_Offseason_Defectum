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

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  VictorSPX liftMotor = null;
  DigitalInput liftHallEffect = null;
  int mode = 0;

  public Lift() {
    liftMotor = new VictorSPX(RobotMap.liftMotor);
    liftHallEffect = new DigitalInput(RobotMap.liftHallEffect);
    if (!limitTriggered()) {
      mode = 1;
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
