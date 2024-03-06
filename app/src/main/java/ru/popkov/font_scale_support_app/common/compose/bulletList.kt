package ru.popkov.font_scale_support_app.common.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import ru.popkov.font_scale_support_app.ui.theme.RTLSupportAppTheme

@Suppress("NOTHING_TO_INLINE")
@Stable
inline fun <T> T.toImmutableWrapper(): ImmutableWrapper<T> = ImmutableWrapper(this)

@Immutable
data class ImmutableWrapper<T>(val value: T)

@Composable
fun Int.toSp() = with(LocalDensity.current) { this@toSp.toSp() }

fun String.bulletWithHighlightedTxtAnnotatedString(
    bullet: String,
    restLine: TextUnit,
    highlightedTxt: String,
) = buildAnnotatedString {
    split("\n").forEach {
        var txt = it.trim()
        if (txt.isNotBlank()) {
            withStyle(style = ParagraphStyle(textIndent = TextIndent(restLine = restLine))) {
                append(bullet)
                if (highlightedTxt.isNotEmpty()) {
                    while (true) {
                        val i = txt.indexOf(string = highlightedTxt, ignoreCase = true)
                        if (i == -1) break
                        append(txt.subSequence(startIndex = 0, endIndex = i).toString())
                        val j = i + highlightedTxt.length
                        withStyle(style = SpanStyle()) {
                            append(txt.subSequence(startIndex = i, endIndex = j).toString())
                        }
                        txt = txt.subSequence(startIndex = j, endIndex = txt.length).toString()
                    }
                }
                append(txt)
            }
        }
    }
}

@Composable
fun BulletText(
    text: String,
    modifier: Modifier = Modifier,
    bullet: String = "\u2022\u00A0\u00A0",
    highlightedText: String = "",
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    inlineContent: ImmutableWrapper<Map<String, InlineTextContent>> = mapOf<String, InlineTextContent>().toImmutableWrapper(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
) {
    val restLine = run {
        val textMeasurer = rememberTextMeasurer()
        remember(bullet, style, textMeasurer) {
            textMeasurer.measure(text = bullet, style = style).size.width
        }.toSp()
    }
    Text(
        text = remember(text, bullet, restLine, highlightedText) {
            text.bulletWithHighlightedTxtAnnotatedString(
                bullet = bullet,
                restLine = restLine,
                highlightedTxt = highlightedText,
            )
        },
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        inlineContent = inlineContent.value,
        onTextLayout = onTextLayout,
        style = style,
        modifier = modifier,
    )
}

@Preview
@Composable
fun PreviewBulletText() {
    RTLSupportAppTheme {
        Surface {
            BulletText(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                        "\nEtiam lipsums et metus vel mauris scelerisque molestie eget nec ligula." +
                        "\nNulla scelerisque, magna id aliquam rhoncus, ipsumx turpis risus sodales mi, sit ipsum amet malesuada nibh lacus sit amet libero." +
                        "\nCras in sem euismod, vulputate ligula in, egestas enim ipsum.",
                modifier = Modifier.padding(8.dp),
                overflow = TextOverflow.Ellipsis,
                onTextLayout = {},
            )
        }
    }
}