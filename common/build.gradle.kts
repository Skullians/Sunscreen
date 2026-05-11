plugins {
    id("java")
    id("sunscreen-main")
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":api"))
    implementation(libs.lamp.common)
    implementation(libs.lamp.paper)
}
