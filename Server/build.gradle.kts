plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.apache.logging.log4j:log4j-core:2.25.1")
    implementation("org.apache.logging.log4j:log4j-api:2.25.1")
}

tasks {
    val copyDeps = register<Copy>("copyDeps") {
        group = "build"
        from(configurations.runtimeClasspath)
        into("build/libs/deps/")
    }

    jar {
        archiveBaseName.set("loli-server")
        dependsOn(copyDeps)
    }
}