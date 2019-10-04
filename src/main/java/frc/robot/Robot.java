/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
   __ __   ___     ______    ______
  / // /  |__ \   / ____/   / ____/
 / // /_  __/ /  /___ \    /___ \  
/__  __/ / __/  ____/ /   ____/ /  
  /_/   /____/ /_____/   /_____/ 
  _____   _                ____            _                   _                             
 |_   _| | |__     ___    |  _ \    ___   | |__     ___     __| |   ___    _ __    ___   ___ 
   | |   | '_ \   / _ \   | |_) |  / _ \  | '_ \   / _ \   / _` |  / _ \  | '__|  / _ \ / __|
   | |   | | | | |  __/   |  _ <  | (_) | | |_) | | (_) | | (_| | | (_) | | |    |  __/ \__ \
   |_|   |_| |_|  \___|   |_| \_\  \___/  |_.__/   \___/   \__,_|  \___/  |_|     \___| |___/
                                                                                             
*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

//import java.awt.List;

import edu.wpi.first.cameraserver.CameraServer;

//Subsystems
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ArmPID;
import frc.robot.subsystems.WristPID;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.IntakeArmPID;
import frc.robot.subsystems.JeVois;

//Commands
import frc.robot.commands.MechanismInit;

public class Robot extends TimedRobot {
  public static DriveTrain m_driveTrain;
  public static ArmPID m_arm;
  public static WristPID m_wrist;
  public static Intake m_intake;
  public static LEDs m_LEDs;
  public static Lift m_lift;
  public static IntakeArmPID m_intakeArm;
  public static JeVois m_jeVois;

  public static OI m_oi;

  public static MechanismInit m_init;

  boolean zeroed = false;
  boolean disabled = false;

  Command m_autonomousCommand;

  @Override
  public void robotInit() {
    m_driveTrain = new DriveTrain();
    m_arm = new ArmPID();
    m_wrist = new WristPID();
    m_intake = new Intake();
    m_LEDs = new LEDs();
    m_lift = new Lift();
    m_intakeArm = new IntakeArmPID();
    m_jeVois = new JeVois();
    m_oi = new OI();
  
    m_init = new MechanismInit();

    reset();    

    // chooser.addOption("My Auto", new MyAutoCommand());

    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
    //disabled = true;
    m_intakeArm.killEbrake();
    /*zeroed = false;
    reset();*/
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    if(!zeroed) {
      reset();
      zeroed = true;
    }
    m_intakeArm.startEbrake();

    if (!m_init.isCompleted() || disabled) m_init.start();
                                                      
    /*if (m_autonomousCommand != null) { //For our nonexistent autonomous
      m_autonomousCommand.start();
    }*/
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if(!zeroed) {
      reset();
      zeroed = true;
    }
    m_intakeArm.startEbrake();

    if (!m_init.isCompleted() || disabled) m_init.start();

    /*if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }*/
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
    System.out.println("Wrist:" + m_wrist.returnPIDInput());
  }

  public void reset() {
    m_arm.reset();
    m_wrist.reset();
    m_intakeArm.resetEncoder();
    m_arm.disable();
    m_wrist.disable();
    m_intakeArm.disable();
  }
}
