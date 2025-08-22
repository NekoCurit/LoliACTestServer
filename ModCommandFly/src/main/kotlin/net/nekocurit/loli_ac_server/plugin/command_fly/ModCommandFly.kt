package net.nekocurit.loli_ac_server.plugin.command_fly

import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase

object ModCommandFly {

    @JvmStatic
    fun onInitialize() {
        ModCommandBase.registerCommand(CommandFly())
    }

}