package com.ad.rainchecker.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details")

    fun detailWithArg(weather: String): String = "$Details/$weather"
}