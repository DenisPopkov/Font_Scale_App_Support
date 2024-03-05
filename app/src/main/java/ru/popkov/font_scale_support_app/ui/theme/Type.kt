package ru.popkov.font_scale_support_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.popkov.font_scale_support_app.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val GeometriaTextBold28 = TextStyle(
    fontSize = 28.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = FontFamily(Font(R.font.geometria_bold)),
)

val GeometriaTextBold20 = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = FontFamily(Font(R.font.geometria_bold)),
)

val GeometriaTextRegular20 = TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily(Font(R.font.geometria_normal)),
)

val GeometriaTextRegular16 = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily(Font(R.font.geometria_normal)),
)

val GeometriaTextRegularWithLineHeight16 = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily(Font(R.font.geometria_normal)),
    lineHeight = 22.sp
)

val GeometriaTextMedium14 = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily(Font(R.font.geometria_medium)),
)

val GeometriaTextRegular12 = TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily(Font(R.font.geometria_normal)),
)