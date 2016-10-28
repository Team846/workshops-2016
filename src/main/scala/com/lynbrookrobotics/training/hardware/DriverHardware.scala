package com.lynbrookrobotics.training.hardware

import com.lynbrookrobotics.training.config.DriverConfig
import edu.wpi.first.wpilibj.Joystick

case class DriverHardware(operatorJoystick: Joystick)

object DriverHardware {
  def apply(config: DriverConfig): DriverHardware = {
    DriverHardware(new Joystick(config.operatorPort))
  }
}