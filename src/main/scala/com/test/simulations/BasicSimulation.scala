package com.test.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.scenario.Simulation

import com.common.HttpProtocol.HTTP_PROTOCOL
import com.common.{Configuration, CorrelationIdFeeder, SessionIdFeeder}
import com.test.chains.TestService

import scala.concurrent.duration._
import scala.language.postfixOps


class BasicSimulation extends Simulation {

  val correlationIds = CorrelationIdFeeder()
  val sessionIds = SessionIdFeeder()

  val HealthCheckScenario = scenario("Health Check Scenario")
    .during(Configuration.duration) {
      exitBlockOnFail(
        exec(
          pace(60),
            exec(TestService.healthCheck())
        )
      )
    }

  val PeakTestScenario = scenario("Peak Test Scenario")
    .during(Configuration.duration) {
      exitBlockOnFail(
        exec(
          pace(Configuration.paceFrom, Configuration.paceTo),
          feed(sessionIds), feed(correlationIds)
            exec(TestService.testEndpoint())
        )
          .exec(session => { session.attributes.keys.foreach(k => session.remove(k)); session })
      )
    }

  setUp(
      HealthCheckScenario.inject(rampUsers(Configuration.healthCheckUsers) over (Configuration.ramp seconds)),
        PeakTestScenario.inject(rampUsers(Configuration.users) over (Configuration.ramp seconds))
  )
    .protocols(HTTP_PROTOCOL)
    .assertions(forAll.failedRequests.count.is(0))

}
