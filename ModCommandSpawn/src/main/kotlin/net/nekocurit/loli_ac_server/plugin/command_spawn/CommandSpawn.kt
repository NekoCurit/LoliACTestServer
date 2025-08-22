package net.nekocurit.loli_ac_server.plugin.command_spawn

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.plugin.command_base.Command

class CommandSpawn: Command(listOf("spawn"), "", "回出生点") {

    override fun execute(player: EntityPlayer, args: List<String>) {
        player.bukkitEntity.teleport(player.bukkitEntity.world.spawnLocation)
    }

}