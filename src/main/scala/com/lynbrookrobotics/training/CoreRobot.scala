package com.lynbrookrobotics.training

import com.lynbrookrobotics.potassium.Clock
import com.lynbrookrobotics.potassium.events.EventPolling

import com.lynbrookrobotics.training.config.RobotConfig

import squants.time.Milliseconds

import com.lynbrookrobotics.training.hardware.DriverHardware

class CoreRobot(implicit config: RobotConfig, clock: Clock) {
  implicit val driverHardware = DriverHardware(config.driver)

  // TODO: configure and contstruct shooter flywheel

  implicit val eventPolling = EventPolling(clock, Milliseconds(20))

  // TODO: map button events to tasks
}
