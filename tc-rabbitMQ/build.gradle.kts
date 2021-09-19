import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.spring") version "1.5.21"
}

group = "br.com.edrs"
version = "0.1"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.github.pedrocomitto:rabbit4lazy:1.0.0")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.github.javafaker:javafaker:1.0.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("org.springframework.amqp:spring-rabbit-test")

    testImplementation("org.testcontainers:junit-jupiter:1.16.0")
    testImplementation("org.testcontainers:rabbitmq:1.16.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "16"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
