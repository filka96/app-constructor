package com.appconstructor.plugins

import com.appconstructor.database.*
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*


fun Application.configureRouting() {
    routing {
        route("/") {
            get{
                val temp = TableModel.readAll()
                call.respond(temp)
            }

            post{
                val jsonQuery = call.receive<TableDTO>()
                TableModel.create(jsonQuery)
                call.respond(status = HttpStatusCode.Created, jsonQuery)
            }

            put{
                val jsonQuery = call.receive<TableDTO>()
                TableModel.update(jsonQuery)
                call.respond(status = HttpStatusCode.OK, jsonQuery)
            }

            delete{
                val jsonQuery = call.receive<TableDTO>()
                TableModel.delete(jsonQuery.id)
                call.respond(status =  HttpStatusCode.OK, jsonQuery.id)
            }
        }
    }
}