import com.appconstructor.gradle.Versions

plugins {
  kotlin("jvm")
}

dependencies {
  api("org.jetbrains.exposed:exposed-core:${Versions.exposed}")
  api("org.jetbrains.exposed:exposed-jdbc:${Versions.exposed}")
  api("org.jetbrains.exposed:exposed-json:${Versions.exposed}")
  api("org.jetbrains.exposed:exposed-crypt:${Versions.exposed}")
  api("org.jetbrains.exposed:exposed-kotlin-datetime:${Versions.exposed}")

  api("org.postgresql:postgresql:${Versions.postgresql}")
  api("com.h2database:h2:${Versions.h2}")

  api("app.softwork:kotlinx-uuid-exposed:${Versions.uuid}")
}
