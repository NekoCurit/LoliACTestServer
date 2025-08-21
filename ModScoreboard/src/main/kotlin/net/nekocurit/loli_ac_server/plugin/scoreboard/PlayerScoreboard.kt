package net.nekocurit.loli_ac_server.plugin.scoreboard

import net.minecraft.server.v1_8_R3.ChatComponentText
import net.minecraft.server.v1_8_R3.EntityPlayer
import net.minecraft.server.v1_8_R3.IScoreboardCriteria
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore
import net.minecraft.server.v1_8_R3.Scoreboard
import net.minecraft.server.v1_8_R3.ScoreboardObjective
import net.minecraft.server.v1_8_R3.ScoreboardScore

class PlayerScoreboard(val player: EntityPlayer) {

    val scoreboard = Scoreboard()
    val objective: ScoreboardObjective = scoreboard.registerObjective("loli-test-server", IScoreboardCriteria.b)

    val remoteLines = mutableMapOf<Int, String>()

    init {
        player.playerConnection.sendPacket(PacketPlayOutScoreboardObjective(objective, 0))
        player.playerConnection.sendPacket(PacketPlayOutScoreboardDisplayObjective(1, objective))
    }

    fun update(title: String, lines: List<String>) {
        objective.displayName = title

        lines.forEachIndexed { i, line ->
            if (line != remoteLines[i]) {
                // 删除旧的数据
                remoteLines[i]?.also { remote ->
                    player.playerConnection.sendPacket(PacketPlayOutScoreboardScore(remote, objective))
                }
                // 下发新的数据
                val score = ScoreboardScore(scoreboard, objective, line)
                score.score = lines.size - i
                player.playerConnection.sendPacket(PacketPlayOutScoreboardScore(score))

                remoteLines[i] = line
            }
        }
    }

}