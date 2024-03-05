package ru.popkov.font_scale_support_app.common.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.popkov.font_scale_support_app.ui.theme.GeometriaTextMedium14

@Composable
fun BulletListText(
    textLine: String,
    isSingleLine: Boolean,
    textLineColor: Color,
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .padding(top = if (!isSingleLine) 6.dp else 0.dp)
            .size(4.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawCircle(color = textLineColor)
        }
    }

    Text(
        text = textLine,
        style = GeometriaTextMedium14,
        color = textLineColor,
        modifier = Modifier.padding(start = 10.dp),
    )
}