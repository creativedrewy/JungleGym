package com.solanamobile.krate.profilescreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.solanamobile.krate.extension.getScreenModel
import com.solanamobile.krate.profilescreen.viewmodel.ProfileScreenViewModel
import com.solanamobile.krate.profilescreen.viewmodel.ProfileViewState

object ProfileScreen: Screen {

    @Composable
    override fun Content() {
        val viewModel: ProfileScreenViewModel = getScreenModel()
        val state by viewModel.state.collectAsState()

        ProfileScreenContent(
            state = state
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreenContent(
    state: ProfileViewState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(171.dp)
                .background(MaterialTheme.colors.surface)
        )

        val pagerState = rememberPagerState()

        ScrollableTabRow(
//            modifier = Modifier
//                .fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.background,
            selectedTabIndex = 0,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .height(8.dp)
                        .tabIndicatorOffset(tabPositions[0])
                )
            },
//            divider = {
//                TabRowDefaults.Divider(
//                    thickness = 4.dp,
//                    color = MaterialTheme.colors.onSurface
//                )
//            }
        ) {
            Tab(
                modifier = Modifier
                    .wrapContentWidth()
                    .background(Color.Green),
                text = {
                    Text(
                        text = "Creations",
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.h6
                    )
                },
                selected = true,
                onClick = { },
            )
        }
    }
}