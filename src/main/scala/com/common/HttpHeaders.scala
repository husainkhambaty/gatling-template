package com.common

object HttpHeaders {

  val HTTP_HEADERS = Map (
    "X-Correlation-Id" -> s"""test-$${correlationId}""",
    "X-Session-Id" -> s"""test-${Configuration.whoAmI}-$${sessionId}"""
  )

}
