package com.lynbrookrobotics.training.shooterArm.tasks

import com.lynbrookrobotics.potassium.tasks.ContinuousTask
import com.lynbrookrobotics.training.WPISugar._
import com.lynbrookrobotics.training.hardware.DriverHardware
import com.lynbrookrobotics.training.shooterArm.control.ShooterArm
import com.lynbrookrobotics.training.shooterArm.control.ShooterArmControllers._

class ShooterArmJoystickOverride(implicit driverHardware: DriverHardware,
                                 shooterArm: ShooterArm) extends ContinuousTask {
  override def onStart(): Unit = {
    shooterArm.setController(directControl(driverHardware.operatorJoystick.y))
  }

  override def onEnd(): Unit = {
    shooterArm.resetToDefault()
  }
}
