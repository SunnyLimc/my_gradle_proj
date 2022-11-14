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
    implementation("com.zaxxer:HikariCP:5.0.1")
}

//application {
//    mainClass.set("me.limc.demo.servlet.Entry")
//}


// Don't be misleading by following, just example, don't use it unless you know what you are doing
//tasks.war {
//
//    println("Coping jdbc driver to \$CATALINA_BASE/lib")
//    from(tasks.war.get().classpath) {
//        into("lib")
//        include("mariadb-java-client*.jar")
//    }
//}