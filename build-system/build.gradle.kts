plugins {
    kotlin("jvm") version "2.2.10"
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(libs.build.system.license)

    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(25)
}
