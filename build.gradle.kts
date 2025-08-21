@file:Suppress("SpellCheckingInspection")

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

allprojects {
    apply {
        plugin("kotlin")
    }

    repositories {
        mavenCentral()
        maven("https://repo.spongepowered.org/repository/maven-public/")
        maven("https://maven.fabricmc.net/")
    }
}

subprojects {
    if (name in listOf("Loader")) return@subprojects

    dependencies {
        implementation(fileTree("${rootProject.projectDir}/libs") { include("*.jar") })
    }
}

group = "net.nekocurit.loli_ac_server"
version = "1.0-SNAPSHOT"


tasks.register("generate") {
    group = "build"
    description = "生成可执行版本"

    dependsOn(":Server:jar", ":Loader:shadowJar")

    doLast {
        val outputDir = layout.buildDirectory.dir("launch").get().asFile
            .apply {
                mkdirs()
            }

        project(":Server").tasks.named<Jar>("jar").get().archiveFile.get().asFile
            .apply {
                copyTo(outputDir.resolve(name), overwrite = true)
            }
        project(":Loader").tasks.named<ShadowJar>("shadowJar").get().archiveFile.get().asFile
            .apply {
                copyTo(outputDir.resolve(name), overwrite = true)
            }
        project(":Server").tasks.named<Copy>("copyDeps").get().destinationDir
            .apply {
                val depsDir = outputDir.resolve("deps")
                    .apply {
                        mkdirs()
                    }

                listFiles().forEach { file ->
                    file.copyTo(depsDir.resolve(file.name), overwrite = true)
                }
            }

        outputDir.resolve("start.bat").writeText("""
            @echo off
            java.exe -Dfabric.skipMcProvider=true -classpath "loli-loader.jar;loli-server;deps/*" net.fabricmc.loader.launch.knot.KnotClient
        """.trimIndent())
    }
}