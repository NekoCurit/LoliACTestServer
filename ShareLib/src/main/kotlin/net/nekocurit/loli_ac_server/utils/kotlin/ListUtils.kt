package net.nekocurit.loli_ac_server.utils.kotlin

fun <T> List<T>.intersperse(separator: () -> T): List<T> {
    if (isEmpty()) return this
    return flatMapIndexed { index, element ->
        if (index == lastIndex) listOf(element)
        else listOf(element, separator.invoke())
    }
}