import net.minecrell.pluginyml.paper.PaperPluginDescription
import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("java")
    id("sunscreen-main")

    alias(libs.plugins.shadow)
    alias(libs.plugins.run.paper)
    alias(libs.plugins.pluginyml)
}

repositories {
    mavenCentral()
    maven("https://mvn.lumine.io/repository/maven-public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://jitpack.io")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.io/repository/maven-releases/")
    maven("https://repo.codemc.io/repository/maven-snapshots/")
    maven("https://repo.extendedclip.com/releases/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://repo.nexomc.com/releases")
}

dependencies {
    implementation(project(":common"))
    implementation(libs.packetevents)
    implementation(libs.lamp.paper)
    implementation(libs.lamp.brigadier)
    compileOnly(libs.paper)
    library(libs.bundles.utils)
}

configurations.all {
    resolutionStrategy {
        cacheChangingModulesFor(0, "seconds")
    }
}

tasks {
    runServer {
        minecraftVersion("1.21.11")
        jvmArgs("-Dcom.mojang.eula.agree=true", "-Dfile.encoding=UTF-8", "--add-modules=jdk.incubator.vector")
        downloadPlugins {
            //github("retrooper", "packetevents", "v2.11.1", "packetevents-spigot-2.11.1.jar")
            hangar("PlaceholderAPI", "2.11.6")
        }
        serverJar(kotlin.io.path.Path("paper-1.21.11-127.jar").toFile())
    }

    build {
        dependsOn( "shadowJar")
    }

    shadowJar {
        archiveBaseName.set("Sunscreen")
        archiveClassifier.set(null)
        archiveVersion.set(project.version.toString())
        configurations = listOf(project.configurations.runtimeClasspath.get())

        dependencies {
            exclude(dependency("com.google.guava:guava"))
            exclude(dependency("org.apache.commons:commons-lang3"))
            exclude(dependency("commons-io:commons-io"))
            exclude(dependency("com.github.ben-manes.caffeine:caffeine"))
            exclude(dependency("org.jetbrains.kotlin:kotlin-reflect"))
            exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8"))
        }

        relocate("com.github.retrooper.packetevents", "me.combimagnetron.shaded.packetevents.api")
        relocate("io.github.retrooper.packetevents", "me.combimagnetron.shaded.packetevents.impl")
    }

    withType(xyz.jpenilla.runtask.task.AbstractRun::class) {
        javaLauncher = project.javaToolchains.launcherFor {
            vendor = JvmVendorSpec.AZUL
            languageVersion = JavaLanguageVersion.of(25)
        }
        //jvmArgs("-XX:+AllowEnhancedClassRedefinition")
    }

    withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
    }
}

paper {
    name = "Sunscreen"
    main = "me.combimagnetron.sunscreen.SunscreenPlugin"
    apiVersion = "1.21"
    foliaSupported = true
    version = project.version.toString()
    authors = listOf("Combimagnetron")
    serverDependencies {
        register("ProtocolLib") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
        register("ProtocolSupport") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
        register("ViaVersion") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
        register("ViaBackwards") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
        register("ViaRewind") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
        register("Geyser-Spigot") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
    }
    description = "Create UIs like never seen before, all from within the game!"
    website = "https://sunscreen.combimagnetron.net"
}
