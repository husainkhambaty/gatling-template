package com.common

import io.gatling.core.Predef._
import io.gatling.http.Predef.http
import io.gatling.http.request.ExtraInfo
import io.gatling.commons.util.StringHelper

object HttpProtocol {
  val HTTP_PROTOCOL = http
    .inferHtmlResources(
      BlackList(
        """.*\.css""",
        """.*\.gif""",
        """.*\.ico""",
        """.*\.jpeg""",
        """.*\.jpg""",
        """.*\.png""",
        """.*\.(t|o)tf""",
        """.*\.wotf"""
      ),
      WhiteList()
    )
    .acceptHeader("application/json, text/plain, */*")
    .acceptEncodingHeader("gzip, deflate, sdch")
    .acceptLanguageHeader("en-GB,en-US;q=0.8,en;q=0.6")
    .connectionHeader("keep-alive")
    .contentTypeHeader("application/json;charset=utf-8")
    .userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.111 Safari/537.36 Gatling")
    .extraInfoExtractor {
      extraInfo => List(getExtraInfo(extraInfo))
    }

  private def getOrEmpty(extraInfo: ExtraInfo, key: String): String = {
    if (extraInfo.session.contains(key)) {
      extraInfo.session(key).as[String]
    }
    else {
      ""
    }
  }
  private def getExtraInfo(extraInfo: ExtraInfo): String = {
    "someExtraInfo=" + getOrEmpty(extraInfo, "someExtraInfo");
  }
}