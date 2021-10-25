package com.nmrc.custom

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ViewInAr
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nmrc.components.ActionIconButtom
import com.nmrc.components.CardExplained
import com.nmrc.components.DefaultPreviewCard
import com.nmrc.theme.PurpleMaterial
import com.nmrc.theme.YellowMaterial

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun DefaultCardExplained(
    modifier: Modifier = Modifier,
    title: String,
    painter: Painter,
    description: String,
    onDetail: () -> Unit,
    onAction: () -> Unit
) {

    CardExplained(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)),
        contentPreview = {

            DefaultPreviewCard(
                painter = painter,
                text = title
            )
        },
        content = {

            Text(
                text = description,
                textAlign = TextAlign.Justify
            )
        },
        detailContent = {

            ActionIconButtom(
                icon = Icons.Rounded.Info,
                tint = YellowMaterial,
                content = "",
                onClick = onDetail
            )
        },
        actionContent = {

            ActionIconButtom(
                icon = Icons.Outlined.ViewInAr,
                tint = PurpleMaterial,
                content = "AR",
                onClick = onAction
            )
        }
    )
}