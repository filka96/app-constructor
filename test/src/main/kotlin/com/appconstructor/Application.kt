package com.appconstructor

import com.appconstructor.plugins.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database

val db_conn = Database.connect(
  "jdbc:postgresql://localhost:5432/Test",
  driver = "org.postgresql.Driver",
  user = "...",
  password = "..."
)

fun main() {
  embeddedServer(
    Netty,
    port = 8080,
    host = "0.0.0.0",
    module = Application::module
  ).start(wait = true)
}

fun Application.module() {
  configureRouting()
}
