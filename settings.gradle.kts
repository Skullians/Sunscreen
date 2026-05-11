pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencyResolutionManagement {
    includeBuild("build-system")
    repositories.gradlePluginPortal()
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "Sunscreen"
include(
    "api",
    "spigot",
    "minestom",
    "common"
)
