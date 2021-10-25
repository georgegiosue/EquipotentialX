package com.nmrc.routes

import com.nmrc.core.getCurve
import com.nmrc.core.getLayerWrapper
import com.nmrc.core.getParameters
import com.nmrc.core.readAllPath
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getAllCurves() {
    get("/environments/curves/all") {

        call.respond(
            HttpStatusCode.OK,
            readAllPath().map { model ->
                getCurve(model)
            }
        )

    }
}

fun Route.getCurve() {
    get("/environments/curves/get") {
        call.respond(
            HttpStatusCode.OK,
            getCurve(getParameters())
        )
    }
}

fun Route.getLayerModel() {
    get("/environments/layer-model") {
        call.respond(
            HttpStatusCode.OK,
            getLayerWrapper()
        )
    }
}

