val ktor_version: String by project // todo: Property name 'ktor_version' should not contain underscores
val kotlin_version: String by project // todo: Property name 'ktor_version' should not contain underscores
val logback_version: String by project // todo: Property name 'ktor_version' should not contain underscores
val exposedVersion: String by project
val postgresqlVersion: String by project

plugins {
  kotlin("jvm") version "1.9.0"
  id("io.ktor.plugin") version "2.3.2"
  kotlin("plugin.serialization") version "1.9.0"
}

group = "com.constructor"
version = "0.0.1"
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
  implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
  implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
  implementation("ch.qos.logback:logback-classic:$logback_version")
  testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
  // exposed lib - for db CRUD and OOP stuff
  implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
  // html
  //implementation("io.ktor:ktor-server-freemarker:$ktor_version")
  implementation("io.ktor:ktor-server-html-builder:$ktor_version")
  implementation("io.ktor:ktor-client-core:$ktor_version")
  implementation("io.ktor:ktor-client-cio:$ktor_version")
  implementation("org.postgresql:postgresql:$postgresqlVersion")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jre7:1.2.71") // todo: move version to gradle.properties
  // json
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1") // todo: move version to gradle.properties
  // uuid
  implementation("com.eaio.uuid:uuid:3.2") // todo: move version to gradle.properties
}
