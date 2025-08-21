plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    mainClass.set("net.fabricmc.loader.launch.knot.KnotClient")
}

dependencies {
    implementation("org.spongepowered:mixin:0.8.5")

    implementation("com.google.code.gson:gson:2.13.1")
    implementation("com.google.guava:guava:33.4.8-jre")

    implementation("net.fabricmc:fabric-loader:0.13.3")
    implementation("net.fabricmc:tiny-mappings-parser:0.2.2.14")
    implementation("net.fabricmc:access-widener:2.1.0")

    implementation("org.ow2.asm:asm:9.2")
    implementation("org.ow2.asm:asm-analysis:9.2")
    implementation("org.ow2.asm:asm-commons:9.2")
    implementation("org.ow2.asm:asm-tree:9.2")
    implementation("org.ow2.asm:asm-util:9.2")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        archiveBaseName.set("loli-loader")

        manifest {
            attributes("Main-Class" to application.mainClass.get())
        }

        relocate("com.google", "net.nekocurit.lib.com.google")
    }
}