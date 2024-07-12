@file:OptIn(ExperimentalFoundationApi::class)

package com.uefa.bracket.paresantation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BracketScreen(){
    BracketMatchScreenRoot()
}

@Composable
internal fun BracketMatchScreenRoot(
    viewModel: BracketMatchViewModel = hiltViewModel(),
) {
    BracketMatchScreen(
        state = viewModel.uiState,
        onAction = viewModel::setEvent
    )
}

@Composable
private fun BracketMatchScreen(
    state: BracketMatchContract.State,
    onAction: (BracketMatchContract.Event) -> Unit
) {
    BracketsList(state.brackets)
}

@Preview
@Composable
private fun BracketMatchScreenPreview() {
    BracketMatchScreen(
        state = BracketMatchContract.State(),
        onAction = {}
    )
}