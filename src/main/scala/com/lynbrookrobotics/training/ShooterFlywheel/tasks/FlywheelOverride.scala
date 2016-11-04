package com.lynbrookrobotics.training.ShooterFlywheel.tasks

import com.lynbrookrobotics.potassium.tasks.ContinuousTask
import com.lynbrookrobotics.training.ShooterFlywheel.control.{ShooterFlywheel, ShooterFlywheelControllers}
import com.lynbrookrobotics.training.hardware.DriverHardware
import squants.electro.Volts
import com.lynbrookrobotics.potassium.frc.Implicits._

class FlywheelOverride(implicit driverHardware: DriverHardware, shooterFlywheel: ShooterFlywheel) extends
  ContinuousTask {
  override def onStart(): Unit = {
    val signal = ShooterFlywheelControllers.directControl(
      driverHardware.operatorJoystick.y.map(_.toEach * Volts(12)))
    shooterFlywheel.setController(signal)
  }

  override def onEnd(): Unit = {
    shooterFlywheel.resetToDefault()
  }
}
