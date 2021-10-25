package com.nmrc.equipotentialx.framework.ui.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import com.nmrc.equipotentialx.di.BaseApplication
import com.nmrc.equipotentialx.framework.ui.navigation.Navigation
import com.nmrc.theme.ComponentsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var application: BaseApplication

    @RequiresApi(Build.VERSION_CODES.R)
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            ComponentsAppTheme {

                Navigation(this, application)
            }
        }
    }
}
