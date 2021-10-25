package com.nmrc

import com.nmrc.plugins.configureMonitoring
import com.nmrc.plugins.configureRouting
import com.nmrc.plugins.configureSerialization
import io.ktor.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureRouting()
    configureSerialization()
    configureMonitoring()
}
