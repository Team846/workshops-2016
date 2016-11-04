package com.lynbrookrobotics.training.hardware

import com.lynbrookrobotics.training.config.ShooterFlywheelConfig
import edu.wpi.first.wpilibj.{DigitalInput, Talon}

/**
  * Created by sanghakchun on 10/28/16.
  */
 case class ShooterFlywheelHardware (leftMotor: Talon,
                                     rightMotor: Talon,
                                     leftHall: DigitalInput,
                                     rightHall: DigitalInput)
object ShooterFlywheelHardware{
  def apply(config: ShooterFlywheelConfig): ShooterFlywheelHardware = {
    ShooterFlywheelHardware(
      leftMotor = new Talon(config.leftMotorPort),
      rightMotor = new Talon(config.rightMotorPort),
      leftHall = new DigitalInput(config.leftHallPort),
      rightHall = new DigitalInput(config.rightHallPort)
    )
  }
}


