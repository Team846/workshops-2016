package com.lynbrookrobotics.training.shooterArm.control

import com.lynbrookrobotics.potassium.{Component, Signal}
import com.lynbrookrobotics.training.hardware.ShooterArmHardware
import squants.electro.{ElectricPotential, Volts}
import squants.time.Milliseconds

import com.lynbrookrobotics.training.WPIClock.notifierClock

class ShooterArm(implicit hardware: ShooterArmHardware)
  extends Component[ElectricPotential](Milliseconds(5)) {
  val defaultController = Signal.constant(Volts(0)).toPeriodic

  override def applySignal(signal: ElectricPotential): Unit = {
    hardware.armMotor.set(signal / Volts(12))
  }
}
