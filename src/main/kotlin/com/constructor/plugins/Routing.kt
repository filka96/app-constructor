package com.constructor.plugins

import com.constructor.database.TableDTO
import com.constructor.database.TableModel
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.get
import java.io.File
import java.util.*

fun Application.configureRouting() {
    val myUuid = UUID.randomUUID()
    routing {
        get("/")
        {
            call.respondFile(File("src/main/resources/HTML_templates/CRUD.html"))
        }

        val storage = mutableListOf<TableDTO>()

        route("/create")
        {
            get {
                call.respondText("Create\nCheck debug log")
            }
            post{
                val jsonQuery = call.receive<TableDTO>()
                storage.add(jsonQuery)
                TableModel.create(storage)
                call.respondText("" , status = HttpStatusCode.Created)
            }
        }

        get("/read")
        {
            val temp = TableModel.readAll()
            call.respond(temp)
        }
        route("/delete") {
            delete {
                val jsonQuery = call.receive<TableDTO>()
                storage.add(jsonQuery)

                var forDeleteId = myUuid
                for(el in storage) {
                    forDeleteId = el.id
                }

                println(forDeleteId)
                TableModel.delete(forDeleteId)

                call.respondText("Delete\nCheck debug log", status = HttpStatusCode.OK)
            }
        }
        route("/update")
        {
            put {
                val jsonQuery = call.receive<TableDTO>()
                storage.add(jsonQuery)
                TableModel.update(storage)

                call.respondText("Update\nCheck debug log", status = HttpStatusCode.OK)
            }
        }
    }
}