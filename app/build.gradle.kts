plugins {
    java
    checkstyle
    jacoco
    id("org.sonarqube") version "7.2.2.6593"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}


checkstyle {
    toolVersion = "9.0"
    configDirectory.set(file("config/checkstyle"))
}

sonar {
    properties {
        property("sonar.projectKey", "ndreyserg_java-project-78")
        property("sonar.organization", "ndreyserg")
    }
}