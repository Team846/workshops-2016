package com.lynbrookrobotics.training

import com.lynbrookrobotics.potassium.Clock
import edu.wpi.first.wpilibj.{Notifier, Utility}
import squants.Time
import squants.time.{Microseconds, Seconds}

object WPIClock {
  implicit val notifierClock = new Clock {
    var lastTime: Option[Time] = None

    override def apply(period: Time)(thunk: (Time) => Unit): Cancel = {
      val currentTime = Microseconds(Utility.getFPGATime)

      val notifier = new Notifier(new Runnable {
        override def run(): Unit = {
          lastTime.foreach { l =>
            thunk(currentTime - l)
          }

          lastTime = Some(currentTime)
        }
      })

      notifier.startPeriodic(period.to(Seconds))

      () => notifier.stop()
    }
  }
}
