package net.nekocurit.loli_ac_server.plugin.command_base.exception

abstract class CommandExecuteException: Throwable() {
    abstract fun getResponseMessage(): String
}