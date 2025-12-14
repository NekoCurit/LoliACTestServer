package net.nekocurit.loli_ac_server.loader

import net.fabricmc.loader.impl.FormattedException
import net.fabricmc.loader.impl.game.GameProvider
import net.fabricmc.loader.impl.game.GameProvider.BuiltinMod
import net.fabricmc.loader.impl.game.GameProviderHelper
import net.fabricmc.loader.impl.game.patch.GameTransformer
import net.fabricmc.loader.impl.launch.FabricLauncher
import net.fabricmc.loader.impl.metadata.BuiltinModMetadata
import net.fabricmc.loader.impl.metadata.ContactInformationImpl
import net.fabricmc.loader.impl.util.Arguments
import net.fabricmc.loader.impl.util.SystemProperties
import net.fabricmc.loader.impl.util.log.Log
import net.fabricmc.loader.impl.util.log.LogCategory
import java.lang.reflect.InvocationTargetException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.zip.ZipFile

class AppGameProvider : GameProvider {

    companion object {
        @Suppress("SpellCheckingInspection")
        const val CLIENT_ENTRYPOINT: String = "org.bukkit.craftbukkit.Main"

        private val TRANSFORMER: GameTransformer = AppGameTransformer()
    }

    private var arguments = Arguments()
    private var appJar: Path? = null

    override fun getGameId() = "server"

    override fun getGameName() = "Minecraft"

    override fun getRawGameVersion() = "paper-1.8"

    override fun getNormalizedGameVersion() = "1.8"

    @Suppress("SpellCheckingInspection")
    override fun getBuiltinMods() = mutableListOf(
        BuiltinMod(
            mutableListOf(appJar),
            BuiltinModMetadata.Builder(gameId, normalizedGameVersion)
                .setName(gameName)
                .addAuthor("NekoCurit", emptyMap<String, String>())
                .setContact(ContactInformationImpl(emptyMap<String, String>()))
                .setDescription("一个 Minecraft 反作弊测试服务器.")
                .build()
        )
    )

    override fun getEntrypoint() = CLIENT_ENTRYPOINT

    override fun getLaunchDirectory(): Path = Paths.get(arguments.getOrDefault("appDirectory", "."))

    override fun requiresUrlClassLoader() = false

    override fun getBuiltinTransforms(className: String) = setOf<GameProvider.BuiltinTransform>()

    override fun isEnabled() = true

    override fun locateGame(launcher: FabricLauncher, args: Array<String>): Boolean {
        arguments.parse(args)

        val appLocations = mutableListOf<String>()
            .apply {
                System.getProperty(SystemProperties.GAME_JAR_PATH)
                    ?.also { property ->
                        add(property)
                    }

                add("./loli-server.jar")
            }

        val existingAppLocations = appLocations
            .mapNotNull { it.let { Paths.get(it).toAbsolutePath().normalize() } }
            .filter { Files.exists(it) }


        this.appJar = GameProviderHelper.findFirst(existingAppLocations, HashMap<Path, ZipFile>(), true, *arrayOf(CLIENT_ENTRYPOINT))
            ?.path
            ?: run {
                Log.error(LogCategory.GAME_PROVIDER, "Could not locate the application JAR! We looked in: \n${appLocations.joinToString("\n") { it.let { p -> "* ${Paths.get(p).toAbsolutePath().normalize()}" } }}")
                return false
            }

        return true
    }

    override fun initialize(launcher: FabricLauncher) = TRANSFORMER.locateEntrypoints(launcher, listOf(appJar))

    override fun getEntrypointTransformer() = TRANSFORMER

    override fun unlockClassPath(launcher: FabricLauncher) = launcher.addToClassPath(appJar)

    override fun launch(loader: ClassLoader) {
        runCatching {
            loader.loadClass(entrypoint)
                .getMethod("main", Array<String>::class.java)
                .invoke(null, arguments.toArray())
        }
            .onFailure { e ->
                when(e) {
                    is InvocationTargetException -> throw FormattedException("App has crashed!", e.cause)
                    is ReflectiveOperationException -> throw FormattedException("Failed to launch App", e)
                    else -> e.printStackTrace()
                }
            }
    }

    override fun getArguments() = arguments

    override fun getLaunchArguments(sanitize: Boolean) = arguments.toArray()!!
}
