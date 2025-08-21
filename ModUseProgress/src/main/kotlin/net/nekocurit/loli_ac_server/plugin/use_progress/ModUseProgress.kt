package net.nekocurit.loli_ac_server.plugin.use_progress

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.minecraft.server.v1_8_R3.ItemStack
import net.nekocurit.loli_ac_server.utils.sendActionBar
import org.bukkit.ChatColor

object ModUseProgress {

    @JvmStatic
    fun sendActionbar(player: EntityPlayer, item: ItemStack, progress: Int) {
        player.sendActionBar(StringBuilder().apply {
            append("${ChatColor.LIGHT_PURPLE}使用物品中 ")
            append("${ChatColor.WHITE}| ")
            append("${ChatColor.LIGHT_PURPLE}物品类名: ${item.javaClass.simpleName} ")
            append("${ChatColor.WHITE}| ")
            append("${ChatColor.LIGHT_PURPLE}状态数据: $progress")
        }.toString());
    }

    @JvmStatic
    fun sendActionbarClear(player: EntityPlayer) {
        player.sendActionBar("")
    }

}