package com.lynbrookrobotics.training

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.{Route, RoutingSettings}
import akka.stream.ActorMaterializer

import com.lynbrookrobotics.funkydashboard.{FunkyDashboard, TimeSeriesNumeric}
import com.lynbrookrobotics.potassium.Signal

object Main extends App {
  val cpuUsage: Signal[Double] = ??? // TODO: create a signal of system cpu usage (between -1 and 1)
  val cpuUsagePercentage: Signal[Double] = ??? // TODO: apply a transformation to get usage as a percent (between -100 and 100)

  // Plot the CPU usage to a dashboard (don't worry too much about what's going on here)
  implicit val system = ActorSystem("funky-dashboard")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  implicit val routingSettings = RoutingSettings.default

  Http().bindAndHandle(Route.handlerFlow(FunkyDashboard.route), "0.0.0.0", 8080)

  FunkyDashboard.datasetGroup("System Stats")
    .addDataset(new TimeSeriesNumeric("CPU Usage")(cpuUsage.get))

  FunkyDashboard.datasetGroup("System Stats")
    .addDataset(new TimeSeriesNumeric("CPU %")(cpuUsagePercentage.get))
}
