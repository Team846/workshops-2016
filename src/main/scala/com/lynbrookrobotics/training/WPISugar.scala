package com.lynbrookrobotics.training

import com.lynbrookrobotics.potassium.Signal
import com.lynbrookrobotics.potassium.events.{ContinuousEvent, EventPolling}
import edu.wpi.first.wpilibj.{GenericHID, Joystick}
import squants.{Dimensionless, Each}

object WPISugar {
  implicit class JoystickWithSignals(joystick: Joystick) {
    val y: Signal[Dimensionless] = Signal(Each((joystick: GenericHID).getY))

    def buttonPressed(button: Int)(implicit polling: EventPolling): ContinuousEvent = {
      Signal(joystick.getRawButton(button)).filter(down => down)
    }
  }
}
