package net.nekocurit.loli_ac_server.plugin.command_server_info

import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase

object ModCommandServerInfo {

    @JvmStatic
    fun onInitialize() {
        ModCommandBase.registerCommand(CommandPlugins())
    }

}