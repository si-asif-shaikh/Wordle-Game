package com.uefa.wordle.theming.ui.core

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.DeviceFontFamilyName
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

private val fontCondensed =
    Font(DeviceFontFamilyName("sans-serif-condensed")).toFontFamily()


@Suppress("PropertyName")
data class Typography(
    val XL7: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 96.sp,
        lineHeight = 1.1.em,
        letterSpacing = (-1.2).sp
    ),

    val XL6R: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 72.sp,
        lineHeight = 1.1.em,
        letterSpacing = (-0.5).sp
    ),
    val XL6B: TextStyle = XL6R.copy(fontWeight = FontWeight.Bold),

    val XL5: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 60.sp,
        lineHeight = 1.1.em,
        letterSpacing = (-0.5).sp
    ),

    val XL4R: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        lineHeight = 1.1.em,
        letterSpacing = (-0.5).sp
    ),
    val XL4M: TextStyle = XL4R.copy(fontWeight = FontWeight.Medium),
    val XL4B: TextStyle = XL4R.copy(fontWeight = FontWeight.Bold),
    val XL4C: TextStyle = XL4B.copy(fontFamily = fontCondensed),

    val XL3R: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        lineHeight = 1.1.em,
        letterSpacing = (-0.5).sp
    ),
    val XL3M: TextStyle = XL3R.copy(fontWeight = FontWeight.Medium),
    val XL3B: TextStyle = XL3R.copy(fontWeight = FontWeight.Bold),
    val XL3C: TextStyle = XL3B.copy(fontFamily = fontCondensed),

    val XL2R: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.5).sp
    ),
    val XL2M: TextStyle = XL2R.copy(fontWeight = FontWeight.Medium),
    val XL2B: TextStyle = XL2R.copy(fontWeight = FontWeight.Bold),
    val XL2C: TextStyle = XL2M.copy(fontFamily = fontCondensed),

    val XLR: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.25).sp
    ),
    val XLM: TextStyle = XLR.copy(fontWeight = FontWeight.Medium),
    val XLB: TextStyle = XLR.copy(fontWeight = FontWeight.Bold),
    val XLC: TextStyle = XLM.copy(fontFamily = fontCondensed),

    val LR: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 1.3.em,
        letterSpacing = (-0.1).sp
    ),
    val LM: TextStyle = LR.copy(fontWeight = FontWeight.Medium),
    val LB: TextStyle = LR.copy(fontWeight = FontWeight.Bold),
    val LC: TextStyle = LM.copy(fontFamily = fontCondensed),

    val MR: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 1.3.em,
    ),
    val MM: TextStyle = MR.copy(fontWeight = FontWeight.Medium),
    val MB: TextStyle = MR.copy(fontWeight = FontWeight.Bold),
    val MC: TextStyle = MM.copy(fontFamily = fontCondensed),

    val SR: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 1.3.em,
    ),
    val SM: TextStyle = SR.copy(fontWeight = FontWeight.Medium),
    val SB: TextStyle = SR.copy(fontWeight = FontWeight.Bold),
    val SC: TextStyle = SM.copy(fontFamily = fontCondensed),

    val XSR: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 1.3.em,
    ),
    val XSM: TextStyle = XSR.copy(fontWeight = FontWeight.Medium),
    val XSB: TextStyle = XSR.copy(fontWeight = FontWeight.Bold),
    val XSC: TextStyle = XSM.copy(fontFamily = fontCondensed),

    val XS2: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 1.3.em
    ),

    val XS3: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        lineHeight = 1.3.em
    ),

    val HeaderTitle: TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.4).sp
    ),
    val HeaderTitleExpanded: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 42.sp,
        letterSpacing = (-0.5).sp
    ),
    val SplashTitle: TextStyle = TextStyle(),
    val custom: Custom = object : Custom { }
) {

    interface Custom
}