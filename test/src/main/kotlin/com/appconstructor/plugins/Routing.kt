package com.appconstructor.plugins

import com.appconstructor.app.database.entity.getAllApp
import com.appconstructor.lobby.model.LobbyDTO
import com.appconstructor.lobby.entity.createLobby
import com.appconstructor.test.database.model.TableTestDTO
import com.appconstructor.lobby.entity.getLobby
import com.appconstructor.test.database.entity.createRow
import com.appconstructor.test.database.entity.deleteRow
import com.appconstructor.test.database.entity.getAllTableTest
import com.appconstructor.test.database.entity.getTableTest
import com.appconstructor.test.database.entity.updateRow
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
        route("/lobby"){
            route("{id}"){
                get{
                    val idParameter = call.parameters.getOrFail<UUID>("id")
                    val result = getLobby(idParameter)
                    if (result != null) {
                        call.respond(status = HttpStatusCode.OK, "${result.id} " +
                                                                 "${result.title}")
                    }
                    else{
                        call.respond(status = HttpStatusCode.NotFound, "")
                    }
                }
                get("/listApp"){
                    val result = getAllApp()
                    if (result != null ){
                        call.respond(HttpStatusCode.OK, result)
                    }
                    else{
                        call.respond(status = HttpStatusCode.NotFound, "")
                    }
                }
                post("/createApp"){
                    call.respond(HttpStatusCode.OK, "")
                }
            }
            post("/create"){
                val jsonQuery = call.receive<LobbyDTO>()
                createLobby(jsonQuery)
                call.respond(HttpStatusCode.Created, "")
            }
        }
        route("/test") {
            get{
                val temp = getAllTableTest()
                if(temp != null){
                    call.respond(status = HttpStatusCode.OK, temp)
                }
                else{
                    call.respond(status = HttpStatusCode.BadRequest, "")
                }
            }
            get("{id}"){
                val idParameter = call.parameters.getOrFail<UUID>("id")
                val result = getTableTest(idParameter)
                if(result != null){
                    call.respond(HttpStatusCode.OK, "${result.strField} " +
                                                    "${result.intField} " +
                                                    "${result.boolField}")
                }
                else{
                    call.respond(HttpStatusCode.NotFound, "")
                }
            }
            post{
                val jsonQuery = call.receive<TableTestDTO>()
                createRow(jsonQuery)
                call.respond(status = HttpStatusCode.Created, jsonQuery)
            }
            put("{id}"){
                val idParameter = call.parameters.getOrFail<UUID>("id")
                val jsonQuery = call.receive<TableTestDTO>()
                val isUpdated = updateRow(idParameter, jsonQuery)

                if(isUpdated){
                    call.respond(status = HttpStatusCode.Accepted, "")
                }
                else{
                    call.respond(status = HttpStatusCode.NotFound, "")
                }
            }
            delete("{id}"){
                val idParameter = call.parameters.getOrFail<UUID>("id")
                val result = deleteRow(idParameter)

                if(result){
                    call.respond(status = HttpStatusCode.OK, "")
                }
                else{
                    call.respond(status = HttpStatusCode.NotFound, "")
                }
            }
        }
    }
}