package com.ad.rainchecker.ui.screens.details.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ad.rainchecker.data.model.WeatherInfo
import com.ad.rainchecker.ui.composables.FloatingCircle
import com.ad.rainchecker.ui.composables.LocationCard
import com.ad.rainchecker.ui.composables.RainPredictionCard
import com.ad.rainchecker.ui.composables.WeatherConditionCard
import com.ad.rainchecker.ui.composables.WeatherDetailCard
import com.ad.rainchecker.ui.theme.weatherGradient


@Composable
fun DetailsScreen(weatherInfo: WeatherInfo, onBackPress: (Unit) -> Unit) {

    WeatherDetailsScaffold(
        weatherInfo = weatherInfo,
        onBackPress = onBackPress
    )
}


@Composable
private fun WeatherDetailsScaffold(
    weatherInfo: WeatherInfo,
    onBackPress: (Unit) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
            .background(weatherGradient)
    ) {
        // Animated floating circles
        FloatingCircle(offsetX = 0.1f, offsetY = 0.15f, size = 32.dp, delay = 0)
        FloatingCircle(offsetX = 0.9f, offsetY = 0.25f, size = 24.dp, delay = 2000)
        FloatingCircle(offsetX = 0.3f, offsetY = 0.35f, size = 16.dp, delay = 4000)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    onBackPress.invoke(Unit)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "RainCheck",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Your weather companion",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.size(48.dp)) // Invisible spacer for balance
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Location card
            LocationCard(weatherInfo.getCityName() ?: "-NA-")

            Spacer(modifier = Modifier.height(24.dp))

            // Rain prediction
            RainPredictionCard(weatherInfo.getRainProbabilityFloat())

            Spacer(modifier = Modifier.height(24.dp))

            // Weather details
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherDetailCard(
                    emoji = "🌡️",
                    value = "${weatherInfo.getTemperature()}°C",
                    label = "Temperature"
                )

                WeatherDetailCard(
                    emoji = "💧", value = "${weatherInfo.getHumidity()}%", label = "Humidity"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Weather condition
            WeatherConditionCard(weatherInfo.getDescription().toString())

            Spacer(modifier = Modifier.height(24.dp))

            // Footer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Updated: ${weatherInfo.getLatestUpdateTime() ?: ""} ago",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 12.sp
                )
                Text(
                    text = "Powered by OpenWeatherMap",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 12.sp
                )
            }
        }
    }
}