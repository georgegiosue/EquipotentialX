package com.nmrc.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    content: @Composable() (BoxScope.() -> Unit),
    actions: @Composable() (BoxScope.() -> Unit)? = null,
    onClick: () -> Unit
    ) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Icon(
                imageVector = icon,
                modifier = Modifier.padding(8.dp),
                contentDescription = ""
            )

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 48.dp),
                contentAlignment = Alignment.CenterStart,
                content = content
            )

            actions?.let {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart,
                    content = actions
                )
            }
        }
    }
}