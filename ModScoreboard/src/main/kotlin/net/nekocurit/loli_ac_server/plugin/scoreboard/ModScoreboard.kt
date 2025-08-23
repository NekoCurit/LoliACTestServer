package net.nekocurit.loli_ac_server.plugin.scoreboard

import net.minecraft.server.v1_8_R3.EntityPlayer
import net.nekocurit.loli_ac_server.plugin.scoreboard.utils.MultiStatistics
import org.bukkit.ChatColor
import java.util.*


object ModScoreboard {

    @JvmField
    val multiStatisticsManager = MultiStatistics()

    @JvmField
    val scoreboards = WeakHashMap<EntityPlayer, PlayerScoreboard>()

    @JvmStatic
    fun EntityPlayer.tick() {
        this.airTicks
        val statistics = multiStatisticsManager.getPlayerDataOrCreate(this)

        scoreboards
            .getOrPut(this) { PlayerScoreboard(this) }
                .update("", mutableListOf<String>().apply {
                    this.
                    add("${ChatColor.WHITE}${ChatColor.WHITE}")
                    add("${ChatColor.WHITE}Health: ${ChatColor.GREEN}${String.format("%.2f", health)}/${maxHealth}")
                    add("${ChatColor.WHITE}Motion: ${ChatColor.GREEN}${String.format("%.2f", motX)}, ${String.format("%.2f", motY)}, ${String.format("%.2f", motZ)}")
                    add("${ChatColor.WHITE}HurtTime: ${ChatColor.GREEN}$noDamageTicks/${maxNoDamageTicks}")
                    add("${ChatColor.WHITE}CPS: ${ChatColor.GREEN}${statistics.cps.get(1000)}")
                    add("${ChatColor.WHITE}BPS: ${ChatColor.GREEN}${statistics.bps.getFormated(2)}")
                    add("${ChatColor.WHITE}PPS ${ChatColor.GRAY}I${ChatColor.WHITE}/${ChatColor.GRAY}O${ChatColor.WHITE}: ${ChatColor.GREEN}${statistics.ppsIn.get(1000)}/${statistics.ppsOut.get(1000)}")
                    add("${ChatColor.WHITE}")
                    add("${ChatColor.WHITE}在地面: ${yesOrNo(onGround)}")
                    add("${ChatColor.WHITE}潜行中: ${yesOrNo(isSneaking)}")
                    add("${ChatColor.WHITE}疾跑中: ${yesOrNo(isSprinting)}")
                    add("${ChatColor.WHITE}格挡中: ${yesOrNo(isBlocking)}")
                    add("${ChatColor.YELLOW}@NekoCurit")
                })
    }

    val YES = "${ChatColor.GREEN}✔"
    val NO = "${ChatColor.RED}✖"

    fun yesOrNo(state: Boolean) = when (state) {
        true -> YES
        false -> NO
    }

}