package com.assignment.ui.common.components

import UserImage
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import coil.annotation.ExperimentalCoilApi
import com.assignment.data.network.model.users.UserInfoDto
import com.assignment.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

// help to manage crousel view
@OptIn(ExperimentalPagerApi::class, ExperimentalCoilApi::class)
@Composable
fun PhotoPagerScreen(
    userInfo: List<UserInfoDto>,
    page: Int = 0,
) {
    // help to manage pager state
    val pagerState = rememberPagerState()

    // set up pager
    HorizontalPager(
        count = userInfo.size,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = AppTheme.dimens.size_2_5),
    ) { page ->
        // get user instance
        val userInfo = userInfo[page]
       // create view
        Card(
            Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 65% and 85%
                    lerp(
                        start = 0.65f,
                        stop = 0.85f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 85%
                    alpha = lerp(
                        start = 0.5f,
                        stop = .85f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            // show user manage
            UserImage(url = userInfo.avatar)
        }

    }

    // help to manage state of crousal on scroll
    LaunchedEffect(page) {
        pagerState.scrollToPage(page)
    }
}
