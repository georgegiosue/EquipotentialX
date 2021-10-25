package com.nmrc.equipotentialx.framework.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nmrc.components.SettingsItem
import com.nmrc.equipotentialx.di.BaseApplication

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(navController: NavController, application: BaseApplication) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {


            SettingsItem(
                icon = Icons.Rounded.DarkMode,
                content = {
                    Text(
                        text = "Dark Mode"
                    )
                },
                actions = {
                    var checkedState by remember { mutableStateOf(true) }

                    Switch(
                        checked = checkedState,
                        onCheckedChange = { checked ->
                            checkedState = checked

                            Toast.makeText(
                                application.applicationContext,
                                "EN PRUEBAS",
                                Toast.LENGTH_SHORT
                            ).show()

                            //application.toggleLightTheme()
                        }
                    )
                },
                onClick = {}
            )

            Divider(
                modifier = Modifier.height(16.dp),
                color = Color.Transparent
            )

            SettingsItem(
                icon = Icons.Rounded.Info,
                content = {
                    Text(
                        text = "Application Info"
                    )
                },
                onClick = {
                    navController.navigate(Screen.InfoScreen.route)
                }
            )
        }
    }
}

