package io.github.sadeqit.weatherino.presentation.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.sadeqit.weatherino.domain.model.DistanceUnit
import io.github.sadeqit.weatherino.domain.model.PrecipitationUnit
import io.github.sadeqit.weatherino.domain.model.PressureUnit
import io.github.sadeqit.weatherino.domain.model.TemperatureUnit
import io.github.sadeqit.weatherino.domain.model.WindSpeedUnit
import io.github.sadeqit.weatherino.presentation.component.background.WeatherBackground
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder
import io.github.sadeqit.weatherino.presentation.theme.GlassShape


@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onTemperatureUnitChange: (TemperatureUnit) -> Unit,
    onWindSpeedUnitChange: (WindSpeedUnit) -> Unit,
    onPrecipitationUnitChange: (PrecipitationUnit) -> Unit,
    onPressureUnitChange: (PressureUnit) -> Unit,
    onDistanceUnitChange: (DistanceUnit) -> Unit,
    onResetUnits: () -> Unit,
    onBackClick: () -> Unit,
) {
    var selectedSetting by remember {
        mutableStateOf<SettingType?>(null)
    }

    WeatherBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars),
        ) {
            SettingsTopBar(
                onBackClick = onBackClick,
            )

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Rtl,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                ) {
                    UnitsCard(
                        temperatureUnit = uiState.temperatureUnit.symbol,
                        windSpeedUnit = uiState.windSpeedUnit.symbol,
                        precipitationUnit = uiState.precipitationUnit.symbol,
                        pressureUnit = uiState.pressureUnit.symbol,
                        visibilityUnit = uiState.distanceUnit.symbol,
                        onSettingClick = {
                            selectedSetting = it
                        },
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    ResetSettingsCard(
                        onClick = onResetUnits,
                    )
                }
            }
        }
    }

    selectedSetting?.let { setting ->
        UnitSelectionBottomSheet(
            setting = setting,
            selectedUnit = when (setting) {
                SettingType.Temperature -> uiState.temperatureUnit.symbol
                SettingType.WindSpeed -> uiState.windSpeedUnit.symbol
                SettingType.Precipitation -> uiState.precipitationUnit.symbol
                SettingType.Pressure -> uiState.pressureUnit.symbol
                SettingType.Visibility -> uiState.distanceUnit.symbol
            },
            onUnitSelected = { unit ->
                when (setting) {
                    SettingType.Temperature -> {
                        TemperatureUnit.entries
                            .firstOrNull { it.symbol == unit }
                            ?.let(onTemperatureUnitChange)
                    }

                    SettingType.WindSpeed -> {
                        WindSpeedUnit.entries
                            .firstOrNull { it.symbol == unit }
                            ?.let(onWindSpeedUnitChange)
                    }

                    SettingType.Precipitation -> {
                        PrecipitationUnit.entries
                            .firstOrNull { it.symbol == unit }
                            ?.let(onPrecipitationUnitChange)
                    }

                    SettingType.Pressure -> {
                        PressureUnit.entries
                            .firstOrNull { it.symbol == unit }
                            ?.let(onPressureUnitChange)
                    }

                    SettingType.Visibility -> {
                        DistanceUnit.entries
                            .firstOrNull { it.symbol == unit }
                            ?.let(onDistanceUnitChange)
                    }
                }

                selectedSetting = null
            },
            onDismissRequest = {
                selectedSetting = null
            },
        )
    }
}

@Composable
private fun SettingsTopBar(
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 12.dp),
    ) {
        androidx.compose.material3.IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "بازگشت",
                tint = Color.White,
            )
        }

        Text(
            text = "تنظیمات",
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun UnitsCard(
    temperatureUnit: String,
    windSpeedUnit: String,
    precipitationUnit: String,
    pressureUnit: String,
    visibilityUnit: String,
    onSettingClick: (SettingType) -> Unit,
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column {
            Text(
                text = "واحدهای اندازه گیری",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            Spacer(
                modifier = Modifier.height(16.dp),
            )

            HorizontalDivider(
                color = GlassBorder,
                thickness = 2.dp,
            )

            val items = listOf(
                SettingItem(
                    type = SettingType.Temperature,
                    title = "دما",
                    value = temperatureUnit,
                    icon = Icons.Default.Thermostat,
                    tint = Color(0xFFFF6B6B),
                ),
                SettingItem(
                    type = SettingType.WindSpeed,
                    title = "سرعت باد",
                    value = windSpeedUnit,
                    icon = Icons.Default.Air,
                    tint = Color(0xFF81D4FA),
                ),
                SettingItem(
                    type = SettingType.Precipitation,
                    title = "میزان بارش",
                    value = precipitationUnit,
                    icon = Icons.Default.WaterDrop,
                    tint = Color(0xFF64B5F6),
                ),
                SettingItem(
                    type = SettingType.Pressure,
                    title = "فشار هوا",
                    value = pressureUnit,
                    icon = Icons.Default.Compress,
                    tint = Color(0xFFCE93D8),
                ),
                SettingItem(
                    type = SettingType.Visibility,
                    title = "مسافت / دید",
                    value = visibilityUnit,
                    icon = Icons.Default.Visibility,
                    tint = Color(0xFF81C784),
                ),
            )

            items.forEachIndexed { index, item ->
                SettingsItemRow(
                    item = item,
                    onClick = {
                        onSettingClick(item.type)
                    },
                )

                if (index < items.lastIndex) {
                    HorizontalDivider(
                        color = GlassBorder,
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsItemRow(
    item: SettingItem,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            modifier = Modifier.size(26.dp),
            tint = item.tint,
        )

        Spacer(
            modifier = Modifier.width(12.dp),
        )

        Text(
            text = item.title,
            modifier = Modifier.weight(1f),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = item.value,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )

        Spacer(
            modifier = Modifier.width(4.dp),
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color.White.copy(alpha = 0.7f),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UnitSelectionBottomSheet(
    setting: SettingType,
    selectedUnit: String,
    onUnitSelected: (String) -> Unit,
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = Color(0xFF16243A),
        contentColor = Color.White,
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Rtl,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 24.dp,
                    ),
            ) {
                Text(
                    text = setting.title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(
                    modifier = Modifier.height(12.dp),
                )

                setting.options.forEachIndexed { index, option ->
                    UnitOption(
                        option = option,
                        selected = option.symbol == selectedUnit,
                        onClick = {
                            onUnitSelected(option.symbol)
                        },
                    )

                    if (index < setting.options.lastIndex) {
                        HorizontalDivider(
                            color = GlassBorder,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UnitOption(
    option: UnitOption,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
        )

        Spacer(
            modifier = Modifier.width(8.dp),
        )

        Text(
            text = option.name,
            modifier = Modifier.weight(1f),
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
        )

        Text(
            text = option.symbol,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun ResetSettingsCard(
    onClick: () -> Unit,
) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clip(GlassShape)
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = "بازگشت به تنظیمات پیش فرض",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "بازنشانی واحدهای اندازه گیری",
                    color = Color.White.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Icon(
                imageVector = Icons.Default.RestartAlt,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}

private enum class SettingType(
    val title: String,
    val options: List<UnitOption>,
)
{
    Temperature(
        title = "واحد دما",
        options = listOf(
            UnitOption("سانتی گراد", "°C"),
            UnitOption("فارنهایت", "°F"),
        ),
    ),

    WindSpeed(
        title = "واحد سرعت باد",
        options = listOf(
            UnitOption("کیلومتر بر ساعت", "km/h"),
            UnitOption("متر بر ثانیه", "m/s"),
            UnitOption("مایل بر ساعت", "mph"),
        ),
    ),

    Precipitation(
        title = "واحد بارش",
        options = listOf(
            UnitOption("میلی متر", "mm"),
            UnitOption("اینچ", "in"),
        ),
    ),

    Pressure(
        title = "واحد فشار هوا",
        options = listOf(
            UnitOption("هکتوپاسکال", "hPa"),
            UnitOption("میلی متر جیوه", "mmHg"),
            UnitOption("اینچ جیوه", "inHg"),
        ),
    ),

    Visibility(
        title = "واحد مسافت",
        options = listOf(
            UnitOption("کیلومتر", "km"),
            UnitOption("مایل", "mi"),
        ),
    ),
}

private data class UnitOption(
    val name: String,
    val symbol: String,
)

private data class SettingItem(
    val type: SettingType,
    val title: String,
    val value: String,
    val icon: ImageVector,
    val tint: Color,
)