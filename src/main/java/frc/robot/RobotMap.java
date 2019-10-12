/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
public class RobotMap {

  // Joysticks
  public static final int leftStickPort = 0;
  public static final int rightStickPort = 1;
  public static final int sideStickPort = 2;
  public static final int toggleSwitchPort = 3;

  public static final int gearSwitcherButton = 3;

  //SparkMaxs
  public static final int leftMotor = 0;
  public static final int leftMotorFollower = 1;
  public static final int rightMotor = 2;
  public static final int rightMotorFollower = 3;

  //VictorSPXs
  public static final int armMotor = 5;
  public static final int wristMotor = 4;
  public static final int armIntakeMotor = 6;
  public static final int liftMotor = 1;
  public static final int intakeArmMotor = 3;
  public static final int intakeRollerMotor = 2;
  //public static final int armMotorFollower = 12;
  //public static final int intakeArmMotorFollower = 13;
  //public static final int wristMotorFollower = 14;

  //Encoder Ports
  public static final int wristSourceA = 0;
  public static final int wristSourceB = 1;
  public static final int armSourceA = 2;
  public static final int armSourceB = 3;
  public static final int intakeSourceA = 4;
  public static final int intakeSourceB = 5;

  //Limit Switches
  public static final int intakeHallEffect = 6;
  public static final int liftHallEffect = 7;

  //Solenoids
  public static final int mandibleSolenoid = 1;
  public static final int gearSwitcher = 0;

  public static final int pcm_id = 0;

  //LEDs
  public static final int blinkinPort = 9;
}
