package com.constructor.plugins

import com.constructor.database.TableModel
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.get
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/")
        {
            call.respondFile(File("src/main/resources/HTML_templates/CRUD.html"))
        }
        get("/create")
        {
            TableModel.insert(65,"jaska", 2333, true)
            call.respondText("Create\nCheck debug log")
        }
        get("/read")
        {
            println(TableModel.readAll())
            call.respondText("Read\nCheck debug log")
        }
        get("/delete")
        {
            TableModel.delete(3)
            call.respondText { "Delete\nCheck debug log" }
        }
        get("/update")
        {
            call.respondText{"Update\nCheck debug log"}
        }
    }
}