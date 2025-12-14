rootProject.name = "LoliACTestServer"

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()

        gradlePluginPortal()
    }

    val kotlinVersion: String by settings
    val shadowVersion: String by settings

    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("com.gradleup.shadow") version shadowVersion
    }
}

include(
    ":Loader", ":ShareLib",
    ":ModPositionTeleportInfo", ":ModScoreboard", ":ModUseProgress", ":ModClientBrand",
    ":ModZombieProtect", ":ModGrassBlockKeeper",
    ":ModCommandBase", ":ModCommandTrash", ":ModCommandItems", ":ModCommandSpawn", ":ModCommandSuicide", ":ModCommandEnderChest", ":ModCommandFly", ":ModCommandServerInfo", ":ModCommandVelocity"
)