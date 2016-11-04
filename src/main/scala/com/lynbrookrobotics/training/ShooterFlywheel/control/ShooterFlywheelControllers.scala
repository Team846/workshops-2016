package com.lynbrookrobotics.training.ShooterFlywheel.control

import com.lynbrookrobotics.potassium.Signal
import squants.Dimensionless
import squants.electro.ElectricPotential

object ShooterFlywheelControllers {
  def directControl(speed: Signal[ElectricPotential]) =
    speed.map(s => FlywheelSignal(s, s)).toPeriodic

  def rpmControl(leftHall: Signal[Dimensionless],
                 rightHall: Signal[Dimensionless],
                 target: Double,
                 pGain: Double) = {

    val leftError = leftHall.map(target - _.value)
    val rightError = rightHall.map(target - _.value)

    val leftProportional = leftError.map(e => new ElectricPotential(e * pGain))
    val rightProportional = rightError.map(e => new ElectricPotential(e * pGain))

    val leftFeedFwd = leftError.map(e => (e / 6000) * new ElectricPotential(12))
    val rightFeedFwd = leftError.map(e => (e / 6000) * new ElectricPotential(12))

    val leftCombined = leftProportional.zip(leftFeedFwd)
    val rightCombined = rightProportional.zip(rightFeedFwd)

    val speed = leftCombined.zip(rightCombined)

    speed.map { speed =>
      val left = speed._1
      val right = speed._2

      FlywheelSignal(
        left._1 + left._2,
        right._1 + right._2
      )
    }.toPeriodic
  }
}
