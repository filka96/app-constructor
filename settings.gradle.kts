rootProject.name = "app-constructor"

pluginManagement {
  repositories {
    gradlePluginPortal()
  }
}

include(":core")
include(":libs:db")
include(":libs:server")
include(":plugins")
include(":test")
