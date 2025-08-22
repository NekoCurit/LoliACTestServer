package net.nekocurit.loli_ac_server.plugin.command_suicide

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.plugin.command_base.Command

class CommandSuicide: Command(listOf("suicide", "kill"), "", "自我了结") {

    override fun execute(player: EntityPlayer, args: List<String>) {
        player.bukkitEntity.health = 0.0
    }

}