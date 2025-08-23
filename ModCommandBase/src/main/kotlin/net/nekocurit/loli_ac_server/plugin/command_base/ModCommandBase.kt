package net.nekocurit.loli_ac_server.plugin.command_base

import net.minecraft.server.v1_8_R3.BlockPosition
import net.minecraft.server.v1_8_R3.ChatComponentText
import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.plugin.command_base.exception.CommandExecuteException
import net.nekocurit.loli_ac_server.plugin.command_base.exception.CommandInvalidArgumentException
import net.nekocurit.loli_ac_server.utils.sendActionBar
import org.bukkit.ChatColor
import org.bukkit.event.player.PlayerChatTabCompleteEvent
import java.util.WeakHashMap

object ModCommandBase {

    val prefix = "${ChatColor.LIGHT_PURPLE}Loli ${ChatColor.GRAY}>> ${ChatColor.WHITE}"

    val commands = hashMapOf<String, Command>()

    @JvmStatic
    fun registerCommand(command: Command) {
        command.root.forEach { root ->
            commands[root.lowercase()] = command
        }
    }

    @JvmStatic
    fun onCommand(player: EntityPlayer, command: String) = runCatching {
        val args = command.removePrefix("/").split(" ").toTypedArray()
        return@runCatching commands[args.getOrNull(0)?.lowercase()]?.execute(player, args.drop(1))?.let { true } ?: false
    }
        .onFailure { e ->
            when (e) {
                is CommandExecuteException -> player.sendCommandResponse(e.getResponseMessage())
                else -> player.sendCommandResponse("执行命令时发生错误: $e")
            }
        }
        .getOrDefault(true)

    @JvmStatic
    fun onTabComplete(player: EntityPlayer, command: String, trigger: BlockPosition?): List<String>? {
        val args = command.removePrefix("/").split(" ").toTypedArray()
        return commands[args.getOrNull(0)?.lowercase()]?.complete(player, args.drop(1), trigger)
    }

    @JvmStatic
    fun EntityPlayer.sendCommandResponse(message: String) {
        this.sendMessage(ChatComponentText("$prefix$message"))
    }

}