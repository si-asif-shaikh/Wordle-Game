package com.uefa.wordle.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.uefa.wordle.theming.Theme


@Composable
internal fun InstructionsDialog(onDismiss: () -> Unit) {


    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false,
            securePolicy = SecureFlagPolicy.SecureOff,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
                .clickable(
                    onClick = onDismiss,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() })
                .padding(20.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "How to Play",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 10.dp),
                    color = Theme.colors.neutral.text01
                )

                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(
                            Theme.colors.elevation.elevation01,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp,
                    backgroundColor = Theme.colors.elevation.elevation01
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {

                        ItemInstruction(
                            letter = "A",
                            boxColor = Theme.colors.support.support01,
                            title = "Correct Place",
                            sub_title = "The letter is in the player's\nname and correct order."
                        )

                        ItemInstruction(
                            letter = "B",
                            boxColor = Theme.colors.support.support03A,
                            title = "In the name",
                            sub_title = "The letter is in the player's\nname but incorrect order."
                        )

                        ItemInstruction(
                            letter = "C",
                            boxColor = Theme.colors.elevation.elevation02,
                            title = "Wrong letter",
                            sub_title = "The letter is not in the player's name."
                        )
                    }
                }
            }

        }
    }
}

@Composable
internal fun ItemInstruction(
    letter: String,
    boxColor: Color,
    title: String,
    sub_title: String
) {
    Row(
        modifier = Modifier.padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(55.dp)
                .height(60.dp)
                .padding(10.dp)
                .background(boxColor, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                letter,
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                title,
                modifier = Modifier.padding(bottom = 4.dp),
                color = Theme.colors.neutral.text01,
                fontSize = 18.sp
            )
            Text(
                sub_title,
                fontSize = 12.sp,
                color = Theme.colors.neutral.text01.copy(
                    0.50f
                )
            )
        }
    }

}