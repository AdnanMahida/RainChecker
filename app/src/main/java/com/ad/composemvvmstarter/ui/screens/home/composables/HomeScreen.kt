package com.ad.composemvvmstarter.ui.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ad.composemvvmstarter.ui.screens.bottomNavPadding
import com.ad.composemvvmstarter.ui.screens.home.viewmodels.HomeViewModel
import timber.log.Timber


@Composable
fun HomeScreen(navController: NavController) {

    val viewModel: HomeViewModel = hiltViewModel()
    val listOfDevices by viewModel.listOfDevicesFlow.collectAsState()

    Timber.i("DeviceList :: ${listOfDevices?.data}")
    LaunchedEffect(Unit) {
        viewModel.callGetListOfDevices()
    }
    HomeScreenScaffold(
        viewModel = viewModel,
        navController = navController
    )
}


@Composable
private fun HomeScreenScaffold(
    viewModel: HomeViewModel,
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = bottomNavPadding),
    ) { paddingValues ->
        HomeScreenContents(
            viewModel = viewModel,
            navController = navController,
            paddingValues = paddingValues
        )
    }
}

@Composable
fun HomeScreenContents(
    viewModel: HomeViewModel,
    navController: NavController,
    paddingValues: PaddingValues
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text("Home")
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController()
    )
}