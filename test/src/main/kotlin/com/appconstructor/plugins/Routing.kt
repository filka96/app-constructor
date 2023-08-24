package com.appconstructor.plugins

import com.appconstructor.app.database.entity.createApp
import com.appconstructor.app.database.entity.getAllApp
import com.appconstructor.app.database.entity.getOneApp
import com.appconstructor.app.database.model.AppDTO
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
import io.ktor.server.html.respondHtml
import io.ktor.server.request.receive
import io.ktor.server.response.respondFile
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.util.getOrFail
import java.io.File
import java.util.UUID
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.br
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.hr
import kotlinx.html.title

fun Application.configureRouting() {
    routing {
        route("/lobby") {
            route("{lobbyId}") {
                get {
                    val idParameter = call.parameters.getOrFail<UUID>("lobbyId")
                    val lobby = getLobby(idParameter)

                    if (lobby != null) {
                        val appList = getAllApp()
                        call.respondHtml(HttpStatusCode.OK){
                            head{
                                title{+lobby.title}
                            }
                            body{
                                h1{+"You open lobby ${lobby.title}"}
                                h1{+"id: ${lobby.id}"}
                                a("http://127.0.0.1:8080/lobby/${idParameter}/createApp"){
                                    +"CreateApp"
                                }
                                h1{+"List app:"}
                                hr{}
                                for(app in appList){
                                    a("http://127.0.0.1:8080/lobby/${idParameter}/editApp/${app.id}"){
                                        +"${app.title}"
                                    }
                                    br{}
                                }
                            }
                        }
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
                route("/createApp") {
                    get {
                        call.respondFile(File("test/src/main/resources/templates/lobby/createApp.html"))
                    }
                    post {
                        // рисуем форму
                        val jsonData = call.receive<AppDTO>()
                        createApp(jsonData)
                        call.respond(HttpStatusCode.OK)
                    }
                }
                route("/editApp/{appId}"){
                    get{
                        val idParameter = call.parameters.getOrFail<UUID>("appId")
                        // мне кажется это лишняя выгрузка
                        val nameApp = getOneApp(idParameter)
                        if(nameApp != null) {
                            call.respondHtml {
                                head { title { +"edit App page" } }
                                body { h1 { +"${nameApp.title}" } }
                            }
                        }else{
                            call.respond(HttpStatusCode.NotFound)
                        }
                    }
                }
            }
        }
        route("/test") {
            get{
                val temp = getAllTableTest()

                if(temp != null){
                    call.respond(HttpStatusCode.OK, temp)
                } else{
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            get("{id}"){
                val idParameter = call.parameters.getOrFail<UUID>("id")
                val result = getTableTest(idParameter)

                if(result != null){
                    call.respond(HttpStatusCode.OK, "${result.strField} " +
                                                    "${result.intField} " +
                                                    "${result.boolField}")
                } else{
                    call.respond(HttpStatusCode.NotFound)
                }
            }
            post{
                val jsonQuery = call.receive<TableTestDTO>()
                createRow(jsonQuery)
                call.respond(HttpStatusCode.Created, jsonQuery)
            }
            put("{id}"){
                val idParameter = call.parameters.getOrFail<UUID>("id")
                val jsonQuery = call.receive<TableTestDTO>()
                val isUpdated = updateRow(idParameter, jsonQuery)

                if(isUpdated){
                    call.respond(HttpStatusCode.Accepted)
                } else{
                    call.respond(HttpStatusCode.NotFound)
                }
            }
            delete("{id}"){
                val idParameter = call.parameters.getOrFail<UUID>("id")
                val result = deleteRow(idParameter)

                if(result){
                    call.respond(HttpStatusCode.OK)
                } else{
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}