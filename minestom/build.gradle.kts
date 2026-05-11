plugins {
    application
    id("sunscreen-main")
    id("sunscreen-publish")
}

dependencies {
    implementation(project(":common"))

    implementation(libs.bundles.utils)
    implementation(libs.logback.classic)
    implementation(libs.minestom)
}

configurations.all {
    resolutionStrategy {
        cacheChangingModulesFor(0, "seconds")
    }
}

application {
    mainClass.set("me.combimagnetron.sunscreen.MinestomTest")
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
