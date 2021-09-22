rootProject.name = "expenses-spring-backend"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("users")
include("auth")
include("app")