/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.commands.ReadJeVois;

/**
 * Add your docs here.
 */
public class JeVois extends Subsystem {
  SerialPort cam = null;

  public JeVois() {
    cam = new SerialPort(115200, SerialPort.Port.kMXP);
  }

  public void print() {
    try {
      System.out.println(cam.readString());
    } catch(Exception e) {
      System.out.println("Error");
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ReadJeVois());
  }
}
