package com.nmrc.equipotentialx.framework.ui.screens

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nmrc.components.AppBar
import com.nmrc.custom.DefaultCardExplained
import com.nmrc.equipotentialx.R
import com.nmrc.equipotentialx.framework.ui.activities.ARActivity
import com.nmrc.equipotentialx.util.Display.getWidth
import com.nmrc.helpers.MenuContext
import com.nmrc.theme.Gradient


@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun MainScreen(navController: NavController, activity: Activity) {

    var menuState by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {

            AppBar(
                modifier = Modifier
                    .padding(top = 8.dp),
                title = "EQUIPOTENTIALX",
                context = MenuContext.MORE,
                actionOnClick = { menuState = !menuState }
            )
        }
    ) {

        /** Performance problem */
        DropdownMenu(
            offset = DpOffset(getWidth(activity).dp, 50.dp),
            expanded = menuState,
            onDismissRequest = { menuState = false }
        ) {
            DropdownMenuItem(
                onClick = { navController.navigate(Screen.SettingsScreen.route) }
            ) {
                Icon(Icons.Rounded.Settings, "")
                Text(text = "Setting")
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    elevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier
                            .height(210.dp)
                            .background(
                                Brush.horizontalGradient(
                                    Gradient.Solar.getGradient(),
                                    startX = 0f
                                )
                            )
                    ) {


                    }
                }

                Divider(
                    modifier = Modifier
                        .height(64.dp),
                    color = MaterialTheme.colors.background
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Experiments",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Divider(
                    modifier = Modifier
                        .height(32.dp),
                    color = MaterialTheme.colors.background
                )

                LazyRow {

                    item {

                        DefaultCardExplained(
                            title = "Equipotential surfaces",
                            painter = painterResource(id = R.drawable.ic_atom),
                            description = "Observing the equipotential surfaces of an electric charge in augmented reality.",
                            onDetail = {}) {

                            activity.run {
                                val intent = Intent(this, ARActivity::class.java)
                                startActivity(intent)
                            }
                        }

                        Divider(
                            modifier = Modifier
                                .width(16.dp),
                            color = MaterialTheme.colors.background
                        )

                        DefaultCardExplained(
                            title = "Equipotential curves",
                            painter = painterResource(id = R.drawable.ic_equipotential_3d),
                            description = "Testing by entering arbitrary values of position and electric charge for two atoms.",
                            onDetail = {}) {

                            navController.navigate(Screen.DataScreen.route)
                        }
                    }
                }
            }
        }
    }
}
