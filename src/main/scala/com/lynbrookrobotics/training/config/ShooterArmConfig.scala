package com.lynbrookrobotics.training.config

import com.lynbrookrobotics.potassium.Signal
import com.lynbrookrobotics.training.hardware.ShooterArmHardware
import edu.wpi.first.wpilibj.{AnalogInput, Talon}
import squants.electro.{ElectricPotential, Volts}
import squants.space.{Angle, Degrees}

case class ShooterArmConfig(ports: ShooterArmPorts, props: ShooterArmProperties) {
  lazy val shooterArmPot: Signal[ElectricPotential] =
    Signal(Volts(ports.asHardware.pot.getAverageVoltage))

  lazy val shooterArmAngle: Signal[Angle] =
    shooterArmPot.map(v => (v / Volts(1.853) * Degrees(90)) - props.shooterPotOffset)
}

case class ShooterArmPorts(motorPort: Int, potPort: Int) {
  lazy val asHardware = ShooterArmHardware(
    new Talon(motorPort),
    new AnalogInput(potPort)
  )
}

case class ShooterArmProperties(shooterPotOffset: Angle, forwardLimit: Angle, reverseLimit: Angle, maxSpeed: Double)