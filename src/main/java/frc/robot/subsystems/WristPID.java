/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.SetArm;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Add your docs here.
 */
public class WristPID extends PIDSubsystem {
  VictorSPX wristMotor = new VictorSPX(RobotMap.wristMotor);
  //VictorSPX wristMotorFollower = new VictorSPX(RobotMap.wristMotorFollower);
  Encoder wristEncoder = null;
  double wristOffset;

  public WristPID() {
    // Intert a subsystem name and PID values here
    super("WristPID", 0.04, 0.001, 0.005);

    setAbsoluteTolerance(0.005);

    wristOffset = -99.0; //From initial calibration

    getPIDController().setContinuous(false);
    //wristMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    wristEncoder = new Encoder(RobotMap.wristSourceA, RobotMap.wristSourceB);
    wristEncoder.setReverseDirection(false);
    wristEncoder.setDistancePerPulse(9.0/3400.0);
  }

  public void reset() {
    wristEncoder.reset();
  }

  public void nudgeWrist(double angle){
    wristOffset -= angle; //subtract from offset angle to move the mech up
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    //return wristMotor.getSelectedSensorPosition();
    //make more negative to move wrist higher
    System.out.print((wristEncoder.getDistance()) + wristOffset);
    return wristEncoder.getDistance()+wristOffset;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    double limiter = 0.4;
    if (output > limiter) {
      output = limiter;
    } else if (output < -limiter) {
      output = -limiter;
    }
    wristMotor.set(ControlMode.PercentOutput, -output);
    //wristMotorFollower.set(ControlMode.PercentOutput, output);
  }

  void stop(){
    wristMotor.set(ControlMode.PercentOutput, 0.0);
    //wristMotorFollower.set(ControlMode.PercentOutput, 0.0);
  }
}
