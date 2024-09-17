import org.springframework.boot.gradle.tasks.bundling.BootJar
import java.net.URI
import java.nio.file.Paths

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.gradleup.shadow:shadow-gradle-plugin:8.3.1")
    }
}

plugins {
    java
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("maven-publish")
    id("com.gradleup.shadow") version "8.3.1"

}
group = "com.pepej"
version = "0.1"

tasks.withType<BootJar> {
    enabled = false
}

repositories {
    mavenCentral()
    maven { url = URI("https://hub.spigotmc.org/nexus/content/groups/public/") }
    maven { url = URI("https://oss.squareland.ru/repository/minecraft") }
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("org.slf4j:slf4j-api")
    implementation("org.slf4j:slf4j-simple")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("com.pepej:papi-core:2.6.4")
    annotationProcessor("com.pepej:papi-core:2.6.4")
    implementation("com.pepej:papi-spring:0.0.15")
    compileOnly("org.spigotmc:spigot-api:1.12.2-R0.1-SNAPSHOT")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.kafka:spring-kafka:3.2.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    archiveFileName = "gamma-$version.jar"
    destinationDirectory.set(File("test-server/plugins/"))
}

publishing {
    repositories {
        maven {
            name = "minecraft"
            url = uri("https://oss.squareland.ru/repository/minecraft")
            credentials {
                username = properties["sonatypeUser"].toString()
                password = properties["sonatypePassword"].toString()

            }
        }
    }
}

