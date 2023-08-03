val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposedVersion: String by project
val postgresqlVersion: String by project

plugins {
  kotlin("multiplatform") version "1.9.0" apply false
  kotlin("plugin.serialization") version "1.9.0" apply false
  id("io.ktor.plugin") version "2.3.2"
}

tasks.wrapper {
  distributionType = Wrapper.DistributionType.ALL
  gradleVersion = "8.2.1"
}

allprojects {
  version = "0.1.1" // todo: generate by git version

  repositories {
    mavenCentral()
  }
}

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
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jre7:1.2.71")
    // json
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
}