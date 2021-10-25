package com.nmrc.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardDetail(
    content: @Composable ColumnScope.() -> Unit,
    actionContent: @Composable BoxScope.() -> Unit = {},
    detailContent: @Composable BoxScope.() -> Unit = {}
) {

    Column(modifier = Modifier.padding(16.dp)) {

        content()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(4.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                content = detailContent
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                content = actionContent
            )
        }
    }
}