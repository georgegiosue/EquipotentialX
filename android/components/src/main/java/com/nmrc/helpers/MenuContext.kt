package com.nmrc.helpers

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector

enum class MenuContext(
    navIcon: ImageVector?,
    actionIcon: ImageVector?
) {

    MENU(navIcon = Icons.Outlined.Menu, null),
    MENU_DEFAULT(navIcon = Icons.Outlined.Menu, actionIcon = Icons.Outlined.MoreVert),
    MORE(null, actionIcon = Icons.Outlined.MoreVert),
    BACK(navIcon = Icons.Outlined.ArrowBack, null);

    var navIcon: ImageVector? = null
    var actionIcon: ImageVector? = null

    init {
        this@MenuContext.navIcon = navIcon
        this@MenuContext.actionIcon = actionIcon
    }
}