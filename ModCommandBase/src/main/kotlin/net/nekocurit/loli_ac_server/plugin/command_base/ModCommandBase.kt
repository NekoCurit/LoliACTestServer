package net.nekocurit.loli_ac_server.plugin.command_base

import net.minecraft.server.v1_8_R3.BlockPosition
import net.minecraft.server.v1_8_R3.ChatComponentText
import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.utils.sendActionBar
import org.bukkit.ChatColor
import org.bukkit.event.player.PlayerChatTabCompleteEvent
import java.util.WeakHashMap

object ModCommandBase {

    val commands = hashMapOf<String, Command>()

    @JvmStatic
    fun registerCommand(command: Command) {
        command.root.forEach { root ->
            commands[root] = command
        }
    }

    @JvmStatic
    fun onCommand(player: EntityPlayer, command: String): Boolean {
        val args = command.removePrefix("/").split(" ").toTypedArray()
        return commands[args.getOrNull(0)]?.execute(player, args.drop(1))?.let { true } ?: false
    }

    @JvmStatic
    fun onTabComplete(player: EntityPlayer, command: String, trigger: BlockPosition?): List<String>? {
        val args = command.removePrefix("/").split(" ").toTypedArray()
        return commands[args.getOrNull(0)]?.complete(player, args.drop(1), trigger)
    }

}