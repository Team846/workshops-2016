package com.lynbrookrobotics.training.config

import com.lynbrookrobotics.training.hardware.DriverHardware
import edu.wpi.first.wpilibj.Joystick

case class DriverConfig(operatorPort: Int) {
  lazy val asHardware = DriverHardware(new Joystick(operatorPort))
}
