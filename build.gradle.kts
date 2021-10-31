repositories {
  mavenCentral()
  maven("https://jitpack.io")
}
plugins {
  `java-library`
  `maven-publish`
  kotlin("jvm") version "1.5.31"
}
dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
  testImplementation("com.natpryce:hamkrest:1.8.0.1")
  testImplementation("io.mockk:mockk:1.12.0")
}
tasks.test {
  useJUnitPlatform()
}
publishing {
  publications {
    create<MavenPublication>("tokeinzer") {
      from(components["java"])
    }
  }
}
