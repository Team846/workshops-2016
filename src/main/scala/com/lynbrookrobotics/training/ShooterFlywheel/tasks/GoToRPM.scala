package com.lynbrookrobotics.training.ShooterFlywheel.tasks

import com.lynbrookrobotics.potassium.{PeriodicSignal, Signal}
import com.lynbrookrobotics.potassium.tasks.ContinuousTask
import com.lynbrookrobotics.training.ShooterFlywheel.control.{ShooterFlywheel, ShooterFlywheelControllers}
import com.lynbrookrobotics.training.hardware.ShooterFlywheelHardware
import squants.Dimensionless

class GoToRPM(hardware: ShooterFlywheelHardware, shooterFlywheel: ShooterFlywheel, rpm: Double) extends ContinuousTask {
  override def onStart(): Unit = {
    val leftRPM: Signal[Dimensionless] = hardware.leftHall.signal
    val rightRPM: Signal[Dimensionless] = hardware.rightHall.signal

    val signal = ShooterFlywheelControllers.rpmControl(
      leftRPM, rightRPM, rpm, 0.1/12.0)
    shooterFlywheel.setController(signal)
  }

  override def onEnd(): Unit = {
    shooterFlywheel.resetToDefault()
  }
}
