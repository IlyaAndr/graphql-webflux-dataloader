package com.yg.gqlwfdl

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// Miscellaneous logging-related methods

fun getLogMessage(message: String, includeThreadName: Boolean = true): String {
    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss.SSS"))
    val threadInfo = if (includeThreadName) " [${Thread.currentThread().name}]" else ""
    return "$timestamp$threadInfo :: $message"
}

fun logMessage(message: String, includeThreadName: Boolean = true) {
    println(getLogMessage(message, includeThreadName))
}