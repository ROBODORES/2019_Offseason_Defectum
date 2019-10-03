/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class SetForLiftTwo extends CommandGroup {
  public SetForLiftTwo() {
    requires(Robot.m_intakeArm);
    requires(Robot.m_arm);

    addSequential(new SetArm(SetArm.setForLift));
    addSequential(new SetIntakeArm(SetIntakeArm.toTheFloor));
    addSequential(new SetArm(SetArm.returnHome));
    addParallel(new LiftUp());
    addSequential(new SetIntakeArm(SetIntakeArm.intake));
  }
}
