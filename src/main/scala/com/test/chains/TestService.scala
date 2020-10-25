package com.test.chains

import com.common.Configuration._
import com.common.HttpHeaders._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TestService {

  val urlBase = s"${baseUrl}/test-service"

  // Template Health Check
  def healthCheck() =
    exec(
      http("Health Check")
        .get(urlBase ++ "/health")
        .check(
          status.is(200), regex("UP").exists, regex("DOWN").notExists
        )
    )

  def testEndpoint() =
    exec(
      http("Test Endpoint")
        .get(urlBase ++ "/endpoint1")
        .headers(HTTP_HEADERS)
        .check(
          status.is(200)
        )
    )


}