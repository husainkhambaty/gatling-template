package com.common

import java.time.{LocalDate, LocalTime}
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory
import scala.language.postfixOps
import scala.util.Properties

object Configuration {
  val logger = LoggerFactory.getLogger(this.getClass)

  // Setting the default configuration file
  val defaultConfigurationFile = "application.conf"

  // Check if configuration filename was passed while starting Gatling
  val configFileSystemProperty = Properties.propOrElse("configuration", defaultConfigurationFile)

  // Load the configuration file accordingly
  val configFile = Properties.propOrElse("configuration", defaultConfigurationFile) match {
    case configFileName if configFileName == defaultConfigurationFile => ConfigFactory.load()
    case configFileName => ConfigFactory.load(configFileName)
  }

  // Test configuration params
  val users = Integer.parseInt(Properties.propOrElse("Users", configFile.getString("gatling.users")))
  val healthCheckUsers = configFile.getInt("gatling.healthCheckUsers")

  val duration = Integer.parseInt(Properties.propOrElse("Duration", configFile.getString("gatling.duration")))
  val ramp = Integer.parseInt(Properties.propOrElse("UserRampup", configFile.getString("gatling.ramp")))

  val thinkTime = configFile.getInt("gatling.thinkTime")
  val paceFrom = configFile.getInt("gatling.paceFrom")
  val paceTo = configFile.getInt("gatling.paceTo")
  val pacing = configFile.getInt("gatling.pacing")
  val endpoint = configFile.getString("gatling.endpoint")
  val baseUrl = configFile.getString(s"${endpoint}")

  // Test data path
  val dataDirectory = configFile.getString("gatling.dataDirectory")

  // Total execution duration = duration + 2 x ramp
  val executionDuration = duration + ramp + ramp

  // Set the user running this
  val whoAmI =
    if (configFile.hasPath("gatling.whoami")) {
      configFile.getString("gatling.whoami")
    } else {
      "performance"
    }

  // Printing the Configuration
  println(
    s"""
Test Config:
--------------------------
Date: ${LocalDate.now} ${LocalTime.now}
Users: ${users}
Health Check: ${healthCheckUsers}
Ramp Up/Down Time: ${ramp}\t Duration: ${duration}

Endpoint: ${baseUrl}

Total Test Execution Time: ${executionDuration}

       """
  )
}