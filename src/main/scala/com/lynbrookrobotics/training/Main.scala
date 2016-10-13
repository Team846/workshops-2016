package com.lynbrookrobotics.training

import java.util.function.Supplier

import akka.actor.ActorSystem
import com.lynbrookrobotics.funkydashboard.{FunkyDashboard, TimeSeriesNumeric}
import com.lynbrookrobotics.potassium.Signal

object Main extends App {
  val cpuUsage: Signal[Double] = ??? // TODO: create a signal of system cpu usage

  // Plot the CPU usage to a dashboard
  val system = ActorSystem.create("server")
  val dashboard = new FunkyDashboard
  dashboard.bindRoute("0.0.0.0", 8080, system)

  dashboard.datasetGroup("System Stats").addDataset(new TimeSeriesNumeric(
    "CPU Usage",
    new Supplier[Number] {
      def get() = cpuUsage.get.asInstanceOf[Number]
    }
  ))
}
