package com.constructor.plugins

import database.models.read
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.get
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/")
        {
            val result = read()
            call.respondFile(File("src/main/resources/static/CRUD.html"))
        }
        get("/read")
        {
            //TODO show db Table data
            call.respondText("goodbye")
        }
        /*get("/create")
        {
            call.respondFile(File("src/main/resources/static/Create.html"))
        }
        get("/update")
        {
            call.respondFile(File("src/main/resources/static/Update.html"))
        }
        get("/delete")
        {
            call.respondFile(File("src/main/resources/static/Delete.html"))
        }*/
    }
}