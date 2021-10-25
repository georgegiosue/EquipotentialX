package com.nmrc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Curves(
    val q1: ECharge,
    val q2: ECharge,
    val model3D: String
)
