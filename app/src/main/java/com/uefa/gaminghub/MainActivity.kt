package com.uefa.gaminghub

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.uefa.wordle.theming.Theme
import com.uefa.gaminghub.ui.theme.GaminghubTheme
import com.uefa.wordle.CoreGame
import com.uefa.wordle.core.sdk.Wordle
import com.uefa.wordle.core.sdk.WordleListener
import com.uefa.wordle.wordlegame.presentation.WordleGame
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("cookie_prefs", Context.MODE_PRIVATE)
    }

    private val APP_TOKEN = "app_token"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appToken =
            "_USC=MndyOUVtWVNsRXhsbWNTR0VsK2xzUnFJVFMxNzdZbWtKY2MvdGZSMzgyb3QySXNxMHN2QXVDRlNPc1NrbmhDR3dtMm5laDV4Z1RzS01LVEpReU9OeUxSb1lNcS9YdlpkZkFXb0cwcG9STFQyVGRiNmd6cjZrSTJsQWpxa3JHTXhCY0FsaTNQc1lDT2xkZ05Ccjg0bnZNZHFDM2xyVWt0alAxYTBnVVFhaGx3OGtrOGVqdittYUJCQm1PMnJINXlJNm43TE5kVXdGb0Z0YUtkN2RUcm1Bc2tmc29tc2QyNkVaVlFZN3JUdVZqbVRRaUYvQ2N4aS9sZHVMbHFTZENkQUNpUGNIOGJKdHljeDFZZGNVeUowVFFyTTlyR2NSdDMzejNFUmtwdGd3RThXeU91M01kcnhvRjI3clhpbjBQb1VleU9LcktwN0NORU9LRE8vemQzaDBQa2lxZ1pQN2pRNExJRTRVZWFNRUdFdHBSL09TZDkzNUhNNHc0WVYxZHJYSjJSL1RpS3g5SjQ5QXAxNWNPRXY2ZnhkcURjS1hud1puejdjb1BSVnA3emIvWVdTYTNuZDZCWDM0NFBpcDMrQlo0dFRPYTl1T1dtRnMyMmxTdlZlbFYyTWU2VWFFVVFJUXFxQXdTYW1INWdOb01KZ0NRWnNweHgyRURURUloZ2dyQW10Qk9IVUZWWGRoTXRBQnY0L09sM3phaWFocnBoVWNXNHllTi95UDBNbGx6TXN3Ri80c29LVzFjWUJKOU9lR3NTZ1hQSXRhT1N1RzNCUXIyTDdrODk1c01NY08yVFRHQXhRbHRQbmRNWT0%3D; _URC=%7B%22user_guid%22%3A%220eb43fc3-8351-44ce-be21-5b0f9a1d3748-23072024024520452%22%2C%22name%22%3A%22%22%2C%22first_name%22%3A%22asif%22%2C%22last_name%22%3A%22sk%22%2C%22email_id%22%3Anull%2C%22is_first_login%22%3A%220%22%2C%22favourite_club%22%3A%22%22%2C%22edition%22%3A%22%22%2C%22status%22%3A%221%22%2C%22is_custom_image%22%3A%220%22%2C%22social_user_image%22%3A%22https%3A%2F%2Flh3.googleusercontent.com%2Fa%2FAEdFTp661JxEh-3OAW_6Gioo46BDXlil_qEOPXro83Kx%3Ds96-c%22%2C%22profile_completion_percentage%22%3Anull%2C%22client_id%22%3Anull%2C%22guid%22%3A%22C2B008CF70E95A08A96FBACE6463EFD4F2AD2042%22%2C%22waf_user_guid%22%3A%2260990b3b-7e4d-48ed-bb3b-d3b8acedf441%22%7D;"


        setContent {
            GaminghubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    var appToken by remember {
                        mutableStateOf(sharedPreferences.getString(APP_TOKEN, "").orEmpty())
                    }

                    LaunchedEffect(key1 = appToken) {
                        if (appToken.isNotEmpty())
                            Wordle.init(
                                context = applicationContext,
                                environment = Wordle.ENV_STG,
                                versionName = packageManager.getPackageInfo(
                                    packageName,
                                    0
                                ).versionName,
                                appToken = appToken,
                                listener = object : WordleListener {
                                    override fun openLoginPage(gameId: String) {

                                    }

                                    override fun closeGame() {
                                        navController.popBackStack()
                                    }

                                    override fun openRegistrationPage() {
                                    }

                                    override suspend fun onRequestRefreshToken(): String? {
                                        return ""
                                    }

                                    override fun openUri(uriPath: String) {
                                    }

                                }
                            )
                    }

                    NavHost(navController = navController, startDestination = "onboarding") {

                        navigation("card", "onboarding") {

                            composable("card") {

                                var token by remember {
                                    mutableStateOf("")
                                }

                                var openWordleGame by remember {
                                    mutableStateOf(false)
                                }

                                if (appToken.isEmpty()) {

                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(10.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {

                                        TextField(
                                            modifier = Modifier.heightIn(max = 250.dp),
                                            value = token,
                                            onValueChange = {
                                                token = it
                                            },
                                            placeholder = {
                                                Text(text = "Please submit USC and URC cookie")
                                            }
                                        )

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Button(onClick = {

                                            // Save cookies to SharedPreferences
                                            with(sharedPreferences.edit()) {
                                                putString(APP_TOKEN, token)
                                                apply()
                                            }

                                            appToken = token
                                        }) {
                                            Text(text = "Submit")
                                        }

                                    }

                                } else {
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
                                }

                                if (openWordleGame) {
                                    navController.navigate("wordleGame")
                                    openWordleGame = false
                                }

                            }

                            composable("wordleGame") {

                                CoreGame()
                            }

                        }

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