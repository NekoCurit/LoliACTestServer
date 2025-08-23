package net.nekocurit.loli_ac_server.plugin.client_brand

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.plugin.command_base.ModCommandBase.sendCommandResponse
import org.bukkit.ChatColor

object ModClientBrand {

    @JvmStatic
    fun sendClientBrand(player: EntityPlayer, brand: String) {
        player.sendCommandResponse("您的客户端标识为: ${ChatColor.LIGHT_PURPLE}$brand")
    }

}