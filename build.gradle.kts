import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.springframework.boot") version "2.2.0.RELEASE"
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.jpa") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"
}
group = "me.00047122"
version = "1.0-SNAPSHOT"
repositories {
    mavenCentral()
}
dependencies {
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-security:2.4.1")
    implementation("io.jsonwebtoken:jjwt:0.9.0")
    implementation("io.jsonwebtoken:jjwt-api:0.10.6")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}