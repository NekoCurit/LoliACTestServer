rootProject.name = "LoliACTestServer"

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()

        gradlePluginPortal()
    }

    val kotlinVersion: String by settings

    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
    }
}

include(
    ":Loader", ":ShareLib",
    ":ModPositionTeleportInfo", ":ModScoreboard", ":ModZombieProtect", ":ModUseProgress", ":ModClientBrand",
    ":ModCommandBase", ":ModCommandTrash", ":ModCommandItems", ":ModCommandSpawn", ":ModCommandSuicide", ":ModCommandEnderChest", ":ModCommandFly", ":ModCommandServerInfo", ":ModCommandVelocity"
)