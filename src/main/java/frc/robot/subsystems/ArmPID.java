/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
//import frc.robot.commands.MechanismInit;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Add your docs here.
 */
public class ArmPID extends PIDSubsystem {
  VictorSPX armMotor = null;
  //VictorSPX armMotorFollower = null;
  Encoder armEncoder = null;
  double armOffset;

  public ArmPID() {
    super("ArmPID", 0.05, 0.00005, 0.005);
    setAbsoluteTolerance(0.05);

    getPIDController().setContinuous(false);

    armMotor = new VictorSPX(RobotMap.armMotor);
    //armMotorFollower = new VictorSPX(RobotMap.armMotorFollower);
    //armMotorFollower.follow(armMotor);

    armOffset = 46.0; //From initial calibration

    armEncoder = new Encoder(RobotMap.armSourceA, RobotMap.armSourceB);
    armEncoder.setReverseDirection(true);
    armEncoder.setDistancePerPulse(35.8/63511.0);
  }

  public void reset() {
    armEncoder.reset();
  }

  public void stop() {
    armMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public void nudgeArm(double angle) {
    armOffset -= angle; //subtract from offset angle to move the mech up
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MechanismInit());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return armEncoder.getDistance()+armOffset;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    double limiter = 0.4;
    if (output > limiter) {
      output = limiter;
    } else if (output < -limiter) {
      output = -limiter;
    }
    armMotor.set(ControlMode.PercentOutput, output+0.15);
  }
}
