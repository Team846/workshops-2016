package com.lynbrookrobotics.training.ShooterFlywheel.control

import com.lynbrookrobotics.potassium.Signal
import squants.Dimensionless
import squants.electro.{ElectricPotential, Volts}

object ShooterFlywheelControllers {
  def directControl(speed: Signal[ElectricPotential]) =
    speed.map(s => FlywheelSignal(s, s)).toPeriodic

  def rpmControl(leftHall: Signal[Dimensionless],
                 rightHall: Signal[Dimensionless],
                 target: Double,
                 pGain: Double) = {

    val leftError = ???
    val rightError = ???

    val leftProportional = ???
    val rightProportional = ???

    val leftFeedFwd = ???
    val rightFeedFwd = ???

    val leftCombined = ???
    val rightCombined = ???

    /** uncomment before running! */
//    val speed = leftCombined.zip(rightCombined)
//
//    speed.map { speed =>
//      val left = speed._1
//      val right = speed._2
//
//      FlywheelSignal(
//        left._1 + left._2,
//        right._1 + right._2
//      )
//    }.toPeriodic
  }
}
