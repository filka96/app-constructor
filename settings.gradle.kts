rootProject.name = "app-constructor"

pluginManagement {
  repositories {
    gradlePluginPortal()
  }
}

include(":common")
include(":core")
include(":libs:db")
include(":plugins")
include(":test")
