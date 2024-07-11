package com.uefa.bracket.paresantation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
internal fun BracketsList(rounds: List<Round>) {

    val pagerState = rememberPagerState(pageCount = {rounds.size}, initialPage = 0)

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 32.dp),
        modifier = Modifier.fillMaxWidth()
    ) { pager ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp) // Adjust the padding to control the visibility of the next item
        ) {

            Text(
                text = "Round ${pager + 1}",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            val firstItemMatchesSize = rounds.firstOrNull()?.matches?.size?:0

            val cardTopPadding = (firstItemMatchesSize * 6) * pager

            val cardBottomPadding by animateIntAsState(
                targetValue = if (pager <= pagerState.currentPage) 8 else 12 * firstItemMatchesSize
            )

            MatchesRound(
                modifier = Modifier
                    .padding(top = cardTopPadding.dp),
                round = rounds[pager],
                cardTopBottomPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 0.dp, bottom = cardBottomPadding.dp),
                isShowBrackets = pagerState.currentPage <= pager && (pager + 1) != rounds.size
            )

        }
    }
}