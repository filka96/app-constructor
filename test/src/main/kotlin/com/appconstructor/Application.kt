package com.appconstructor

import com.appconstructor.plugins.configureRouting
import com.appconstructor.propertyReaper.PropertyReader
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database

val secret =
  PropertyReader()

val db_connTest = Database.connect(
  url = secret.connectToTest(),
  driver = secret.getDriver(),
  user = secret.getLogin(),
  password = secret.getPassword()
)

val db_connEnvironment = Database.connect(
  url = secret.connectToLobby(),
  driver = secret.getDriver(),
  user = secret.getLogin(),
  password = secret.getPassword()
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
  configureSerialization()
}