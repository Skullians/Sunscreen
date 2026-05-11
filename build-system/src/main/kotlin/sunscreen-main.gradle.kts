import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    `java-library`
}

val libs = the<LibrariesForLibs>()

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
    maven("https://repo.combimagnetron.net/releases")
    maven("https://repo.codemc.io/repository/maven-releases/")
    maven("https://repo.nexomc.com/releases")
}

dependencies {
    compileOnly(libs.bundles.adventure)
    compileOnly(libs.bundles.utils)

    implementation(libs.bundles.lamp.command)
    implementation(libs.bundles.minecraft)
    implementation(libs.passport)
}
