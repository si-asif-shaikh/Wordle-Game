package com.uefa.wordle.theming.ui.core

import androidx.compose.ui.graphics.Color

data class Colors(
    val base: Base = Base(),
    val neutral: Neutral = Neutral.Dark(),
    val data: Data = Data(),
    val elevation: Elevation = Elevation(),
    val support: Support = Support(),
    val custom: Custom = object : Custom {},
    val header: Color = Color.Transparent,
    val instagramColors: InstagramColors = InstagramColors(),
){
    data class Base(
        val primary01: Color = Color.Transparent,
        val primary02: Color = Color.Transparent,
        val primary03: Color = Color.Transparent,
        val primary04: Color = Color.Transparent,
        val secondary01: Color = Color.Transparent,
        val secondary02: Color = Color.Transparent,
        val secondary03: Color = Color.Transparent,
        val secondary04: Color = Color.Transparent,
        val tertiary01: Color = Color.Transparent,
        val tertiary02: Color = Color.Transparent,
        val tertiary03: Color = Color.Transparent,
        val tertiary04: Color = Color.Transparent,
        val accent01: Color = Color.Transparent,
        val accent02: Color = Color.Transparent,
        val accent03: Color = Color.Transparent,
        val accentAll01: Color = Color.Transparent,
        val accentAll02: Color = Color.Transparent,
        val accentAll03: Color = Color.Transparent,
        val interaction: Color = Color.Transparent,
        val interactionCompl: Color = Color.Transparent,
        val interactionHighContrast: Color = Color.Transparent,
        val interactionSecondary: Color = Color.Transparent,
    )

    interface Neutral {
        val text01: Color
        val text02: Color
        val text03: Color
        val text04: Color
        val text05: Color
        val ui00: Color
        val ui01: Color
        val ui02: Color
        val ui03: Color
        val ui04: Color
        val ui05: Color
        val ui06: Color

        data class Dark(
            override val text01: Color = Color(0xFFFFFFFF),
            override val text02: Color = Color(0xB2FFFFFF),
            override val text03: Color = Color(0x80FFFFFF),
            override val text04: Color = Color(0xFFFFFFFF),
            override val text05: Color = Color(0xFF000000),
            override val ui00: Color = Color(0x0DFFFFFF),
            override val ui01: Color = Color(0x1AFFFFFF),
            override val ui02: Color = Color(0x40FFFFFF),
            override val ui03: Color = Color(0x59FFFFFF),
            override val ui04: Color = Color(0x8CFFFFFF),
            override val ui05: Color = Color(0xCCFFFFFF),
            override val ui06: Color = Color(0xFFFFFFFF),
        ) : Neutral

        data class Light(
            override val text01: Color = Color(0xFF2C2C2C),
            override val text02: Color = Color(0xFF6C6C6C),
            override val text03: Color = Color(0xFF9C9C9C),
            override val text04: Color = Color(0xFFFFFFFF),
            override val text05: Color = Color(0xFF2C2C2C),
            override val ui00: Color = Color(0xFFFFFFFF),
            override val ui01: Color = Color(0xFFEFEFEF),
            override val ui02: Color = Color(0xFFDDDDDD),
            override val ui03: Color = Color(0xFFC9C9C9),
            override val ui04: Color = Color(0xFFBBBBBB),
            override val ui05: Color = Color(0xFF2C2C2C),
            override val ui06: Color = Color(0xFF000000),
        ) : Neutral
    }

    data class Data(
        val data01: Color = Color.Transparent,
        val data02: Color = Color.Transparent,
        val data03: Color = Color.Transparent,
        val data04: Color = Color.Transparent,
    )

    data class Elevation(
        val background: Color = Color.Transparent,
        val elevation01: Color = Color.Transparent,
        val elevation02: Color = Color.Transparent,
        val elevation03: Color = Color.Transparent,
        val elevation01HighContrast: Color = Color.Transparent,
    )

    data class Support(
        val support01: Color = Color(0xFF0A9504),
        val support01A: Color = Color(0xFF19C201),
        val support01B: Color = Color(0xFFCFFECD), // 70ED5E
        val support02: Color = Color(0xFF205BC8),
        val support02A: Color = Color(0xFF009FE3),
        val support02B: Color = Color(0xFFC1ECFF), // 47C6FD
        val support03: Color = Color(0xFFDF6817), // E87223
        val support03A: Color = Color(0xFFFF9933),
        val support03B: Color = Color(0xFFF8D5BD), // FFB974
        val support04: Color = Color(0xFFCB333B),
        val support04A: Color = Color(0xFFFF3B3B),
        val support04B: Color = Color(0xFFF1C8CA), // FF8787
        val support05: Color = Color(0xFFFFCD44),
        val support05A: Color = Color(0xFFFFD970), // FFDC7C
        val support05B: Color = Color(0xFFFFEDBB), // FFE7A7
    )

    data class InstagramColors (
        val top: Color = Color.Transparent,
        val bottom: Color = Color.Transparent,
    )

    interface Custom
}
