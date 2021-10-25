package com.nmrc.equipotentialx.framework.ui.navigation

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nmrc.equipotentialx.di.BaseApplication
import com.nmrc.equipotentialx.framework.ui.screens.*

@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(activity: Activity, application: BaseApplication) {

    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(
            route = Screen.MainScreen.route)
        {

            MainScreen(
                navController = navController,
                activity = activity
            )
        }

        composable(
            route = Screen.DataScreen.route
        ) {
            
            DataScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.SettingsScreen.route
        ) {

            SettingsScreen(
                navController = navController,
                application = application
            )
        }

        composable(
            route = Screen.InfoScreen.route
        ) {

            InfoScreen(
                navController = navController
            )
        }
    }
}