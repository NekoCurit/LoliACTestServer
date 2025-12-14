plugins {
    kotlin("jvm")
    application
    id("com.gradleup.shadow")
}

application {
    mainClass.set("net.fabricmc.loader.launch.knot.KnotClient")
}

dependencies {
    implementation("net.fabricmc:fabric-loader:0.17.3")
    implementation("net.fabricmc:tiny-mappings-parser:0.2.2.14")
    implementation("net.fabricmc:access-widener:2.1.0")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        archiveBaseName.set("loli-loader")

        manifest {
            attributes("Main-Class" to application.mainClass.get())
        }

        mergeServiceFiles()

        relocate("com.google", "net.nekocurit.lib.com.google")
    }
}