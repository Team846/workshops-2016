package com.lynbrookrobotics.training

import com.lynbrookrobotics.potassium.Clock
import com.lynbrookrobotics.potassium.events.EventPolling
import com.lynbrookrobotics.training.config.RobotConfig
import com.lynbrookrobotics.training.shooterArm.control.ShooterArm
import squants.time.Milliseconds
import WPISugar._
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.{Route, RoutingSettings}
import akka.stream.ActorMaterializer
import com.lynbrookrobotics.funkydashboard.{FunkyDashboard, TimeSeriesNumeric}
import com.lynbrookrobotics.training.shooterArm.tasks.ShooterArmPositionControl
import squants.space.Degrees

class CoreRobot(implicit config: RobotConfig, clock: Clock) {
  implicit val driverHardware = config.driver.asHardware
  implicit val shooterArmConfig = config.shooterArm

  implicit val shooter = new ShooterArm

  implicit val eventPolling = EventPolling(clock, Milliseconds(20))

  driverHardware.operatorJoystick.buttonPressed(1).foreach(new ShooterArmPositionControl(Degrees(20)))
  driverHardware.operatorJoystick.buttonPressed(2).foreach(new ShooterArmPositionControl(Degrees(60)))

  FunkyDashboard.datasetGroup("shooter-arm").addDataset(new TimeSeriesNumeric("Pot Angle")(
    shooterArmConfig.shooterArmAngle.get.to(Degrees)
  ))

  implicit val system = ActorSystem("funky-dashboard")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  implicit val routingSettings = RoutingSettings.default

  Http().bindAndHandle(Route.handlerFlow(FunkyDashboard.route), "0.0.0.0", 8080)

}
