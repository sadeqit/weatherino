package io.github.sadeqit.weatherino.domain.model

enum class TemperatureUnit(
    val symbol: String,
) {
    Celsius("°C"),
    Fahrenheit("°F"),
}

enum class WindSpeedUnit(
    val symbol: String,
) {
    KilometersPerHour("km/h"),
    MetersPerSecond("m/s"),
    MilesPerHour("mph"),
}

enum class PrecipitationUnit(
    val symbol: String,
) {
    Millimeter("mm"),
    Inch("in"),
}

enum class PressureUnit(
    val symbol: String,
) {
    Hectopascal("hPa"),
    MillimeterOfMercury("mmHg"),
    InchOfMercury("inHg"),
}

enum class DistanceUnit(
    val symbol: String,
) {
    Kilometer("km"),
    Mile("mi"),
}