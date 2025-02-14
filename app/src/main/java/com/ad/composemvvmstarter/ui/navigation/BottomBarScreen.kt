package com.ad.composemvvmstarter.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.ad.composemvvmstarter.R


sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: ImageVector
) {
    data object Home : BottomBarScreen(
        route = "home",
        title = R.string.navigation_home,
        icon = Icons.Default.Home
    )

    data object Profile : BottomBarScreen(
        route = "profile",
        title = R.string.navigation_profile,
        icon = Icons.Default.Person
    )

    data object Settings : BottomBarScreen(
        route = "settings",
        title = R.string.navigation_settings,
        icon = Icons.Default.Settings
    )
}