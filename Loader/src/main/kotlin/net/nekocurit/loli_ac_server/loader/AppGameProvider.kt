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
import java.util.stream.Collectors
import java.util.zip.ZipFile

class AppGameProvider : GameProvider {
    private var arguments: Arguments? = null
    private var appJar: Path? = null

    override fun getGameId() = "server"

    override fun getGameName() = "Minecraft"

    override fun getRawGameVersion() = "paper-1.8"

    override fun getNormalizedGameVersion() = "1.8"

    override fun getBuiltinMods() = mutableListOf(
        BuiltinMod(
            mutableListOf(appJar),
            BuiltinModMetadata.Builder(gameId, normalizedGameVersion)
                .setName(gameName)
                .addAuthor("NekoCurit", emptyMap<String, String>())
                .setContact(ContactInformationImpl(emptyMap<String, String>()))
                .setDescription("A simple Hello World app for Fabric Loader.")
                .build()
        )
    )

    /*
     * Provides the full class name of the app's entrypoint.
     */
    override fun getEntrypoint(): String {
        return CLIENT_ENTRYPOINT
    }

    /*
     * Provides the directory path where the app's resources (such as config) should
     * be located
     * This is where the `mods` folder will be located.
     */
    override fun getLaunchDirectory(): Path {
        if (arguments == null) {
            return Paths.get(".")
        }

        return Companion.getLaunchDirectory(arguments!!)
    }

    override fun requiresUrlClassLoader(): Boolean {
        return false
    }

    override fun getBuiltinTransforms(className: String) = setOf<GameProvider.BuiltinTransform>()

    override fun isEnabled(): Boolean {
        return true
    }

    /*
     * Parse the arguments, locate the game directory, and return true if the game
     * directory is valid.
     */
    override fun locateGame(launcher: FabricLauncher?, args: Array<String?>): Boolean {
        this.arguments = Arguments()
        this.arguments!!.parse(args)

        // Build a list of possible locations for the app JAR.
        val appLocations: MutableList<String?> = ArrayList<String?>()
        // Respect "fabric.gameJarPath" if it is set.
        if (System.getProperty(SystemProperties.GAME_JAR_PATH) != null) {
            appLocations.add(System.getProperty(SystemProperties.GAME_JAR_PATH))
        }
        // List out default locations.
        appLocations.add("./loli-server.jar")

        // Filter the list of possible locations based on whether the file exists.
        val existingAppLocations =
            appLocations.stream().map<Path?> { p: String? -> Paths.get(p).toAbsolutePath().normalize() }
                .filter { path: Path? -> Files.exists(path) }.toList()

        // Filter the list of possible locations based on whether they contain the required entrypoints
        val result = GameProviderHelper.findFirst(existingAppLocations, HashMap<Path?, ZipFile?>(), true, *ENTRYPOINTS)

        if (result == null || result.path == null) {
            // Tell the user we couldn't find the app JAR.
            val appLocationsString = appLocations.stream()
                .map<String?> { p: String? -> (String.format("* %s", Paths.get(p).toAbsolutePath().normalize())) }
                .collect(Collectors.joining("\n"))

            Log.error(
                LogCategory.GAME_PROVIDER,
                "Could not locate the application JAR! We looked in: \n" + appLocationsString
            )

            return false
        }

        this.appJar = result.path

        return true
    }

    /*
     * Add additional configuration to the FabricLauncher, but do not launch your
     * app.
     */
    override fun initialize(launcher: FabricLauncher) = TRANSFORMER.locateEntrypoints(launcher, listOf(appJar))

    override fun getEntrypointTransformer() = TRANSFORMER

    /*
     * Called after transformers were initialized and mods were detected and loaded
     * (but not initialized).
     */
    override fun unlockClassPath(launcher: FabricLauncher) {
        launcher.addToClassPath(appJar)
    }

    /*
     * Launch the app in this function. This MUST be done via reflection.
     */
    override fun launch(loader: ClassLoader) {
        try {
            val main = loader.loadClass(this.entrypoint)
            val method = main.getMethod("main", Array<String>::class.java)

            method.invoke(null, this.arguments!!.toArray() as Any)
        } catch (e: InvocationTargetException) {
            throw FormattedException("App has crashed!", e.cause)
        } catch (e: ReflectiveOperationException) {
            throw FormattedException("Failed to launch App", e)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getArguments(): Arguments? {
        return this.arguments
    }

    override fun getLaunchArguments(sanitize: Boolean): Array<String?> {
        if (arguments == null) return arrayOfNulls<String>(0)

        val ret = arguments!!.toArray()
        return ret
    }

    companion object {
        const val CLIENT_ENTRYPOINT: String = "org.bukkit.craftbukkit.Main"
        val ENTRYPOINTS: Array<String> = arrayOf<String>(CLIENT_ENTRYPOINT)

        const val PROPERTY_APP_DIRECTORY: String = "appDirectory"

        private val TRANSFORMER: GameTransformer = AppGameTransformer()

        private fun getLaunchDirectory(arguments: Arguments): Path {
            return Paths.get(arguments.getOrDefault(PROPERTY_APP_DIRECTORY, "."))
        }
    }
}
