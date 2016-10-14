package com.lynbrookrobotics.training.shooterArm.control

import com.lynbrookrobotics.potassium.{Component, Signal}
import squants.electro.{ElectricPotential, Volts}
import squants.time.Milliseconds
import com.lynbrookrobotics.training.WPIClock.notifierClock
import com.lynbrookrobotics.training.config.ShooterArmConfig

class ShooterArm(implicit config: ShooterArmConfig)
  extends Component[ElectricPotential](Milliseconds(5)) {
  val hardware = config.ports.asHardware

  override def defaultController = Signal.constant(Volts(0)).toPeriodic

  val beyondForward = config.shooterArmAngle.map(_ > config.props.forwardLimit)
  val belowReverse = config.shooterArmAngle.map(_ < config.props.reverseLimit)

  def clamp(value: Double, low: Double, high: Double): Double = {
    (value min high) max low
  }

  override def applySignal(signal: ElectricPotential): Unit = {
    val out = if (beyondForward.get && signal.to(Volts) > 0) {
      Volts(0)
    } else if (belowReverse.get && signal.to(Volts) < 0) {
      Volts(0)
    } else signal

    println(clamp(out / Volts(12), -config.props.maxSpeed, config.props.maxSpeed))

    hardware.armMotor.set(clamp(out / Volts(12), -config.props.maxSpeed, config.props.maxSpeed))
  }
}
