package com.lynbrookrobotics.training

import com.lynbrookrobotics.potassium.Clock
import com.lynbrookrobotics.potassium.events.EventPolling
import com.lynbrookrobotics.training.ShooterFlywheel.control.ShooterFlywheel
import com.lynbrookrobotics.training.config.RobotConfig
import squants.time.Milliseconds
import com.lynbrookrobotics.training.hardware.{DriverHardware, ShooterFlywheelHardware}
import com.lynbrookrobotics.potassium.frc.Implicits._
import com.lynbrookrobotics.training.ShooterFlywheel.tasks.FlywheelOverride

class CoreRobot(implicit config: RobotConfig, clock: Clock) {
  implicit val driverHardware = DriverHardware(config.driver)

  implicit val shooterFlywheelHardware = ShooterFlywheelHardware(config.shooterFlywheelConfig)

  implicit val shooterFlywheel = new ShooterFlywheel

  implicit val eventPolling = EventPolling(clock, Milliseconds(20))

  driverHardware.operatorJoystick.buttonPressed(1).foreach(new FlywheelOverride)
}
