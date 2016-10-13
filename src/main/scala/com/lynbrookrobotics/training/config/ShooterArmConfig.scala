package com.lynbrookrobotics.training.config

import com.lynbrookrobotics.training.hardware.ShooterArmHardware
import edu.wpi.first.wpilibj.{AnalogInput, Talon}

case class ShooterArmConfig(ports: ShooterArmPorts, props: ShooterArmProperties)

case class ShooterArmPorts(motorPort: Int, potPort: Int) {
  lazy val asHardware = ShooterArmHardware(
    new Talon(motorPort),
    new AnalogInput(potPort)
  )
}

case class ShooterArmProperties()