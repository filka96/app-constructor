package com.appconstructor

import com.appconstructor.serialization.configureSerialization
import com.appconstructor.plugins.configureRouting
import com.appconstructor.properties.PropertyReader
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

val prop = PropertyReader()

val db_connection = Connections()
val dbTest = db_connection.testConnection(prop.getUrlTest(), prop.getDriver(),
  prop.getLogin(), prop.getPassword())

val dbLobby = db_connection.lobbyConnection(prop.getUrlLobby(), prop.getDriver(),
  prop.getLogin(), prop.getPassword())

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