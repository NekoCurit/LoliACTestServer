package net.nekocurit.loli_ac_server.plugin.scoreboard.utils

data class StatisticsData(
    var ppsIn: DelayStatisticsData = DelayStatisticsData(),
    var ppsOut: DelayStatisticsData = DelayStatisticsData(),
    var cps: DelayStatisticsData = DelayStatisticsData(),
)