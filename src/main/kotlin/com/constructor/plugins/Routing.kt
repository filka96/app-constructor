package com.constructor.plugins

import com.constructor.database.CRUD
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.get
import java.io.File
//
fun Application.configureRouting() {
    routing {
        get("/")
        {

            call.respondFile(File("src/main/resources/HTML_templates/CRUD.html"))
        }
        get("/create")
        {
            // input data for example
            val id = 10
            val stringLine = " weather"
            val number = 67
            val boolField = false

            val obj : CRUD = CRUD() // вызвали дефолтный конструктор
            obj.create(id, stringLine, number, boolField)

            call.respondText("Check debug log")
        }
        get("/read")
        {
            val obj : CRUD = CRUD()
            val Data = obj.read()

            try
            {
                println(Data)
            }
            catch (e : Exception)
            {
                println("Error")
                println(e.message)
            }

            call.respondText("Check debug log")
        }
        get("/delete")
        {
            val obj : CRUD = CRUD()
            obj.delete(5)
            call.respondText { "Check debug log" }
        }
    }
}