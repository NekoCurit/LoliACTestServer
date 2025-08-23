package net.nekocurit.loli_ac_server.plugin.position_teleport_info

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.utils.sendActionBar
import org.bukkit.ChatColor
import java.util.WeakHashMap

object ModPositionTeleportInfo {

    @JvmField
    val lastPositions = WeakHashMap<EntityPlayer, PositionData>()

    @JvmStatic
    fun EntityPlayer.tick() {
        lastPositions[this]
            ?.also { data ->
                val currentTime = System.currentTimeMillis()

                when (val time = currentTime - data.time) {
                    in 0..5200 -> sendActionBar(StringBuilder().apply {
                        append("${ChatColor.LIGHT_PURPLE}接收到 Server Position 包 ")
                        append("${ChatColor.WHITE}| " )
                        append("${ChatColor.LIGHT_PURPLE}位置: ${String.format("%.2f", data.x)}, ${String.format("%.2f", data.y)}, ${String.format("%.2f", data.z)} ")
                        append("${ChatColor.WHITE}| " )
                        append("${ChatColor.LIGHT_PURPLE}时间差: ${String.format("%.2f", (time / 1000.0).coerceAtMost(5.0))}s")
                    }.toString())
                    else -> {
                        sendActionBar("")
                        lastPositions.remove(this)
                    }
                }
            }
    }

}