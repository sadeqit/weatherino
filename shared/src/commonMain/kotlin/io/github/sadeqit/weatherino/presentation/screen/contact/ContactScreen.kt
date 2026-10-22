package io.github.sadeqit.weatherino.presentation.screen.contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeqit.weatherino.platform.openLink
import io.github.sadeqit.weatherino.presentation.component.background.WeatherBackground
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder
import io.github.sadeqit.weatherino.presentation.theme.GlassShape
import io.github.sadeqit.weatherino.resources.Res
import io.github.sadeqit.weatherino.resources.ic_bale
import io.github.sadeqit.weatherino.resources.ic_chat
import io.github.sadeqit.weatherino.resources.ic_eta
import io.github.sadeqit.weatherino.resources.ic_git
import io.github.sadeqit.weatherino.resources.ic_gmile
import io.github.sadeqit.weatherino.resources.ic_insta
import io.github.sadeqit.weatherino.resources.ic_tel
import org.jetbrains.compose.resources.painterResource

@Composable
fun ContactScreen(
    onBackClick: () -> Unit,
) {
    val contactMethods = remember {
        listOf(
            ContactMethod(
                title = "ایمیل",
                subtitle = "ارسال پیام و پیشنهاد",
                icon = Res.drawable.ic_gmile,
                uri = "mailto:danayankalam@gmail.com",
            ),
            ContactMethod(
                title = "گیت‌ هاب",
                subtitle = "پروژه‌های من",
                icon = Res.drawable.ic_git,
                uri = "https://github.com/sadeqit",
            ),
            ContactMethod(
                title = "تلگرام",
                subtitle = "ارتباط با من در تلگرام",
                icon = Res.drawable.ic_tel,
                uri = "https://t.me/danayan_kalam",
            ),
            ContactMethod(
                title = "اینستاگرام",
                subtitle = "ما را دنبال کنید",
                icon = Res.drawable.ic_insta,
                uri = "https://instagram.com/danayan.kalam",
            ),
            ContactMethod(
                title = "بله",
                subtitle = "ارتباط با پشتیبانی در بله",
                icon = Res.drawable.ic_bale,
                uri = "https://ble.ir/danayan_kalam",
            ),
            ContactMethod(
                title = "ایتا",
                subtitle = "ارتباط با پشتیبانی در ایتا",
                icon = Res.drawable.ic_eta,
                uri = "https://eitaa.com/danayan_kalam",
            ),
        )
    }

    WeatherBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars),
        ) {
            ContactTopBar(
                onBackClick = onBackClick,
            )

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Rtl,
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 14.dp,
                    ),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    item(
                        span = { GridItemSpan(maxLineSpan) },
                    ) {
                        ContactHeader()
                    }

                    items(
                        items = contactMethods,
                        key = { it.title },
                    ) { contact ->
                        ContactCard(
                            contact = contact,
                        )
                    }

                    item(
                        span = { GridItemSpan(maxLineSpan) },
                    ) {
                        HorizontalDivider(
                            color = GlassBorder,
                            thickness = 2.dp
                        )
                        Text(
                            text = "از همراهی و اعتماد شما سپاسگزاریم ❤️",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactTopBar(
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
            text = "تماس با ما",
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun ContactHeader() {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_chat),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Fit,
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "📬 با ما در تماس باشید",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "اگر سوال، پیشنهاد یا ایده‌ای دارید،\nیا نیاز به یک اپلیکیشن حرفه‌ ای برای کسب‌ و کار خود دارید،\nبا ما در ارتباط باشید.",
                modifier = Modifier.fillMaxWidth(),
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
            )
        }
    }
}

@Composable
private fun ContactCard(
    contact: ContactMethod,
) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clip(GlassShape)
            .clickable {
                openLink(contact.uri)
            },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(contact.icon),
                contentDescription = contact.title,
                modifier = Modifier.size(48.dp),
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = contact.title,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = contact.subtitle,
                color = Color.White.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
            )

        }
    }
}