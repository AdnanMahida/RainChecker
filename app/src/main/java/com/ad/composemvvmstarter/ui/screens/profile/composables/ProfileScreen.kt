package com.ad.composemvvmstarter.ui.screens.profile.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ad.composemvvmstarter.ui.screens.bottomNavPadding
import com.ad.composemvvmstarter.ui.screens.profile.viewmodels.ProfileViewModel


@Composable
fun ProfileScreen(navController: NavController) {

    val viewModel: ProfileViewModel = hiltViewModel()

    ProfileScreenScaffold(
        viewModel = viewModel,
        navController = navController
    )
}


@Composable
private fun ProfileScreenScaffold(
    viewModel: ProfileViewModel,
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = bottomNavPadding),
    ) { paddingValues ->
        ProfileScreenContents(
            viewModel = viewModel,
            navController = navController,
            paddingValues = paddingValues
        )
    }
}

@Composable
fun ProfileScreenContents(
    viewModel: ProfileViewModel,
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
        Text("Profile")
    }
}


@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen(
        navController = rememberNavController()
    )
}