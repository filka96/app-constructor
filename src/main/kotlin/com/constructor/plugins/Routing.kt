package com.constructor.plugins

import com.constructor.CRUD
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.get
import kotlinx.html.*
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/")
        {
            call.respondFile(File("src/main/resources/static/CRUD.html"))
        }
        get("/read")
        {
            val obj : CRUD = CRUD()
            val str = obj.read() // Type ArrayList<String>
            call.respondHtml(HttpStatusCode.OK)
            {
                body {
                    ul {
                        for (comment in str.indices) {
                            li {
                                +str[comment] // it doesn't work
                            }
                        }
                    }
                }
            }
        }
        get("/delete")
        {
            val obj : CRUD = CRUD()
            val temp = 3
            obj.delete(temp)
            call.respondFile(File("src/main/resources/static/CRUD.html"))
        }
    }
}