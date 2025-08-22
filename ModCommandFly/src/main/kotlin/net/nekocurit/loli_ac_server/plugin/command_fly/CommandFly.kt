package net.nekocurit.loli_ac_server.plugin.command_fly

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.plugin.command_base.Command
import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase.sendCommandResponse

class CommandFly: Command(listOf("fly"), "", "开启或关闭飞行") {

    override fun execute(player: EntityPlayer, args: List<String>) {
        val bukkitPlayer = player.bukkitEntity
        
        bukkitPlayer.allowFlight = !bukkitPlayer.allowFlight
        player.sendCommandResponse("已${if (bukkitPlayer.allowFlight) "开启" else "关闭"}飞行")
    }

}