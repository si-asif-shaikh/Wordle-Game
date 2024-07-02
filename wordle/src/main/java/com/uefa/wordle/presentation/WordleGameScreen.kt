package com.uefa.wordle.presentation

import android.app.GameState
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uefa.gaminghub.R
import com.uefa.wordle.core.presentation.TopAppToolbar
import com.uefa.wordle.theming.Theme

object WordleGame {
    @Composable
    fun open() {
        WordleGameScreenRoot()
    }
}

@Composable
internal fun WordleGameScreenRoot(
    viewModel: WordleGameViewModel = hiltViewModel(),
) {
    WordleGameScreen(
        state = viewModel.uiState,
        onAction = viewModel::setEvent
    )
}

@Composable
private fun WordleGameScreen(
    state: WordleGameContract.State,
    onAction: (WordleGameContract.Event) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppToolbar(
                title = {
                    Text(
                        text = "Wordle",
                        fontSize = 17.sp,
                        style = Theme.typography.MM.copy(
                            color = Theme.colors.neutral.text01,
                            fontSize = 17.sp
                        )
                    )
                },
                navigationIcon = {
                },
                actionIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {

                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {


        var showInstructions by remember { mutableStateOf(true) }

        if (showInstructions) {
            InstructionsDialog(onDismiss = { showInstructions = false })
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Theme.colors.elevation.background)
        ) {
            GuessBoard(state)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                GridKeyboard(
                    keyboardState = state.keyboardState,
                    onKeyPress = { letter ->
                        if (state.currentGuess.length < state.targetWord.length) {
                            onAction(WordleGameContract.Event.OnLetterEnter(letter))
                        }
                    },
                    onBackspacePress = {
                        onAction(WordleGameContract.Event.OnBackPressed)
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    state.boosterList.forEach {
                        Column(
                            modifier = Modifier
                                .border(1.dp, Color.White, shape = RoundedCornerShape(10.dp))
                                .width(80.dp)
                                .height(50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Image(
                                modifier = Modifier.size(14.dp),
                                painter = painterResource(id = R.drawable.ic_booster_fifty_fifty),
                                contentDescription = ""
                            )

                            Text(
                                modifier = Modifier,
                                text = "Booster",
                                color = Color.White
                            )

                        }

                        Spacer(modifier = Modifier.width(10.dp))

                    }

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Theme.colors.base.interaction,
                            contentColor = Theme.colors.elevation.elevation02,
                            disabledBackgroundColor = Theme.colors.base.interaction.copy(
                                0.55f
                            ),
                            disabledContentColor = Theme.colors.elevation.elevation02.copy(
                                0.55f
                            )
                        ),
                        onClick = {
                            onAction(WordleGameContract.Event.CheckGuess)
                        },
                        enabled = state.isCheckEnable
                    ) {
                        Text(
                            text = "Submit",
                            color = Theme.colors.elevation.elevation02,
                            style = Theme.typography.MM.copy(
                                fontSize = 16.sp,
                                color = Theme.colors.elevation.background
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun WordleGameScreenPreview() {
    WordleGameScreen(
        state = WordleGameContract.State(),
        onAction = {}
    )
}

@Composable
internal fun GuessBoard(state: WordleGameContract.State) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        (0 until 5).forEach { rowIndex ->
            Row {
                (0 until state.targetWord.length).forEach { colIndex ->
                    val letter = getCurrentGridLetter(
                        gameState = state,
                        rowIndex = rowIndex,
                        colIndex = colIndex
                    )

                    val targetColor =
                        getLetterColor(state = state, rowIndex = rowIndex, letter = letter)
                    val isFlipped = state.guesses.size > rowIndex

                    FlipCard(
                        modifier = Modifier
                            .width(55.dp)
                            .height(60.dp)
                            .padding(5.dp)
                            .border(
                                1.dp,
                                if (!shouldHighlightBorder(
                                        gameState = state,
                                        rowIndex = rowIndex,
                                        colIndex = colIndex
                                    )
                                ) Color.Transparent
                                else Theme.colors.base.accent01,
                                shape = RoundedCornerShape(14.dp)
                            ),
                        cardColors = targetColor,
                        front = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    letter.toString(),
                                    fontSize = 24.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        },
                        back = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    letter.toString(),
                                    fontSize = 24.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        },
                        isFlipped = isFlipped
                    )
                }
            }
        }
    }
}

internal fun getCurrentGridLetter(
    gameState: WordleGameContract.State,
    rowIndex: Int,
    colIndex: Int
): Char {
    val isCurrentRow = rowIndex == gameState.guesses.size
    return if (isCurrentRow)
        gameState.currentGuess.getOrNull(colIndex) ?: ' '
    else gameState.guesses.getOrNull(rowIndex)?.getOrNull(colIndex) ?: ' '
}

internal fun shouldHighlightBorder(
    gameState: WordleGameContract.State,
    rowIndex: Int,
    colIndex: Int
): Boolean {
    return rowIndex == gameState.guesses.size && colIndex == gameState.currentGuess.length
}

internal fun shouldHighlightBackground(
    gameState: WordleGameContract.State,
    rowIndex: Int
): Boolean {
    return rowIndex < gameState.guesses.size
}

internal fun getLetterColor(state: WordleGameContract.State, rowIndex: Int, letter: Char): Color {
    return if (shouldHighlightBackground(
            gameState = state,
            rowIndex = rowIndex
        )
    ) when (state.keyboardState[letter]) {
        LetterStatus.CORRECT -> Color.Green
        LetterStatus.PRESENT -> Color.Yellow
        LetterStatus.ABSENT -> Theme.colors.elevation.elevation03
        else -> Theme.colors.base.primary02.copy(
            0.75f
        )
    } else Theme.colors.base.primary02.copy(
        0.75f
    )
}

internal fun getKeyboardColor(keyboardState: Map<Char, LetterStatus>, letter: Char): Color {
    return when (keyboardState[letter]) {
        LetterStatus.CORRECT -> Color.Green
        LetterStatus.PRESENT -> Color.Yellow
        else -> Color.Transparent
    }
}


@Composable
fun GridKeyboard(
    keyboardState: Map<Char, LetterStatus>,
    onKeyPress: (Char) -> Unit,
    onBackspacePress: () -> Unit
) {
    val keys = listOf(
        listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
        listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'),
        listOf('Z', 'X', 'C', 'V', 'B', 'N', 'M')
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Theme.colors.elevation.elevation01HighContrast)
    ) {

        Divider(color = Theme.colors.neutral.ui01)
        Column(
            modifier = Modifier.padding(top = 15.dp)
        ) {
            keys.forEachIndexed { index, row ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    row.forEach { letter ->
                        Box(
                            modifier = Modifier
                                .width(38.dp)
                                .height(53.dp)
                                .padding(1.dp)
                                .border(
                                    width = 1.dp,
                                    Color.White.copy(0.25f),
                                    RoundedCornerShape(14.dp)
                                )
                                .background(
                                    color = getKeyboardColor(keyboardState = keyboardState, letter),
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .clickable { onKeyPress(letter) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = letter.toString(),
                                fontSize = 24.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    if (keys.lastIndex == index) {

                        Box(
                            modifier = Modifier
                                .size(53.dp)
                                .padding(1.dp)
                                .border(
                                    width = 1.dp,
                                    Color.White.copy(0.25f),
                                    RoundedCornerShape(10.dp)
                                )
                                .clickable { onBackspacePress() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "",
                                tint = Color.White
                            )

                        }
                    }

                }
            }
        }
    }
}
