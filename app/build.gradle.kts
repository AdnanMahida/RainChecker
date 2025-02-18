import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.ad.rainchecker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ad.rainchecker"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        flavorDimensions.add("environment")
        productFlavors {
            create("stage") {
                dimension = "environment"
                applicationId = "com.ad.rainchecker.stage"
                buildConfigField(
                    "String", "BASE_URL", "\"https://api.openweathermap.org/\""
                )
                buildConfigField(
                    type = "String",
                    name = "OPEN_WEATHER_API_KEY",
                    value = "\"YOUR_API_KEY\""
                )
                resValue(
                    "string",
                    "app_name",
                    "Rain Checker"
                )
                setProperty(
                    "archivesBaseName",
                    "RainChecker_V${defaultConfig.versionName}_${getCurrentDate()}"
                )
            }

            create("production") {
                dimension = "environment"
                applicationId = "com.ad.rainchecker"
                buildConfigField(
                    "String", "BASE_URL", "\"https://api.openweathermap.org/\""
                )
                buildConfigField(
                    type = "String",
                    name = "OPEN_WEATHER_API_KEY",
                    value = "\"YOUR_API_KEY\""
                )
                resValue(
                    "string",
                    "app_name",
                    "Rain Checker"
                )
                setProperty(
                    "archivesBaseName",
                    "RainChecker_V${defaultConfig.versionName}_${getCurrentDate()}"
                )
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    android.buildFeatures.buildConfig = true
}
// Helper function to get the current date
fun getCurrentDate(): String {
    val formatter = SimpleDateFormat(
        "dd_MM_yyyy", Locale.getDefault()
    )
    return formatter.format(Date())
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    implementation("androidx.compose.material3:material3-window-size-class:1.1.1")
    debugImplementation(libs.androidx.ui.test.manifest)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.common)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.ext.compiler)

    // Location Services
    implementation("com.google.android.gms:play-services-location:20.0.0")

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //Timber
    implementation(libs.timber)

    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)

    // secure sharedpref
    implementation(libs.androidx.security.crypto)

    // Coil Image loading library.
    implementation(libs.coil.compose)

}