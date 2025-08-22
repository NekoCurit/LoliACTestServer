package net.nekocurit.loli_ac_server.plugin.command_ender_chest

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.plugin.command_base.Command

class CommandEnderChest: Command(listOf("ec", "enderChest"), "", "私人末影箱") {

    override fun execute(player: EntityPlayer, args: List<String>) {
        player.bukkitEntity.openInventory(player.bukkitEntity.enderChest)
    }

}