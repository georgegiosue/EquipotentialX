package com.nmrc.equipotentialx.framework.ui.screens

sealed class Screen(val route: String) {

    object MainScreen : Screen("main_scree")

    object DataScreen : Screen("data_screen")

    object SettingsScreen : Screen("settings_screen")

    object InfoScreen : Screen("info_screen")
}
