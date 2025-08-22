package net.nekocurit.loli_ac_server.plugin.command_spawn

import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase

object ModCommandSpawn {

    @JvmStatic
    fun onInitialize() {
        ModCommandBase.registerCommand(CommandSpawn())
    }

}