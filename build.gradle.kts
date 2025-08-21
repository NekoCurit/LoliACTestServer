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
    if (name in listOf("Loader", "ShareLib")) return@subprojects

    dependencies {
        implementation(fileTree("${rootProject.projectDir}/libs") { include("*.jar") })
        implementation("org.spongepowered:mixin:0.8.5")
        implementation(project(":ShareLib"))
    }
}

dependencies {
    implementation("org.spongepowered:mixin:0.8.5")
}

group = "net.nekocurit.loli_ac_server"
version = "1.0-SNAPSHOT"

tasks.register<Copy>("copyDeps") {
    group = "build"
    from(configurations.runtimeClasspath)
    into("build/launch/deps/")
}


tasks.register("generate") {
    group = "build"
    description = "生成可执行版本"

    dependsOn(":Loader:shadowJar", ":ShareLib:jar")

    doLast {
        val outputDir = layout.buildDirectory.dir("launch").get().asFile
            .apply {
                mkdirs()
            }

        project(":Loader").tasks.named<ShadowJar>("shadowJar").get().archiveFile.get().asFile
            .apply {
                copyTo(outputDir.resolve(name), overwrite = true)
            }


        rootDir.resolve("libs/patched_1.8.8.jar").copyTo(outputDir.resolve("loli-server.jar"), overwrite = true)

        val depsDir = outputDir.resolve("deps")
            .apply {
                mkdirs()
            }

        project(":ShareLib").tasks.named<Jar>("jar").get().archiveFile.get().asFile
            .apply {
                copyTo(depsDir.resolve(name), overwrite = true)
            }

        project(":ShareLib").tasks.named<Copy>("copyDeps").get().destinationDir
            .apply {


                listFiles().forEach { file ->
                    file.copyTo(depsDir.resolve(file.name), overwrite = true)
                }
            }

        outputDir.resolve("start.bat").writeText("""
            @echo off
            java.exe -Dfabric.skipMcProvider=true -classpath "loli-loader.jar;deps/*" net.fabricmc.loader.launch.knot.KnotClient
        """.trimIndent())
    }
}