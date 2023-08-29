package com.appconstructor

import com.appconstructor.libs.server.response.ServerResponse
import com.appconstructor.libs.server.uuid
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.testRouting() {
  val service: TestService by inject<TestService>()

  routing {
    route("/test") {
      post("") {
        val model = call.receive<TestEntity>()
        val created = service.create(model)
        call.respond(ServerResponse.ok(created))
      }
      get("/{id}") {
        val id = uuid(call = call, parameterName = "id")
        val model = service.readById(id)
        call.respond(ServerResponse.ok(model))
      }
      get("") {
        val models = service.readAll()
        call.respond(ServerResponse.ok(models))
      }
      put("/{id}") {
        val id = uuid(call = call, parameterName = "id")
        val model = call.receive<TestEntity>()
        val updated = service.update(id, model)
        call.respond(ServerResponse.ok(updated))
      }
      delete("/{id}") {
        val id = uuid(call = call, parameterName = "id")
        service.deleteById(id)
        call.respond(ServerResponse.ok())
      }
      delete("") {
        val model = call.receive<TestEntity>()
        service.delete(model)
        call.respond(ServerResponse.ok())
      }
    }
  }
}
