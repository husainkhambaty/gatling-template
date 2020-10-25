package com.common

import java.util
import java.util.concurrent.ThreadLocalRandom
import io.gatling.core.Predef._


object CorrelationIdFeeder {
  def getUUID(): String = java.util.UUID.randomUUID.toString

  def apply(): Feeder[String] = {
    Iterator.continually(
      Map(("correlationId", "Id-" ++ getUUID()))
    )
  }
}


object SessionIdFeeder {
  def getUUID(): String = java.util.UUID.randomUUID.toString

  def apply(): Feeder[String] = {
    Iterator.continually(
      Map(("sessionId", "Id-" ++ getUUID()))
    )
  }
}