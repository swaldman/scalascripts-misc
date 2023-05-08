#!/usr/bin/env -S scala-cli shebang

//> using scala "3.2.2"
//> using lib "io.circe::circe-core:0.14.5"
//> using lib "io.circe::circe-parser:0.14.5"
//> using lib "com.mchange:mchange-commons-java:0.2.20"

import io.circe._
import io.circe.parser._
import com.mchange.v1.io.InputStreamUtils

val encoding =
  if args.length == 1 then args(0) else "UTF-8"

val text = InputStreamUtils.getContentsAsString(System.in, encoding)
parse(text) match {
  case Left(parsingFailure) => System.err.println(parsingFailure)
  case Right(json)          => println(json.spaces2)
}


