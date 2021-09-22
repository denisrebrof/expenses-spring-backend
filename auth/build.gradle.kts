plugins {
    id("java")
    kotlin("jvm")
    kotlin("plugin.spring")
}

group = "com.upreality"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    //JWT
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.2")

    //Security
    implementation("org.springframework.boot:spring-boot-starter-security:2.5.4")
    implementation("org.springframework.security.oauth:spring-security-oauth2:2.5.1.RELEASE")
    implementation("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")

    //Http endpoints
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.4")

    //Reactor
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2-native-mt")

    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.5.21")

    //Lombok
    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.5.4")

    //Modules
    implementation(projects.users)
}