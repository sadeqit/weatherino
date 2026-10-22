package io.github.sadeqit.weatherino.presentation.screen.splash

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.sadeqit.weatherino.presentation.component.background.WeatherBackground
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.viewmodel.SplashViewModel
import io.github.sadeqit.weatherino.resources.Res
import io.github.sadeqit.weatherino.resources.icon
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(
    onConnectionSuccess: () -> Unit
) {
    val viewModel: SplashViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    WeatherBackground {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val screenHeightPx = constraints.maxHeight

            var startAnimation by remember {
                mutableStateOf(false)
            }

            val imageOffset by animateIntAsState(
                targetValue = if (startAnimation) 0 else -screenHeightPx,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = FastOutSlowInEasing
                ),
                label = "imageAnimation"
            )

            val glassOffset by animateIntAsState(
                targetValue = if (startAnimation) 0 else screenHeightPx,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = FastOutSlowInEasing
                ),
                label = "glassAnimation"
            )

            LaunchedEffect(Unit) {
                delay(200.milliseconds)
                startAnimation = true

                delay(1500.milliseconds)

                viewModel.checkConnection()
            }

            LaunchedEffect(uiState.isConnected) {
                if (uiState.isConnected) {
                    onConnectionSuccess()
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.icon),
                    contentDescription = "Weatherino",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .offset {
                            IntOffset(0, imageOffset)
                        }
                        .size(120.dp)
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                GlassCard(
                    modifier = Modifier
                        .offset {
                            IntOffset(0, glassOffset)
                        }
                        .padding(horizontal = 50.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Weatherino",
                            color = Color.White
                        )

                        Spacer(
                            modifier = Modifier.height(14.dp)
                        )

                        Text(
                            text = "ودرینو | هواشناسی هوشمند",
                            color = Color.White
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                if (uiState.hasConnectionError) {
                    GlassCard(
                        modifier = Modifier
                            .padding(horizontal = 50.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 20.dp,
                                    vertical = 16.dp
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.WifiOff,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Text(
                                text = "اینترنت وصل نیست",
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = "ابتدا اینترنت خود را وصل کنید، سپس تلاش مجدد را بزنید",
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )

                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )

                            Button(
                                onClick = viewModel::checkConnection
                            ) {
                                Text(
                                    text = "تلاش مجدد",
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}