plugins {
  kotlin("multiplatform") version "1.9.0" apply false
  kotlin("plugin.serialization") version "1.9.0" apply false
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
