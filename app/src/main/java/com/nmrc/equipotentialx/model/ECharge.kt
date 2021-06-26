package com.nmrc.equipotentialx.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ECharge(val value: Int, val dx: Int) : Parcelable {

    override fun toString(): String {
        return "$value nC : ${dx}i, 0j, 0k "
    }
}