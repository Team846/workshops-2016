package com.lynbrookrobotics.training.ShooterFlywheel.control

import com.lynbrookrobotics.potassium.Signal
import squants.electro.ElectricPotential

object ShooterFlywheelControllers {
  def directControl(speed: Signal[ElectricPotential]) =
    speed.map(s => FlywheelSignal(s, s)).toPeriodic
}
