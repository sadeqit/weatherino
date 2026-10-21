package io.github.sadeqit.weatherino.presentation.screen.support

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeqit.weatherino.platform.openLink
import io.github.sadeqit.weatherino.presentation.component.background.WeatherBackground
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder
import io.github.sadeqit.weatherino.resources.Res
import io.github.sadeqit.weatherino.resources.ic_like
import io.github.sadeqit.weatherino.resources.ic_pic
import org.jetbrains.compose.resources.painterResource

@Composable
fun SupportScreen(
    onBackClick: () -> Unit,
) {
    WeatherBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars),
        ) {
            SupportTopBar(
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
                    SupportIntroduction()

                    SpecialThanks()

                    DonateSection()

                    BenefitsSection()

                    HorizontalDivider(
                        color = GlassBorder,
                        thickness = 2.dp,
                    )

                    Text(
                        text = "از حمایت و همراهی شما سپاسگزارم ❤️",
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(
                        modifier = Modifier.height(50.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun SupportTopBar(
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
            text = "حمایت از من",
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun SupportIntroduction() {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "👋 سلام، من صادق آی تی هستم",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "ودرینو یک پروژه رایگان و متن‌باز است که با هدف ارائه تجربه‌ای ساده، زیبا و کاربردی برای مشاهده وضعیت آب‌وهوا توسعه داده می‌شود.",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Justify

                )

            }
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .border(
                        width = 2.dp,
                        color = GlassBorder,
                        shape = CircleShape,
                    )
                    .padding(4.dp),
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_pic),
                    contentDescription = "تصویر توسعه‌دهنده",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Composable
private fun SpecialThanks() {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .border(
                        width = 3.dp,
                        color = GlassBorder,
                        shape = CircleShape
                    )

            ) {

                Image(
                    painter = painterResource(Res.drawable.ic_like),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = "از همراهی شما سپاسگزارم",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "حمایت شما انگیزه‌ای برای ادامه توسعه، بهبود و اضافه شدن امکانات جدید به ودرینو است.",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 21.sp,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
private fun DonateSection() {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = "حمایت مالی",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                    tint = Color.White,
                )


            }

            Text(
                text = "اگر ودرینو برایتان مفید بوده، می توانید با حمایت مالی در ادامه توسعه آن سهیم باشید.",
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,

            )

            Button(
                onClick = {
                    openLink("https://daramet.com/sadegh.it")
                },
            ) {
                Text(
                    text = "حمایت می کنم", fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
@Composable
private fun BenefitsSection() {
    val benefits = listOf(
        Triple(
            Icons.Default.BugReport,
            "بهبود عملکرد و رفع باگ ها",
            Color(0xFFFF6B6B),
        ),
        Triple(
            Icons.Default.NewReleases,
            "افزودن قابلیت های جدید",
            Color(0xFFFFC857),
        ),
        Triple(
            Icons.Default.Sync,
            "بهبود و بروزرسانی مداوم",
            Color(0xFF64B5F6),
        ),
        Triple(
            Icons.Default.RocketLaunch,
            "ادامه توسعه ودرینو",
            Color(0xFF81C784),
        ),
    )

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "حمایت شما صرف چه چیزهایی می شود؟",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            HorizontalDivider(
                color = GlassBorder,
                thickness = 2.dp,
            )

            benefits.forEachIndexed { index, (icon, title, tint) ->
                BenefitItem(
                    icon = icon,
                    title = title,
                    tint = tint,
                )

                if (index < benefits.lastIndex) {
                    HorizontalDivider(
                        color = GlassBorder,
                    )
                }
            }
        }
    }
}
/*@Composable
private fun BenefitsSection() {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "حمایت شما صرف چه چیزهایی می‌شود؟",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            HorizontalDivider(
                color = GlassBorder,
                thickness = 2.dp,
            )
            BenefitItem(
                icon = Icons.Default.BugReport,
                title = "بهبود عملکرد و رفع باگ‌ ها",
                Color(0xFFFF6B6B)

            )
            HorizontalDivider(color = GlassBorder)
            BenefitItem(
                icon = Icons.Default.NewReleases,
                title = "افزودن قابلیت‌ های جدید",
                Color(0xFFFFC857)
            )
            HorizontalDivider(color = GlassBorder)
            BenefitItem(
                icon = Icons.Default.Sync,
                title = "بهبود و بروزرسانی مداوم",
                Color(0xFF64B5F6)
            )
            HorizontalDivider(color = GlassBorder)
            BenefitItem(
                icon = Icons.Default.RocketLaunch,
                title = "ادامه توسعه ودرینو",
                Color(0xFF81C784)
            )
        }
    }
}*/

@Composable
private fun BenefitItem(
    icon: ImageVector,
    title: String,
    tint: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(26.dp),
            tint =  tint,
        )

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
