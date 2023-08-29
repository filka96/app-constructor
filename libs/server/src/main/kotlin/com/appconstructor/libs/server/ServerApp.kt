package com.appconstructor.libs.server

import com.appconstructor.libs.db.DbConfig
import com.appconstructor.libs.db.connect
import com.appconstructor.libs.server.response.ServerResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.NotFoundException
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.module.Module
import org.koin.ktor.plugin.Koin

abstract class ServerApp(
  val applicationName: String,
) {
  protected open fun routingConfiguration(ktor: Application) {}
  protected open fun inject(module: Module) {}
  protected open fun getServerConfig(): ServerConfig = ServerConfig()
  protected open fun getDbConfig(): DbConfig = DbConfig(dbName = applicationName)
  protected open fun getTables(): List<Table> = listOf()

  private fun koinConfiguration(): Module {
    val module = Module()
    module.single<DbConfig> { getDbConfig() }
    module.single<ServerConfig> { getServerConfig() }
    module.single<Database> { connect(get()) }

    inject(module)
    return module
  }

  private fun ktorConfiguration(ktor: Application) {
    ktor.install(Koin) {
      modules(koinConfiguration())
    }

    ktor.install(StatusPages) {
      exception<Throwable> { call, cause ->
        when (cause) {
          is NotFoundException -> call.respond(HttpStatusCode.NotFound, ServerResponse.error(cause))
          is BadRequestException -> call.respond(HttpStatusCode.BadRequest, ServerResponse.error(cause))
          else -> call.respond(HttpStatusCode.InternalServerError, ServerResponse.error(cause))
        }
      }
    }

    ktor.install(ContentNegotiation) {
      json(Json {
        prettyPrint = true
        isLenient = true
      })
    }

    routingConfiguration(ktor)
  }

  private fun createTables() {
    val db: Database = connect(getDbConfig())

    transaction(db) {
      getTables().forEach { table ->
        SchemaUtils.create(table)
      }
    }
  }

  companion object {
    fun run(app: ServerApp) {
      app.createTables()
      embeddedServer(
        factory = Netty,
        port = app.getServerConfig().port,
        host = app.getServerConfig().host,
        module = fun Application.() = app.ktorConfiguration(this)
      ).start(wait = true)
    }
  }
}
