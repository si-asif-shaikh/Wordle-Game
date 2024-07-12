package com.uefa.bracket.paresantation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uefa.bracket.bracket.Bracket
import com.uefa.bracket.bracket.BracketColumnView

object Constants {
    const val DEFAULT_BOTTOM_PADDING = 8
    const val SECOND_PAGE_BOTTOM_PADDING = 12
    const val BRACKET_WIDTH = 75
}

@ExperimentalFoundationApi
@Composable
internal fun BracketsList(rounds: List<Bracket>) {

    val pagerState = rememberPagerState(pageCount = {rounds.size}, initialPage = 0)

    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(start = 16.dp, end = 32.dp),
            modifier = Modifier
                .fillMaxSize()
        ) { pager ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                     // Adjust the padding to control the visibility of the next item
            ) {

//                Text(
//                    text = "Round ${pager + 1}",
//                    style = MaterialTheme.typography.h5,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//
//                val firstItemMatchesSize = rounds.firstOrNull()?.matches?.size?:0
//
//                val cardTopPadding = (firstItemMatchesSize * 6) * pager
//
//                val cardBottomPadding by animateIntAsState(
//                    targetValue = if (pager <= pagerState.currentPage) DEFAULT_BOTTOM_PADDING else SECOND_PAGE_BOTTOM_PADDING * firstItemMatchesSize
//                )
//
//                MatchesRound(
//                    modifier = Modifier
//                        .padding(top = cardTopPadding.dp),
//                    round = rounds[pager],
//                    cardTopBottomPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 0.dp, bottom = cardBottomPadding.dp),
//                    isShowBrackets = pagerState.currentPage <= pager && (pager + 1) != rounds.size
//                )

                val previewBracket = rounds[pager]

                BracketColumnView(
                    bracket = previewBracket,
                    columnIndex = pager,
                    focusedColumnIndex = pagerState.currentPage,
                    lastColumnIndex = pagerState.pageCount - 1
                )

            }
        }
    }
}