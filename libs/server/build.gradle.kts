import com.appconstructor.gradle.Versions

plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

dependencies {
  api(project(":libs:db"))

  api("io.ktor:ktor-server-core-jvm:${Versions.ktor}")
  api("io.ktor:ktor-server-netty-jvm:${Versions.ktor}")
  api("io.ktor:ktor-server-html-builder:${Versions.ktor}")
  api("io.ktor:ktor-server-status-pages:${Versions.ktor}")
  api("io.ktor:ktor-server-content-negotiation:${Versions.ktor}")
  api("io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}")

  api("io.insert-koin:koin-core:${Versions.koin}")
  api("io.insert-koin:koin-ktor:${Versions.koin}")

  api("app.softwork:kotlinx-uuid-core-jvm:${Versions.uuid}")
}
