/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;

public class IntakeArmPID extends PIDSubsystem {
  VictorSPX intakeArmMotor = null;
  //VictorSPX intakeArmMotorFollower = null;
  Encoder intakeArmEncoder = null;
  DigitalInput intakeArmHallEffect = null;

  public IntakeArmPID() {
    super("IntakeArmPID", 0.08, 0.0, 0.0);
    setAbsoluteTolerance(0.05);

    getPIDController().setContinuous(false);

    intakeArmMotor = new VictorSPX(RobotMap.intakeArmMotor);
    //intakeArmMotorFollower = new VictorSPX(RobotMap.intakeArmMotorFollower);
    //intakeArmMotorFollower.follow(intakeArmMotor);
    //intakeArmMotor.configRemoteFeedbackFilter(0, null, 0); //attempt to shut off ebrake on disable

    intakeArmEncoder = new Encoder(RobotMap.intakeSourceA, RobotMap.intakeSourceB);
    //intakeArmEncoder.setReverseDirection(true);
    intakeArmEncoder.setDistancePerPulse(90.0/580);

    intakeArmHallEffect = new DigitalInput(RobotMap.intakeHallEffect);
  }

  public void startEbrake() {
    intakeArmMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void killEbrake() {
    intakeArmMotor.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new IntakeArmTest());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return intakeArmEncoder.getDistance();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    double limiter = 0.75;
    if (output > limiter) {
      output = limiter;
    } else if (output < -limiter) {
      output = -limiter;
    }
    //intakeArmMotor.set(ControlMode.PercentOutput, output);
  }

  public void print() {
    System.out.println(intakeArmEncoder.getDistance());
    System.out.println(hallEffectTriggered());
  }

  public boolean hallEffectTriggered() {
    return !intakeArmHallEffect.get();
  }

  public void resetEncoder() {
    intakeArmEncoder.reset();
  }

  public void down() {
    double speed = 0.5;
    intakeArmMotor.set(ControlMode.PercentOutput, -speed);
  }

  public void downForClimb() {
    double speed = 0.32;
    intakeArmMotor.set(ControlMode.PercentOutput, -speed);
  }

  public void up() {
    double speed = 0.5;
    intakeArmMotor.set(ControlMode.PercentOutput, speed);
  }

  public void stop() {
    intakeArmMotor.set(ControlMode.PercentOutput, 0.0);
  }
}