package com.lynbrookrobotics.training

import com.lynbrookrobotics.training.config._
import com.lynbrookrobotics.training.hardware.DriverHardware
import edu.wpi.first.wpilibj.{IterativeRobot, Joystick}

class LaunchRobot extends IterativeRobot {
  implicit val config = RobotConfig(
    DriverConfig(
      operatorPort = 1
    ),
    ShooterArmConfig(
      ShooterArmPorts(
        motorPort = 4,
        potPort = 1
      ),
      ShooterArmProperties()
    )
  )

  implicit val clock = WPIClock.notifierClock

  private var coreRobot: CoreRobot = null

  override def robotInit(): Unit = {
    coreRobot = new CoreRobot
  }
}
