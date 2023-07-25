package com.constructor

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.constructor.plugins.*
import org.jetbrains.exposed.sql.Database

val db_conn = Database.connect(
    "jdbc:postgresql://localhost:5432/Test", driver = "org.postgresql.Driver",
    user = "postgreSql_dude", password = "password"
)

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
}