plugins {
    kotlin("jvm")
}

dependencies {
    compileOnly(fileTree("${rootProject.projectDir}/libs") { include("*.jar") })
}

tasks {
    val copyDeps = register<Copy>("copyDeps") {
        group = "build"
        from(configurations.runtimeClasspath)
        into("build/libs/deps/")
    }

    jar {
        archiveBaseName.set("loli-share-lib")
        dependsOn(copyDeps)
    }
}