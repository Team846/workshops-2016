package com.lynbrookrobotics.training

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.{Route, RoutingSettings}
import akka.stream.ActorMaterializer
import com.lynbrookrobotics.funkydashboard.{FunkyDashboard, TimeSeriesNumeric}
import com.lynbrookrobotics.potassium.Clock
import com.lynbrookrobotics.potassium.events.EventPolling
import com.lynbrookrobotics.training.ShooterFlywheel.control.ShooterFlywheel
import com.lynbrookrobotics.training.config.RobotConfig
import squants.time.Milliseconds
import com.lynbrookrobotics.training.hardware.{DriverHardware, ShooterFlywheelHardware}
import com.lynbrookrobotics.potassium.frc.Implicits._
import com.lynbrookrobotics.training.ShooterFlywheel.tasks.{FlywheelOverride, GoToRPM}
import com.lynbrookrobotics.potassium.frc.Implicits._

class CoreRobot(implicit config: RobotConfig, clock: Clock) {
  implicit val driverHardware = DriverHardware(config.driver)

  implicit val shooterFlywheelHardware = ShooterFlywheelHardware(config.shooterFlywheelConfig)

  implicit val shooterFlywheel = new ShooterFlywheel

  implicit val eventPolling = EventPolling(clock, Milliseconds(20))

  driverHardware.operatorJoystick.buttonPressed(1).foreach(new FlywheelOverride)
  driverHardware.operatorJoystick.buttonPressed(3).foreach(new GoToRPM(shooterFlywheelHardware, shooterFlywheel, 4000.0))
  driverHardware.operatorJoystick.buttonPressed(4).foreach(new GoToRPM(shooterFlywheelHardware, shooterFlywheel, 6000.0))

  // Plot the CPU usage to a dashboard (don't worry too much about what's going on here)
  implicit val system = ActorSystem("funky-dashboard")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  implicit val routingSettings = RoutingSettings.default

  val dashboard = new FunkyDashboard

  Http().bindAndHandle(Route.handlerFlow(dashboard.route), "0.0.0.0", 8080)

  dashboard.datasetGroup("Flywheel")
    .addDataset(new TimeSeriesNumeric("Left Flywheel RPM")(shooterFlywheelHardware.leftHall.getRPM))

  dashboard.datasetGroup("Flywheel")
    .addDataset(new TimeSeriesNumeric("Right Flywheel RPM")(shooterFlywheelHardware.rightHall.getRPM))
}

