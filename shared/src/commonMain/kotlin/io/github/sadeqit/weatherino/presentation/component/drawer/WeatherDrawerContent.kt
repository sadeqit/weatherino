package io.github.sadeqit.weatherino.presentation.component.drawer


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.navigation.DrawerItem
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder
import io.github.sadeqit.weatherino.presentation.theme.GlassShape
import io.github.sadeqit.weatherino.presentation.theme.GlassWhite
import io.github.sadeqit.weatherino.presentation.theme.WeatherBlue
import io.github.sadeqit.weatherino.presentation.theme.WeatherBlueDark
import io.github.sadeqit.weatherino.resources.Res
import io.github.sadeqit.weatherino.resources.icon
import org.jetbrains.compose.resources.painterResource

private val drawerGroups = listOf(
    listOf(
        DrawerItem.Settings,
        DrawerItem.RateApp,
        DrawerItem.Support,
        DrawerItem.Contact,
        DrawerItem.About,
    ),
    listOf(
        DrawerItem.Exit,
    ),
)

@Composable
fun WeatherDrawerContent(
    currentItem: DrawerItem?,
    onItemClick: (DrawerItem) -> Unit,
    modifier: Modifier = Modifier,
) {

    ModalDrawerSheet(
        modifier = modifier.width(290.dp).background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    WeatherBlue,
                    WeatherBlueDark,
                )
            )
        ),
        drawerContainerColor = Color.Transparent,

        drawerShape = RoundedCornerShape(15.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
        ) {

            // Header
            DrawerHeader()

            Box(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    drawerGroups.forEachIndexed { index, group ->

                        DrawerGroup(
                            items = group,
                            currentItem = currentItem,
                            onItemClick = onItemClick,
                        )

                        if (index != drawerGroups.lastIndex) {
                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                }
            }
        }
    }
}


@Composable
private fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(
                color = GlassWhite
            )
            .border(
                width = 1.dp,
                color = GlassBorder,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(Res.drawable.icon),
                contentDescription = "Weatherino",
                modifier = Modifier.size(80.dp),
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "ودِرینو — هواشناسی هوشمند",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun DrawerGroup(
    items: List<DrawerItem>,
    currentItem: DrawerItem?,
    onItemClick: (DrawerItem) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items.forEach { item ->

            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(GlassShape)
                    .clickable {
                    onItemClick(item)
                },
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(30.dp),
                    )

                    Spacer(
                        modifier = Modifier.width(16.dp)
                    )

                    Text(
                        text = item.title,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 18.sp,
                        fontWeight = if (item == currentItem) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        },
                    )
                }
            }
        }
    }
}