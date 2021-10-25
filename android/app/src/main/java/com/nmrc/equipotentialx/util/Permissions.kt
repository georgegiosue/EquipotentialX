package com.nmrc.equipotentialx.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager

sealed class Permissions(
    val name: String,
    val code: Int
    ) {

    private val denied = PackageManager.PERMISSION_DENIED

    object Camera: Permissions(
        name = Manifest.permission.CAMERA,
        code = 5
    ) {
        operator fun invoke(
            activity: Activity,
            denied: (Boolean) -> Unit)
        {
            activity.request()

            denied(activity checkPermission name)
        }

        fun Activity.request() = request(name, this@Camera.code)
    }

    protected fun Activity.request(type: String, code: Int) = requestPermissions(
        arrayOf(type),
        code
    )

    protected infix fun Activity.checkPermission(type: String) = checkSelfPermission(type) == denied
}