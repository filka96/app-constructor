package com.appconstructor.test

import com.appconstructor.libs.server.ServerApp
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Table
import org.koin.core.module.Module

class Application : ServerApp(
  applicationName = "test",
) {
  override fun inject(module: Module) {
    module.single<TestTable> { TestTable() }
    module.single<TestRepository> { TestRepository(get(), get()) }
    module.single<TestService> { TestService(get()) }
  }

  override fun routingConfiguration(ktor: Application) {
    ktor.testRouting()
  }

  override fun getTables(): List<Table> {
    return listOf(TestTable())
  }
}

fun main() {
  val app = Application()
  ServerApp.run(app)
}
