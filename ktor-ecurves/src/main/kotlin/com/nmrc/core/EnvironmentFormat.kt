package com.nmrc.core

import com.nmrc.data.model.Curves
import com.nmrc.data.model.ECharge
import com.nmrc.data.model.LayerWrapper
import io.ktor.application.*
import io.ktor.util.pipeline.*

private const val BASE_URL = "http://192.168.0.198:8100"
private const val EXTENSION = "glb"

fun PipelineContext<*, ApplicationCall>.getParameters() =
    run {
        call.request.queryParameters.entries().map { data ->
            data.value[0]
        }.map { it.toInt() }
    }

/**
 * Coordinates and charges of the study particles
 *
 * q1 : Charge 1 [ v: value, dx: x-axis position ]
 * q2 : Charge 2 [ v: value, dx: x-axis position ]
 * [] : value
 *
 * ...?q1_v=[]&q1_dx=[]&q2_v=[]&q2_dx=[]
 *
 * */

fun getCurve(args: List<Int>) =
    Curves(
        q1 = ECharge(args[0], args[1]),
        q2 = ECharge(args[2], args[3]),
        model3D = "$BASE_URL/curves/$${args[0]}$${args[1]}$${args[2]}$${args[3]}.$EXTENSION"
    )

fun getLayerWrapper() = LayerWrapper(
    "$BASE_URL/layer/layers_model.${EXTENSION}"
)