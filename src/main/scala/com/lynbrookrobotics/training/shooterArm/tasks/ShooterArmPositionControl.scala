package com.lynbrookrobotics.training.shooterArm.tasks

import com.lynbrookrobotics.potassium.tasks.ContinuousTask
import com.lynbrookrobotics.training.WPISugar._
import com.lynbrookrobotics.training.config.ShooterArmConfig
import com.lynbrookrobotics.training.hardware.DriverHardware
import com.lynbrookrobotics.training.shooterArm.control.ShooterArm
import com.lynbrookrobotics.training.shooterArm.control.ShooterArmControllers._
import squants.Angle

class ShooterArmPositionControl(target: Angle)(implicit shooterArmConfig: ShooterArmConfig,
                                shooterArm: ShooterArm) extends ContinuousTask {
  override def onStart(): Unit = {
    shooterArm.setController(positionControl(target))
  }

  override def onEnd(): Unit = {
    shooterArm.resetToDefault()
  }
}
