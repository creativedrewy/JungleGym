package com.solanamobile.krate.startscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.solanamobile.krate.extension.getScreenModel
import com.solanamobile.krate.startscreen.viewmodel.StartScreenViewModel
import com.solanamobile.krate.startscreen.viewmodel.StartScreenViewState

class StartScreen: Screen {

    @Composable
    override fun Content() {
        val viewModel: StartScreenViewModel = getScreenModel()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.loadLogos()
        }

        StartScreenContents(
            viewState = state
        )
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun StartScreenContents(
    viewState: StartScreenViewState
) {
    val navigator = LocalNavigator.currentOrThrow

//        val scope = rememberCoroutineScope()
//
//        scope.launch {
//            delay(500)
//
//            navigator.replace(ScreenRegistry.get(NavScreenProvider.CreateScreen))
//        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(
                top = 183.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {
        Text(
            text = "CREATORS\nGONNA",
            color = Color(0xFF172C4A),
            style = TextStyle.Default.copy(
                fontSize = 59.sp,
                lineHeight = 60.sp,
                drawStyle = Stroke(
                    miter = 10f,
                    width = 5f,
                    join= StrokeJoin.Miter
                )
            )
        )

        if (viewState.logos.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    bitmap = viewState.logos[0],
                    contentDescription = null
                )

                Image(
                    modifier = Modifier
                        .padding(
                            bottom = 33.dp
                        ),
                    bitmap = viewState.logos[1],
                    contentDescription = null
                )

                Image(
                    modifier = Modifier
                        .padding(
                            bottom = 66.dp
                        ),
                    bitmap = viewState.logos[2],
                    contentDescription = null
                )

                Image(
                    modifier = Modifier
                        .padding(
                            bottom = 99.dp
                        ),
                    bitmap = viewState.logos[3],
                    contentDescription = null
                )

                Image(
                    modifier = Modifier
                        .padding(
                            bottom = 132.dp
                        ),
                    bitmap = viewState.logos[4],
                    contentDescription = null
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "KRATE NOW",
                    fontSize = 32.sp,
                    lineHeight = 60.sp,
                )

                Box {
                    Image(
                        modifier = Modifier
                            .height(30.dp)
                            .width(55.dp),
                        bitmap = viewState.logos[5],
                        contentDescription = null
                    )

                    Image(
                        modifier = Modifier
                            .padding(
                                start = 8.dp
                            )
                            .height(30.dp)
                            .width(55.dp),
                        bitmap = viewState.logos[5],
                        contentDescription = null
                    )

                    Image(
                        modifier = Modifier
                            .padding(
                                start = 16.dp
                            )
                            .height(30.dp)
                            .width(55.dp),
                        bitmap = viewState.logos[5],
                        contentDescription = null
                    )

                    Image(
                        modifier = Modifier
                            .padding(
                                start = 24.dp
                            )
                            .height(30.dp)
                            .width(55.dp),
                        bitmap = viewState.logos[5],
                        contentDescription = null
                    )

                    Image(
                        modifier = Modifier
                            .padding(
                                start = 32.dp
                            )
                            .height(30.dp)
                            .width(55.dp),
                        bitmap = viewState.logos[6],
                        contentDescription = null
                    )
                }
            }
        }
    }
}