plugins {
  application
  kotlin("jvm")
  kotlin("plugin.serialization")
}

application {
  mainClass.set("com.appconstructor.ApplicationKt")
}

dependencies {
  implementation(project(":libs:server"))
}
