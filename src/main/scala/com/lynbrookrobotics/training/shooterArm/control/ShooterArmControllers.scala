package com.lynbrookrobotics.training.shooterArm.control

import com.lynbrookrobotics.potassium.PeriodicSignal
import com.lynbrookrobotics.training.config.ShooterArmConfig
import squants.electro.{ElectricPotential, Volts}
import squants.space.{Angle, Degrees}

object ShooterArmControllers {
  def positionControl(target: Angle)
                     (implicit shooterArmConfig: ShooterArmConfig): PeriodicSignal[ElectricPotential] = {
    // TODO: implement bang-bang, test, then replace with proportional control
    ???
  }
}
