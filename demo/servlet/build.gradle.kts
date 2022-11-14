plugins {
    id("demo.kotlin-application-conventions")
    war
    id("com.dorongold.task-tree") version "2.1.0"
}

group = "me.limc.demo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.servlet:jakarta.servlet-api:latest.release")
    implementation("jakarta.servlet.jsp:jakarta.servlet.jsp-api:latest.release")
    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:latest.release")
    implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:latest.release")
    implementation("org.mariadb.jdbc:mariadb-java-client:latest.release")
}

//application {
//    mainClass.set("me.limc.demo.servlet.Entry")
//}

tasks.register<Copy>("MariadbJdbcDriver") {
    println(tasks.named("war").get().classpath)
}

tasks.withType<War> {
    dependsOn(tasks.withType<Copy>())
}

