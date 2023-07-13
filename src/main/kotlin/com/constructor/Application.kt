package com.constructor

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.constructor.plugins.*
import freemarker.cache.ClassTemplateLoader
import io.ktor.server.freemarker.*
//import com.constructor.DAO.*

//import com.constructor.DB_set.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "static")
    }
    //DatabaseFactory.init()
}
