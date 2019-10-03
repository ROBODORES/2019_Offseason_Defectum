/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.SetArm;
import frc.robot.commands.SetIntakeArm;

public class IntakeLevel extends CommandGroup {
  public IntakeLevel() {
    requires(Robot.m_arm);
    requires(Robot.m_intakeArm);

    //For intaking with the intake arm
    /*addSequential(new SetArm(SetArm.LevelTwoHeight));
    addSequential(new SetIntakeArm(SetIntakeArm.outOfTheAway));
    addSequential(new SetArm(SetArm.intakeLevelHeight));
    addSequential(new SetIntakeArm(SetIntakeArm.intake));*/

    //For intaking without
    addSequential(new SetArm(SetArm.LevelTwoHeight));
    addSequential(new SetIntakeArm(SetIntakeArm.stowedAway));
    addSequential(new SetArm(SetArm.intakeLevelHeight));
  }
}
