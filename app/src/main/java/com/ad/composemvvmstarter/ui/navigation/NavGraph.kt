package com.ad.composemvvmstarter.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ad.composemvvmstarter.ui.screens.home.composables.HomeScreen
import com.ad.composemvvmstarter.ui.screens.profile.composables.ProfileScreen
import com.ad.composemvvmstarter.ui.screens.settings.composables.SettingsScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {

        /** Home Screen */
        composable(route = BottomBarScreen.Home.route,
            enterTransition = { bottomNavEnter() },
            exitTransition = { bottomNavExit() },
            popEnterTransition = { bottomNavPopEnter() },
            popExitTransition = { bottomNavPopExit() }) {
            HomeScreen(navController)
        }

        /** Profile Screen */
        composable(route = BottomBarScreen.Profile.route,
            enterTransition = { bottomNavEnter() },
            exitTransition = { bottomNavExit() },
            popEnterTransition = { bottomNavPopEnter() },
            popExitTransition = { bottomNavPopExit() }) {
            ProfileScreen(navController)
        }

        /** Settings Screen */
        composable(route = BottomBarScreen.Settings.route,
            enterTransition = { bottomNavEnter() },
            exitTransition = { bottomNavExit() },
            popEnterTransition = {
                bottomNavPopEnter()
            },
            popExitTransition = { bottomNavPopExit() }) {
            SettingsScreen(navController)
        }
    }
}