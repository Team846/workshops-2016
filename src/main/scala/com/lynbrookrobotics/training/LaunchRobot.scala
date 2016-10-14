package com.lynbrookrobotics.training

import com.lynbrookrobotics.training.config._
import edu.wpi.first.wpilibj.IterativeRobot
import squants.space.Degrees

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
      ShooterArmProperties(
        shooterPotOffset = Degrees(63),
        forwardLimit = Degrees(83),
        reverseLimit = Degrees(3),
        0.4
      )
    )
  )

  implicit val clock = WPIClock.notifierClock

  private var coreRobot: CoreRobot = null

  override def robotInit(): Unit = {
    coreRobot = new CoreRobot
  }
}
