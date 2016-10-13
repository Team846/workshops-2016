package com.lynbrookrobotics.training.shooterArm.control

import com.lynbrookrobotics.potassium.{PeriodicSignal, Signal}
import squants.Dimensionless
import squants.electro.ElectricPotential

object ShooterArmControllers {
  def directControl(speed: Signal[Dimensionless]): PeriodicSignal[ElectricPotential] =
    ??? // TODO: scale the dimensionless value to an output voltage between -12V and 12V
}
