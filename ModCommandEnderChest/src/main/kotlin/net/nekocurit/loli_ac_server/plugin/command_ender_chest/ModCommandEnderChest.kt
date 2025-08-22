package net.nekocurit.loli_ac_server.plugin.command_ender_chest

import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase

object ModCommandEnderChest {

    @JvmStatic
    fun onInitialize() {
        ModCommandBase.registerCommand(CommandEnderChest())
    }

}