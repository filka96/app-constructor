package com.appconstructor.plugins

import com.appconstructor.app.database.entity.AppModel
import com.appconstructor.lobby.LobbyDTO
import com.appconstructor.test.database.model.TableDTO
import com.appconstructor.test.database.entity.TableModel
import com.appconstructor.lobby.entity.LobbyModel
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
                    val lobby = LobbyModel.getLobby(idParameter)
                    call.respond(status = HttpStatusCode.OK, lobby)
                }
                get("/listApp"){
                    val appList = AppModel.getAllApp()
                    call.respond(HttpStatusCode.OK, appList)
                }
                post("/createApp"){
                    call.respond(HttpStatusCode.OK, "")
                }
            }
            post("/create"){
                val jsonQuery = call.receive<LobbyDTO>()
                LobbyModel.createLobby(jsonQuery)
                call.respond(HttpStatusCode.Created, "")
            }
        }
        route("/test") {
            get{
                val temp = TableModel.readAll()
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

                if(isUpdated == 1){
                    call.respond(status = HttpStatusCode.OK, jsonQuery)
                }
                else{
                    call.respond(status = HttpStatusCode.NotFound, "")
                }
            }
            delete("{id}"){
                val jsonQuery = call.parameters.getOrFail<UUID>("id")
                val isDeleted = TableModel.delete(jsonQuery)

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