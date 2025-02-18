package com.ad.rainchecker.data.model


import com.google.gson.annotations.SerializedName

data class WeatherInfo(
    @SerializedName("city")
    var city: City?,
    @SerializedName("cnt")
    var cnt: Int?,
    @SerializedName("cod")
    var cod: String?,
    @SerializedName("list")
    var list: List<Item8?>?,
    @SerializedName("message")
    var message: Int?
) {
    data class City(
        @SerializedName("coord")
        var coord: Coord?,
        @SerializedName("country")
        var country: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("population")
        var population: Int?,
        @SerializedName("sunrise")
        var sunrise: Int?,
        @SerializedName("sunset")
        var sunset: Int?,
        @SerializedName("timezone")
        var timezone: Int?
    ) {
        data class Coord(
            @SerializedName("lat")
            var lat: Double?,
            @SerializedName("lon")
            var lon: Double?
        )
    }

    data class Item8(
        @SerializedName("clouds")
        var clouds: Clouds?,
        @SerializedName("dt")
        var dt: Int?,
        @SerializedName("dt_txt")
        var dtTxt: String?,
        @SerializedName("main")
        var main: Main?,
        @SerializedName("pop")
        var pop: Double?,
        @SerializedName("rain")
        var rain: Rain?,
        @SerializedName("sys")
        var sys: Sys?,
        @SerializedName("visibility")
        var visibility: Int?,
        @SerializedName("weather")
        var weather: List<Weather?>?,
        @SerializedName("wind")
        var wind: Wind?
    ) {
        data class Clouds(
            @SerializedName("all")
            var all: Int?
        )

        data class Main(
            @SerializedName("feels_like")
            var feelsLike: Double?,
            @SerializedName("grnd_level")
            var grndLevel: Int?,
            @SerializedName("humidity")
            var humidity: Int?,
            @SerializedName("pressure")
            var pressure: Int?,
            @SerializedName("sea_level")
            var seaLevel: Int?,
            @SerializedName("temp")
            var temp: Double?,
            @SerializedName("temp_kf")
            var tempKf: Double?,
            @SerializedName("temp_max")
            var tempMax: Double?,
            @SerializedName("temp_min")
            var tempMin: Double?
        )

        data class Rain(
            @SerializedName("3h")
            var h: Double?
        )

        data class Sys(
            @SerializedName("pod")
            var pod: String?
        )

        data class Weather(
            @SerializedName("description")
            var description: String?,
            @SerializedName("icon")
            var icon: String?,
            @SerializedName("id")
            var id: Int?,
            @SerializedName("main")
            var main: String?
        )

        data class Wind(
            @SerializedName("deg")
            var deg: Int?,
            @SerializedName("gust")
            var gust: Double?,
            @SerializedName("speed")
            var speed: Double?
        )
    }

    fun getTemperature(): String? {
        return this.list?.firstOrNull()?.main?.temp?.toString() ?: "0"
    }

    fun getHumidity(): String? {
        return this.list?.firstOrNull()?.main?.humidity?.toString() ?: "0"
    }

    fun getRainProbability(): Int {
        val pop = this.list?.firstOrNull()?.pop ?: 0.0
        return (pop * 100).toInt()
    }

    fun getRainProbabilityFloat(): Float {
        val pop = this.list?.firstOrNull()?.pop ?: 0.0
        val percentage = (pop * 100).toInt()
        return percentage / 100f
    }

    fun getCityName(): String? {
        return this.city?.name
    }

    fun getLatestUpdateTime(): String? {
        return this.list?.maxByOrNull { it?.dtTxt ?: "" }?.dtTxt ?: ""
    }

    fun getDescription(): String? {
        return this.list?.firstOrNull()?.weather?.firstOrNull()?.description ?: ""
    }

}