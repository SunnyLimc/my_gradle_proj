plugins {
  id("com.diffplug.spotless")
  id("org.jetbrains.kotlin.jvm")
//  id("org.springframework.boot")
//  id("io.spring.dependency-management")
}

repositories {
  mavenCentral()
}

dependencies {
  constraints {
    implementation("org.apache.commons:commons-text:latest.release")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  }
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
  implementation ("org.apache.logging.log4j:log4j-core:latest.release")
  testImplementation("org.junit.jupiter:junit-jupiter:latest.release")
//  implementation("org.springframework.boot:spring-boot-starter-web")
}

spotless {
  kotlin {
//    ktlint().editorConfigOverride(mapOf("indent_size" to 2))
    diktat().configFile("/home/limc/Coding/Java/me.limc/demo/buildSrc/src/main/resources/diktat-analysis.yml")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
