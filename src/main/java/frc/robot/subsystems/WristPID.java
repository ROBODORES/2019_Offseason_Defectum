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
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Add your docs here.
 */
public class WristPID extends PIDSubsystem {
  TalonSRX wristMotor = new TalonSRX(RobotMap.wristMotor);
  //VictorSPX wristMotorFollower = new VictorSPX(RobotMap.wristMotorFollower);

  public WristPID() {
    // Intert a subsystem name and PID values here
    super("WristPID", 0.009, 0.0, 0.0);

    setAbsoluteTolerance(0.005);

    getPIDController().setContinuous(false);
    wristMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  }

  public double convertEncoderDistance(double encoderVal) {
    return encoderVal*(90.0/169705.0);
  }

  public void reset() {
    wristMotor.setSelectedSensorPosition(0);
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
    return convertEncoderDistance(wristMotor.getSelectedSensorPosition());
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
    //wristMotor.set(ControlMode.PercentOutput, output);
    //wristMotorFollower.set(ControlMode.PercentOutput, output);
  }

  void stop(){
    wristMotor.set(ControlMode.PercentOutput, 0.0);
    //wristMotorFollower.set(ControlMode.PercentOutput, 0.0);
  }
}
