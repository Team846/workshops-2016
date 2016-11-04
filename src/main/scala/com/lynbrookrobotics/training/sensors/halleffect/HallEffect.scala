package com.lynbrookrobotics.training.sensors.halleffect

import com.lynbrookrobotics.potassium.Signal
import edu.wpi.first.wpilibj.{Counter, DigitalInput}
import squants.{Dimensionless, Each}

class HallEffect(channel: Int) extends Counter() {
  private val source: DigitalInput = new DigitalInput(channel)
  private var curRPM: Double = 0.0

  setUpSource(source)

  def signal: Signal[Dimensionless] = Signal(Each(getRPM()))
  def markNotWorking() = println("ERROR ERROR: HALL EFFECT IS NOT WORKING")

  def update() = {
    var reported = 60 / getPeriod

    if (getStopped)
      reported = 0

    if (reported < 20000)
      curRPM = reported
  }

  def getRPM(): Double = {
    update()
    curRPM
  }
}
