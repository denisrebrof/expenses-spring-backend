plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

group = "com.upreality"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    //Security
    implementation("org.springframework.boot:spring-boot-starter-security:2.5.4")
    implementation("org.springframework.security.oauth:spring-security-oauth2:2.5.1.RELEASE")
    implementation("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:2.5.4")

    //Mongo DB
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.5.4")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:2.5.4")
    //Reactor
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2-native-mt")

    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //Http endpoints
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.4")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.5.4")

    //Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    //Modules
    implementation(project(":users"))
    implementation(project(":auth"))
}