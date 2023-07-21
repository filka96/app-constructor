package com.constructor.plugins

import com.constructor.database.TableDTO
import com.constructor.database.TableModel
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.routing.get
import kotlinx.html.*
import java.io.File
import java.util.*

fun Application.configureRouting() {
    val myUuid = UUID.randomUUID()
    routing {
        get("/")
        {
            call.respondFile(File("src/main/resources/HTML_templates/CRUD.html"))
        }

        route("/create")
        {
            post{
                call.respondText("Create\nCheck debug log")
                val jsonQuery = mutableListOf<TableDTO>()
                jsonQuery.addAll(
                    arrayOf(
                        TableDTO(myUuid, "Jane", 876, true),
                        TableDTO(myUuid, "John", 2424, false)
                    )
                )
                call.respond(jsonQuery)
            }
        }

        get("/read")
        {
            val temp = TableModel.readAll()
            call.respondHtml (HttpStatusCode.OK)
            {
                body {
                    ul {
                        for (el in temp) {
                            +("id : " + (el.id).toString())
                            +"                  "
                            +("strf : " + (el.stringField))
                            +"                 "
                            +("int : " + (el.intfield).toString())
                            +"                  "
                            +("boolfield : " + (el.boolfield).toString())
                            br {}
                        }
                    }
                }
            }
            call.respond(TableDTO)
        }
        route("/delete")
        {
            post {
                call.respondText { "Delete\nCheck debug log" }
            }
        }
        route("/update")
        {
            put {
                call.respondText{"Update\nCheck debug log"}
            }
        }
    }
}