plugins {
    id("demo.kotlin-application-conventions")
    war
}

group = "me.limc.demo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.servlet:jakarta.servlet-api:latest.release")
}

//application {
//    mainClass.set("me.limc.demo.servlet.Entry")
//}