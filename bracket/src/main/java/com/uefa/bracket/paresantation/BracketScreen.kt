@file:OptIn(ExperimentalFoundationApi::class)

package com.uefa.bracket.paresantation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

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
    BracketsList(state.rounds)
}

@Preview
@Composable
private fun BracketMatchScreenPreview() {
    BracketMatchScreen(
        state = BracketMatchContract.State(),
        onAction = {}
    )
}