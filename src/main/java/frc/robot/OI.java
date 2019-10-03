/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.Button;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commands.SetIntake;
import frc.robot.commands.SetIntakeArm;
import frc.robot.commands.IntakeLevel;
import frc.robot.commands.LevelOne;
import frc.robot.commands.LevelTwo;
import frc.robot.commands.CargoShip;
import frc.robot.commands.MechanismInit;
import frc.robot.commands.LevelThree;

import frc.robot.commands.SetForLift;
import frc.robot.commands.LiftGo;
import frc.robot.commands.SetForLiftTwo;
import frc.robot.commands.LiftGoTwo;
import frc.robot.commands.ResetLift;
//import frc.robot.commands.ManualLift;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public static Joystick leftStick = new Joystick(RobotMap.leftStickPort);
  public static Joystick rightStick = new Joystick(RobotMap.rightStickPort);
  public static Joystick sideStick = new Joystick(RobotMap.sideStickPort);
  public static Joystick toggleSwitch = new Joystick(RobotMap.toggleSwitchPort);

  JoystickButton intake = new JoystickButton(rightStick, 1);
  JoystickButton outtake = new JoystickButton(rightStick, 3);
  JoystickButton ballShuttle = new JoystickButton(rightStick, 7);

  JoystickButton intakeHeight = new JoystickButton(rightStick, 5);
  JoystickButton level1 = new JoystickButton(rightStick, 2);
  JoystickButton level2 = new JoystickButton(rightStick, 6);
  JoystickButton cargoShip = new JoystickButton(leftStick, 4);
  //JoystickButton level3 = new JoystickButton(rightStick, 4);

  JoystickButton setForLift = new JoystickButton(sideStick, 3);
  JoystickButton liftRobot = new JoystickButton(sideStick, 5);
  JoystickButton returnLift = new JoystickButton(sideStick, 2);
  JoystickButton manualLift = new JoystickButton(leftStick, 6);
  JoystickButton setForLiftTwo = new JoystickButton(sideStick, 4);
  JoystickButton liftRobotTwo = new JoystickButton(sideStick, 6);

  //JoystickButton debug = new JoystickButton(leftStick, 3);

  public OI() {
    //debug.whenPressed(new MechanismInit());

    intakeHeight.whenPressed(new IntakeLevel()); //intake arm and big arm down to pick up ball from floor/hatch from wall
    level1.whenPressed(new LevelOne());//intake arm inside and big arm at first level on rocket for ball/hatch
    level2.whenPressed(new LevelTwo());//intake arm inside and big arm at second level on rocket for ball/hatch
    cargoShip.whenPressed(new CargoShip());
    //level3.whenPressed(new LevelThree());//intake arm inside and big arm at third level on rocket for ball/hatch

    intake.whenPressed(new SetIntake(SetIntake.intake));//when pressed rollers spin to intake ball/piston retracts for hatch
    intake.whenReleased(new SetIntake(SetIntake.stop));//when released ends above functions

    outtake.whenPressed(new SetIntake(SetIntake.outtake));//when pressed rollers spin to outtake ball/piston extends for hatch
    outtake.whenReleased(new SetIntake(SetIntake.stop));//when released ends above functions

    /*ballShuttle.whenPressed(new SetIntake(SetIntake.shuttle));
    ballShuttle.whenReleased(new SetIntake(SetIntake.stop));*/

    setForLift.whenPressed(new SetForLift());
    liftRobot.whenPressed(new LiftGo());
    setForLiftTwo.whenPressed(new SetForLiftTwo());
    liftRobotTwo.whenPressed(new LiftGoTwo());
    //intakeAfterClimb.whenPressed(new SetIntakeArm(SetIntakeArm.climbFinished)); //NEEDS FIXING!
    returnLift.whenPressed(new ResetLift());

    /*manualLift.whenPressed(new ManualLift(ManualLift.up));
    manualLift.whenReleased(new ManualLift(ManualLift.stop));*/
  }
}