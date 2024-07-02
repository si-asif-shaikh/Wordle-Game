package com.uefa.gaminghub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uefa.wordle.theming.Theme
import com.uefa.gaminghub.ui.theme.GaminghubTheme
import com.uefa.wordle.presentation.WordleGame
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaminghubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var openWordleGame by remember {
                        mutableStateOf(false)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        DefaultCard(
                            modifier = Modifier
                                .padding(10.dp),
                            onButtonClick = {
                                openWordleGame = true
                            },
                            imageUrl = "",
                            title = "Wordle",
                            subTitle = "Wordle Game",
                            buttonText = "Start"
                        )
                    }

                    if (openWordleGame) {
                        WordleGame()
//                            openWordleGame = false
                    }

                }
            }
        }
    }
}

@Composable
internal fun DefaultCard(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    imageUrl: String,
    title: String,
    subTitle: String,
    buttonText: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Theme.colors.elevation.elevation01)
                .clickable {
                    onButtonClick.invoke()
                },
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {

                Box(
                    modifier = Modifier
                        .aspectRatio(16f / 9f)
                        .background(Color.White)
                        .drawWithCache {

                            val startY = size.height * 0.5f
                            val endY = size.height

                            val gradient = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(0.4f)),
                                startY = startY,
                                endY = endY
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.Multiply)
                            }
                        },
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 20.dp),
                ) {

                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 21.sp,
                            fontWeight = FontWeight(600),
                            color = Theme.colors.neutral.text01,
                        )
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 15.dp),
                        text = subTitle,
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 26.sp,
                            fontWeight = FontWeight(600),
                            color = Theme.colors.neutral.text01,
                        )
                    )

                    val buttonStyle = Modifier
                        .background(
                            color = Theme.colors.base.interaction,
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(start = 16.dp, end = 16.dp)

                    if (buttonText.isNotEmpty())
                        Box(
                            modifier = buttonStyle
                                .heightIn(min = 32.dp)
                                .clickable { onButtonClick.invoke() },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = buttonText,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 15.4.sp,
                                    fontWeight = FontWeight(600),
                                    color = Theme.colors.elevation.background,
                                )
                            )
                        }
                }

            }

        }
    }
}