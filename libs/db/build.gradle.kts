import com.appconstructor.gradle.Versions

plugins {
  kotlin("jvm")
}

dependencies {
  implementation("org.jetbrains.exposed:exposed-dao:${Versions.exposed}")
  implementation("org.jetbrains.exposed:exposed-jdbc:${Versions.exposed}")
  implementation("org.jetbrains.exposed:exposed-json:${Versions.exposed}")
  implementation("org.jetbrains.exposed:exposed-kotlin-datetime:${Versions.exposed}")

  implementation("org.postgresql:postgresql:${Versions.postgresql}")
  implementation("com.h2database:h2:${Versions.h2}")
}
