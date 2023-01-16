#!/usr/bin/env -S scala-cli shebang

//> using scala "3.2.1"
//> using lib "com.lihaoyi::requests:0.8.0"

// see https://github.com/chubin/wttr.in

import java.net.URLEncoder

if (args.isEmpty)
   println("Usage: weather [-v] <place name>")
   System.exit(1)

val (opts, placeParts) = args.partition( _.startsWith("-") )

val place = placeParts.mkString(" ")

val v2 = opts.find( _.startsWith("-v") ).nonEmpty

val urlStart =
  if v2 then "https://v2.wttr.in/" else "https://wttr.in/"

// the only way i could get wttr.in to give me 'text/plain' was to pretend to be curl
val response =
  requests.get( urlStart + URLEncoder.encode(place, "UTF8"), headers=Map("accept" -> "text/plain", "user-agent" -> "curl/7.82.0") )

println( response.data )


