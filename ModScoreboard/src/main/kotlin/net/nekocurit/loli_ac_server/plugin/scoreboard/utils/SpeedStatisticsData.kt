package net.nekocurit.loli_ac_server.plugin.scoreboard.utils

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.minecraft.server.v1_8_R3.Position
import org.apache.commons.collections4.queue.CircularFifoQueue
import kotlin.math.abs

class SpeedStatisticsData {
    private var speeds = CircularFifoQueue<Double>(10)
    private var lastPos = Position(0.0, 0.0, 0.0)

    fun add(player: EntityPlayer) {
        speeds.add(abs(player.locX - lastPos.x) + abs(player.locY - lastPos.y) + abs(player.locZ - lastPos.z))
        lastPos = Position(player.locX, player.locY, player.locZ)
    }
    fun get() = speeds.sum()
    fun getFormated(f: Int) = String.format("%.${f}f", get())

    fun reset() = speeds.clear()
}