package com.ad.rainchecker.ui.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ad.rainchecker.core.Resource
import com.ad.rainchecker.data.model.WeatherInfo
import com.ad.rainchecker.ui.composables.CloudWithRing
import com.ad.rainchecker.ui.composables.DatePickerCard
import com.ad.rainchecker.ui.composables.FeatureCard
import com.ad.rainchecker.ui.composables.FloatingCircle
import com.ad.rainchecker.ui.screens.home.viewmodels.WeatherViewModel
import com.ad.rainchecker.ui.theme.primary600
import com.ad.rainchecker.ui.theme.weatherGradient
import com.ad.rainchecker.utility.extensions.isNetworkAvailable
import com.ad.rainchecker.utility.extensions.toToast
import java.util.Date


@Composable
fun HomeScreen(onNavigate: (WeatherInfo) -> Unit) {
    val viewModel: WeatherViewModel = hiltViewModel()

    HomeScreenScaffold(
        viewModel = viewModel,
        onNavigate = onNavigate
    )
}


@Composable
private fun HomeScreenScaffold(
    viewModel: WeatherViewModel,
    onNavigate: (WeatherInfo) -> Unit
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    val weather by viewModel.weatherFlow.collectAsState()

    LaunchedEffect(weather) {
        when (weather) {
            is Resource.Error -> {
                isLoading = false
                weather?.errorMessage?.toToast(context)
            }

            is Resource.Loading -> {
                isLoading = true
            }

            is Resource.Success -> {
                isLoading = false
                weather?.data?.let { weatherInfo ->
                    onNavigate.invoke(weatherInfo)
                }
            }

            null -> {
                isLoading = false
                weather?.errorMessage?.toToast(context)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(weatherGradient)
    ) {
        // Animated floating circles
        FloatingCircle(offsetX = 0.2f, offsetY = 0.1f, size = 48.dp, delay = 0)
        FloatingCircle(offsetX = 0.8f, offsetY = 0.3f, size = 32.dp, delay = 2000)
        FloatingCircle(offsetX = 0.4f, offsetY = 0.5f, size = 24.dp, delay = 4000)
        FloatingCircle(offsetX = 0.7f, offsetY = 0.7f, size = 40.dp, delay = 1000)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Cloud icon with pulsating ring
            CloudWithRing()

            Spacer(modifier = Modifier.height(48.dp))

            // Title
            Text(
                text = "RainCheck",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle
            Text(
                text = "Your friendly weather companion.\nNever get caught in the rain again! 🌧️",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Date picker card
            DatePickerCard(Date(), {})

            Spacer(modifier = Modifier.height(24.dp))

            // Features
            FeatureCard(
                icon = Icons.Default.DateRange,
                title = "Future Forecast",
                description = "Check weather up to 7 days ahead"
            )

            Spacer(modifier = Modifier.height(16.dp))

            FeatureCard(
                icon = Icons.Default.LocationOn,
                title = "Rain Alerts",
                description = "Know exactly when to bring an umbrella"
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Start button
            Button(
                onClick = {
                    if (!context.isNetworkAvailable()) {
                        "No Network available".toToast(context)
                        return@Button
                    }
                    viewModel.callGetWeather(context = context)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = primary600
                )
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = primary600)
                } else {
                    Text(
                        text = "Do I need a RainCheck?",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Start"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tap to start checking the weather ☀️",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 14.sp
            )
        }
    }

}