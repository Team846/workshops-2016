package com.lynbrookrobotics.training.ShooterFlywheel.control

import com.lynbrookrobotics.potassium.{Component, PeriodicSignal, Signal}
import com.lynbrookrobotics.training.hardware.ShooterFlywheelHardware
import squants.electro.{ElectricPotential, Volts}
import squants.time.Milliseconds
import com.lynbrookrobotics.potassium.frc.Implicits._

case class FlywheelSignal(left: ElectricPotential, right: ElectricPotential)

class ShooterFlywheel(implicit hardware: ShooterFlywheelHardware)
  extends Component[FlywheelSignal](Milliseconds(5)) {
  override def defaultController: PeriodicSignal[FlywheelSignal] =
    Signal.constant(FlywheelSignal(Volts(0), Volts(0))).toPeriodic

  override def applySignal(signal: FlywheelSignal): Unit = {
    hardware.leftMotor.set(signal.left / Volts(12))
    hardware.rightMotor.set(signal.right / Volts(12))
  }
}
