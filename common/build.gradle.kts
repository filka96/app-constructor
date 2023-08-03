import com.appconstructor.gradle.Versions

plugins {
  kotlin("multiplatform")
}

kotlin {
  jvm()
  js {
    browser()
  }

  sourceSets {
    commonMain {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerializationJson}")
      }
    }
  }
}
