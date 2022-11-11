plugins{
   `kotlin-dsl`
}

group = "me.limc.demo"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
  implementation("com.diffplug.spotless:spotless-plugin-gradle:latest.release")
//  implementation("org.springframework.boot:spring-boot-gradle-plugin:2.7.5")
//  implementation("io.spring.gradle:dependency-management-plugin:1.1.0")
  implementation(kotlin("gradle-plugin"))
}