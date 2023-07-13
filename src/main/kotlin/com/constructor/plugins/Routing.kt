package com.constructor.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.get
import java.io.File

fun Application.configureRouting() {

    routing {
        get("/")
        {
            call.respondFile(File("src/main/resources/static/CRUD.html"))
        }
        get("/read")
        {
            call.respondText { "Read db.html" }
        }
    }
}