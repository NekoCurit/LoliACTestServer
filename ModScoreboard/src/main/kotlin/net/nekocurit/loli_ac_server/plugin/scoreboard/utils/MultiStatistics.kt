package net.nekocurit.loli_ac_server.plugin.scoreboard.utils

import net.minecraft.server.v1_8_R3.EntityPlayer
import java.util.WeakHashMap

class MultiStatistics {

    var data = WeakHashMap<EntityPlayer, StatisticsData>()
    /**
     * 获取玩家统计数据
     * 如果不存在会自动创建
     *
     * @param player 玩家实例
     */
    fun getPlayerDataOrCreate(player: EntityPlayer): StatisticsData = data[player] ?: let {
        StatisticsData().also {
            data[player] = it
        }
    }

}