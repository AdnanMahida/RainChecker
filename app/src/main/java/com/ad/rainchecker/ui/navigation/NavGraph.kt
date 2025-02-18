package com.ad.rainchecker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ad.rainchecker.ui.screens.details.composables.DetailsScreen
import com.ad.rainchecker.ui.screens.details.viewmodels.SharedViewModel
import com.ad.rainchecker.ui.screens.home.composables.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    val viewModel: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen { weatherInfo ->
                navController.popBackStack(navController.graph.startDestinationId, inclusive = true)
                viewModel.selectWeather(weatherInfo)
                navController.navigate(Screen.Details.route)
            }
        }
        composable(
            route = Screen.Details.route
        ) { backStackEntry ->
            DetailsScreen(
                weatherInfo = viewModel.selectedWeatherData ?: return@composable
            ) {
                navController.popBackStack()
            }
        }
    }
}