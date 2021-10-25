package com.nmrc.equipotentialx.util

import android.app.Activity
import android.graphics.Insets
import android.os.Build
import android.view.WindowInsets
import androidx.annotation.RequiresApi

object Display {

    /** Performance problem */

    @RequiresApi(Build.VERSION_CODES.R)
    fun getWidth(activity: Activity): Int {
        val windowMetrics = activity.windowManager.currentWindowMetrics
        val insets: Insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        return windowMetrics.bounds.width() - insets.left - insets.right
    }
}