package com.nmrc.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nmrc.helpers.MenuContext

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String,
    context: MenuContext,
    elevation: Dp = 0.dp,
    navigationOnClick: () -> Unit = {},
    actionOnClick: () -> Unit = {}
) {

    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        elevation = elevation
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {

            if (context != MenuContext.MORE) {
                IconButton(
                    onClick = navigationOnClick
                ) {
                    Icon(
                        context.navIcon!!,
                        contentDescription = null
                    )
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(start = 16.dp),
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                maxLines = 1
            )

            if (context != MenuContext.BACK && context != MenuContext.MENU) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    onClick = actionOnClick
                ) {
                    Icon(
                        context.actionIcon!!,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
