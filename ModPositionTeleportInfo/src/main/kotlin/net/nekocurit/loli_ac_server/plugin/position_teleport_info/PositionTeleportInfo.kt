package net.nekocurit.loli_ac_server.plugin.position_teleport_info

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.utils.sendActionBar
import org.bukkit.ChatColor
import java.util.WeakHashMap

object PositionTeleportInfo {

    @JvmField
    val lastPositions = WeakHashMap<EntityPlayer, PositionData>()

    @JvmStatic
    fun EntityPlayer.tick() {
        lastPositions[this]
            ?.also { data ->
                val currentTime = System.currentTimeMillis()

                when (val time = currentTime - data.time) {
                    in 0..5500 -> sendActionBar(
                        arrayOf(
                            "${ChatColor.LIGHT_PURPLE}接收到 Server Position 包",
                            "${ChatColor.WHITE}|",
                            "${ChatColor.LIGHT_PURPLE}位置: ${data.x}, ${data.y}, ${data.z}",
                            "${ChatColor.WHITE}|",
                            "${ChatColor.LIGHT_PURPLE}时间差: ${String.format("%.2f", time / 1000.0)}s"
                        ).joinToString(" ")
                    )

                    in 5501..6000 -> sendActionBar("")
                    else -> lastPositions.remove(this)
                }
            }
    }

}