/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.cscore.USBCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;

public class Cameras extends Subsystem {
  /*USBCamera camera1 = CamseraServer.getInstance().startAutomaticCapture(0);
  USBCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
  VideoSink server = CameraServer.getInstance().getServer();

  public Cameras() {
    camera1.setConnectionStrategy(VideoSource.ConnectionStrategy.kConnectionKeepOpen);
    camera2.setConnectionStrategy(VideoSource.ConnectionStrategy.kConnectionKeepOpen);
  }

  public void setCamera(int index) {
    switch (index) {
      case 0:
      server.setSource(camera1);
      break;
      case 1:
      server.setSource(camera2);
      break;
    }
  }*/

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
