package net.nekocurit.loli_ac_server.plugin.command_suicide

import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase

object ModCommandSuicide {

    @JvmStatic
    fun onInitialize() {
        ModCommandBase.registerCommand(CommandSuicide())
    }

}