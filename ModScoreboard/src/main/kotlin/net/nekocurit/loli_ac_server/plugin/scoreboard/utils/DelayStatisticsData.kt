package net.nekocurit.loli_ac_server.plugin.scoreboard.utils

class DelayStatisticsData {
    private var delays = mutableListOf<Long>()

    fun add() = delays.add(System.currentTimeMillis())
    fun get(millis: Long) = (System.currentTimeMillis() - millis).let { time ->
        delays.count { it >= time }
    }
    fun reset() = delays.clear()
    fun reset(millis: Long) = (System.currentTimeMillis() - millis).let { time ->
        delays.removeIf { it < time }
    }
}