/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class LevelTwo extends CommandGroup {
  public LevelTwo() {
    requires(Robot.m_intakeArm);
    requires(Robot.m_arm);

    addSequential(new SetArm(SetArm.LevelTwoHeight));
    addSequential(new SetIntakeArm(SetIntakeArm.stowedAway));
  }
}
