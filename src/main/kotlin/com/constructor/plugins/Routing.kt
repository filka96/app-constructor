package com.constructor.plugins

import com.constructor.database.models.TableDTO
import com.constructor.database.models.TableModel
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.get
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/")
        {
            //Database.connect("jdbc:postgresql://localhost:5432/Test", "org.postgresql.Driver")
            //val query: Query = TableModel.select { TableModel.id eq 2 }
            val query : Query = TableModel.selectAll()
            query.forEach {
                println(it[TableModel.id])
            }

            call.respondFile(File("src/main/resources/static/CRUD.html"))
            //TODO link on next html page
        }
        get("/create")
        {
            //TODO show db Table data
            call.respondText("goodbye")
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