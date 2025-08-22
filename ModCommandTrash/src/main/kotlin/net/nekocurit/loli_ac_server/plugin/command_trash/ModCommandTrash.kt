package net.nekocurit.loli_ac_server.plugin.command_trash

import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase
import org.bukkit.ChatColor

object ModCommandTrash {

    val CONTAINER_TITLE = "${ChatColor.BLUE}垃圾桶 ${ChatColor.RED}(关闭后物品将无法找回)"

    @JvmStatic
    fun onInitialize() {
        ModCommandBase.registerCommand(CommandTrash())
    }

}