package com.constructor.plugins

import com.constructor.database.models.TableDTO
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.html.*
import io.ktor.server.routing.get
import kotlinx.html.body
import kotlinx.html.button
import java.io.File

fun Application.configureRouting() {
    val defaultServerLink: String = "http://127.0.0.1:8080"

    routing {
        get("/")
        {
            call.respondFile(File("src/main/resources/static/CRUD.html"))
            //TODO link on next html page
        }
        get("/read")
        {
            //TODO show db Table data
            val myDB = TableDTO(12, "fuck", 43, true)
            call.respond(FreeMarkerContent("Read.html", mapOf("table" to myDB)))
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