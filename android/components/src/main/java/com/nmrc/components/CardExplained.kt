package com.nmrc.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun CardExplained(
    modifier: Modifier = Modifier,
    modifierPreview: Modifier = Modifier,
    contentPreview: @Composable BoxScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit,
    actionContent: @Composable BoxScope.() -> Unit = {},
    detailContent: @Composable BoxScope.() -> Unit = {},
    elevation: Dp = 0.dp,
    onClick: () -> Unit = {},
    borderStroke: BorderStroke? = null,
    shape: Shape = MaterialTheme.shapes.medium
) {

    var state by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .width(280.dp),
        elevation = elevation,
        border = borderStroke,
        shape = shape,
        onClick = onClick
    ) {

        Column {

            CardPreview(
                modifier = modifierPreview,
                content = contentPreview,
                onClick = { state = !state }
            )

            AnimatedVisibility(visible = state) {

                CardDetail(
                    content = content,
                    actionContent = actionContent,
                    detailContent = detailContent
                )
            }
        }

    }
}

