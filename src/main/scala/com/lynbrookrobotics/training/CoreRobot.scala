package com.lynbrookrobotics.training

import com.lynbrookrobotics.potassium.Clock
import com.lynbrookrobotics.potassium.events.EventPolling
import com.lynbrookrobotics.training.config.RobotConfig
import com.lynbrookrobotics.training.shooterArm.control.ShooterArm
import squants.time.Milliseconds
import WPISugar._
import com.lynbrookrobotics.training.shooterArm.tasks.ShooterArmJoystickOverride

class CoreRobot(implicit config: RobotConfig, clock: Clock) {
  implicit val driverHardware = config.driver.asHardware
  implicit val shooterArmHardware = config.shooterArm.ports.asHardware

  implicit val shooter = new ShooterArm

  implicit val eventPolling = EventPolling(clock, Milliseconds(20))

  driverHardware.operatorJoystick.buttonPressed(1).foreach(new ShooterArmJoystickOverride)
}
