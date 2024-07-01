package com.uefa.wordle.theming

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import com.uefa.wordle.theming.ui.core.Colors
import com.uefa.wordle.theming.ui.core.Sizes
import com.uefa.wordle.theming.ui.core.Theme
import com.uefa.wordle.theming.ui.core.Typography

//private val font = FontFamily(
//    Font(R.font., FontWeight.Normal, FontStyle.Normal),
//    Font(R.font., FontWeight.Bold, FontStyle.Normal),
//)

@Suppress("unused")
val Theme =  Theme(
    colors = Colors(
        base = Colors.Base(
            primary01 = Color(0xFF00004B),
            primary02 = Color(0xFF61619F),
            primary03 = Color(0xFF363683),
            primary04 = Color(0xFF010141),
            secondary01 = Color(0xFF0D3AFF),
            secondary02 = Color(0xFF0929C9),
            secondary03 = Color(0xFF061DA5),
            secondary04 = Color(0xFF041181),
            tertiary01 = Color(0xFF5AF7DC),
            tertiary02 = Color(0xFFA0FBEB),
            tertiary03 = Color(0xFF74F6E0),
            tertiary04 = Color(0xFF22E3C2),
            accent01 = Color(0xFFFF16FF),
            accent02 = Color(0xFFFC84FC),
            accent03 = Color(0xFFCC05CC),
            accentAll01 = Color(0xFFFFFFFF),
            accentAll02 = Color(0xFFFFFFFF),
            accentAll03 = Color(0xFFFFFFFF),
            interaction = Color(0xFF5AF7DC),
            interactionCompl = Color(0xFF00004B),
            interactionHighContrast = Color(0xFFFFFFFF),
        ),
        neutral = Colors.Neutral.Dark(
            text05 = Color(0xFF000041),
        ),
        data = Colors.Data(
            data01 = Color(0xFF5AF7DC),
            data02 = Color(0xFF4063FF),
            data03 = Color(0xFFFFFFFF),
            data04 = Color(0xFFFF16FF),
        ),
        elevation = Colors.Elevation(
            background = Color(0xFF000040),
            elevation01 = Color(0xFF0A0A61),
            elevation02 = Color(0xFF151573),
            elevation03 = Color(0xFF17177A),
            elevation01HighContrast = Color(0x80000040),
        ),
        support = Colors.Support(
            support01 = Color(0xFF32A72C),
            support01A = Color(0xFF7CCB70),
            support02 = Color(0xFF0085E5),
            support02A = Color(0xFF35B5EC),
            support03A = Color(0xFFD68D45),
            support04A = Color(0xFFD96464),
        ),
        header = Color(0xFF010141),
        instagramColors = Colors.InstagramColors(
            top = Color(0xFF0D3AFF),
            bottom = Color(0xFF061DA5),
        ),
    ),
    typography = Typography(),
    sizes = Sizes(
        splashTopBias = 0.26f,
        splashSponsorTopBias = 0.074f
    ),
)

