package com.lynbrookrobotics.training.hardware

import com.lynbrookrobotics.training.config.ShooterFlywheelConfig
import com.lynbrookrobotics.training.sensors.halleffect.HallEffect
import edu.wpi.first.wpilibj.Talon

case class ShooterFlywheelHardware (leftMotor: Talon,
                                    rightMotor: Talon,
                                    leftHall: HallEffect,
                                    rightHall: HallEffect)
object ShooterFlywheelHardware{
  def apply(config: ShooterFlywheelConfig): ShooterFlywheelHardware = {
    ShooterFlywheelHardware(
      leftMotor = new Talon(config.leftMotorPort),
      rightMotor = new Talon(config.rightMotorPort),
      leftHall = new HallEffect(config.leftHallPort),
      rightHall = new HallEffect(config.rightHallPort)
    )
  }
}


