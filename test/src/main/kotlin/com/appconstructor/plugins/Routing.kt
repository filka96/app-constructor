package com.appconstructor.plugins

import com.appconstructor.database.*
import com.appconstructor.databaseProperties.DatabaseProperties
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.util.getOrFail
import java.util.UUID

fun Application.configureRouting() {
    routing {
        route("/test") {
            get{
                val temp = TableModel.readAll()
                val secret : DatabaseProperties =
                    DatabaseProperties("test/src/main/resources/application.properties")
                println(secret.getPassword())
                println(secret.getLogin())
                call.respond(temp)
            }
            get("{id}"){
                val query = call.parameters.getOrFail<UUID>("id")
                val dbAnswer = TableModel.readOneRow(query)
                call.respond(HttpStatusCode.OK, dbAnswer)
            }
            post{
                val jsonQuery = call.receive<TableDTO>()
                TableModel.create(jsonQuery)
                call.respond(status = HttpStatusCode.Created, jsonQuery)
            }
            put{
                val jsonQuery = call.receive<TableDTO>()
                val isUpdated = TableModel.update(jsonQuery)

                if(isUpdated == 1) {
                    call.respond(status = HttpStatusCode.OK, jsonQuery)
                }
                else{
                    call.respond(status = HttpStatusCode.NotFound, "")
                }
            }
            delete{
                val jsonQuery = call.receive<TableDTO>()
                val isDeleted = TableModel.delete(jsonQuery.id)

                if(isDeleted == 1){
                    call.respond(status = HttpStatusCode.OK, "")
                }
                else{
                    call.respond(status = HttpStatusCode.NotFound, "")
                }
            }
        }
    }
}