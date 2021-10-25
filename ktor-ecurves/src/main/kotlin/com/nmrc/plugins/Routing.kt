package com.nmrc.plugins

import com.nmrc.routes.getAllCurves
import com.nmrc.routes.getCurve
import com.nmrc.routes.getLayerModel
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {

        get("/") {
            call.respond("I like pan con queso")
        }

        getAllCurves()

        getCurve()

        getLayerModel()

        static {
            resources("static")
        }
    }
}
