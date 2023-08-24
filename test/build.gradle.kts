import com.appconstructor.gradle.Versions

plugins {
  application
  kotlin("jvm")
  kotlin("plugin.serialization")
}

application {
  mainClass.set("com.constructor.ApplicationKt")

  val isDevelopment: Boolean = project.ext.has("development")
  applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
  mavenCentral()
}
// library
dependencies {
  implementation("io.ktor:ktor-server-core-jvm:${Versions.ktor}")
  implementation("io.ktor:ktor-server-netty-jvm:${Versions.ktor}")
  implementation("ch.qos.logback:logback-classic:${Versions.logback}")
  testImplementation("io.ktor:ktor-server-tests-jvm:${Versions.ktor}")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}")
  // exposed lib - for db CRUD and OOP stuff
  implementation("org.jetbrains.exposed:exposed-core:${Versions.exposed}")
  implementation("org.jetbrains.exposed:exposed-dao:${Versions.exposed}")
  implementation("org.jetbrains.exposed:exposed-jdbc:${Versions.exposed}")
  // html
  implementation("io.ktor:ktor-server-html-builder:${Versions.ktor}")
  implementation("io.ktor:ktor-client-core:${Versions.ktor}")
  implementation("io.ktor:ktor-client-cio:${Versions.ktor}")
  implementation("org.postgresql:postgresql:${Versions.postgresql}")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jre7:1.2.71") // todo: move version to Versions
  // json
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1") // todo: move version to Versions
  implementation("io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}")
  implementation("io.ktor:ktor-server-content-negotiation:${Versions.ktor}")
}