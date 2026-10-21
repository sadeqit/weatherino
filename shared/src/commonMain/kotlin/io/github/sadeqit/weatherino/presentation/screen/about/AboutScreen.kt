package io.github.sadeqit.weatherino.presentation.screen.about

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeqit.weatherino.platform.openLink
import io.github.sadeqit.weatherino.presentation.component.background.WeatherBackground
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.theme.GlassShape
import io.github.sadeqit.weatherino.resources.Res
import io.github.sadeqit.weatherino.resources.icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun AboutScreen(
    onBackClick: () -> Unit,
) {
    WeatherBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars),
        ) {
            AboutTopBar(
                onBackClick = onBackClick,
            )

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Rtl,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    AboutHeader()
                    AboutDescription()
                    AboutDataSource()
                    AboutFeatures()
                    AboutDeveloper()
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}
@Composable
private fun AboutTopBar(
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 12.dp),
    ) {
        IconButton(
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
            text = "درباره ودرینو",
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun AboutHeader() {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(Res.drawable.icon),
                contentDescription = "Weatherino",
                modifier = Modifier.size(90.dp),
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Weatherino",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "هواشناسی هوشمند، مدرن و دقیق",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun AboutDescription() {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "درباره برنامه",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "ودرینو یک اپلیکیشن هواشناسی رایگان و متن باز است که با Kotlin Multiplatform برای اندروید، دسکتاپ و وب توسعه داده شده است و تجربه ای ساده از وضعیت آب و هوا را ارائه می دهد.",
                color = Color.White.copy(alpha = 0.9f),
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 24.sp,
            )
        }
    }
}

@Composable
private fun AboutDataSource() {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                openLink("https://openweathermap.org")
            },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "منبع داده‌های هواشناسی",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(
                    modifier = Modifier.width(8.dp),
                )



                Icon(
                    imageVector = Icons.Default.Cloud,
                    contentDescription = null,
                    tint = Color.White,
                )
            }

            Text(
                text = "اطلاعات هواشناسی و کیفیت هوا در ودرینو با استفاده از داده‌ها و API سرویس OpenWeather دریافت می‌شود.",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 22.sp,
            )

            Row(

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "OpenWeather",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                )

                Spacer(
                    modifier = Modifier.width(4.dp),
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                    contentDescription = "باز کردن وب‌سایت OpenWeather",
                    modifier = Modifier.size(16.dp),
                    tint = Color.White,
                )
            }
        }
    }
}

@Composable
private fun AboutFeatures() {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "ویژگی‌ها",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "• وضعیت لحظه ای آب وهوا با جزئیات کامل\n" +
                        "• نمایش کیفیت هوا\n" +
                        "• پیش بینی ساعتی و ۵ روزه\n" +
                        "• جستجوی آسان شهر ها\n" +
                        "• پشتیبانی از موقعیت مکانی\n" +
                        "• طراحی مدرن و سازگار با پلتفرم‌های اندروید، دسکتاپ و وب",
                color = Color.White.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 26.sp,
            )
        }
    }
}

@Composable
private fun AboutDeveloper() {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clip(GlassShape)
            .clickable {
                openLink("https://github.com/sadeqit/weatherino")
            },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "ساخته شده توسط صادق آی تی \uD83D\uDC68\u200D\uD83D\uDCBB",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "مشاهده پروژه در GitHub",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                )

                Spacer(Modifier.width(6.dp))

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.size(16.dp),
                )
            }
        }
    }
}