package ru.popkov.font_scale_support_app.common.compose

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import ru.popkov.font_scale_support_app.R
import ru.popkov.font_scale_support_app.ui.theme.GeometriaTextRegularWithLineHeight16

@Composable
fun SubscriptionOffer() {
    val uriHandler = LocalUriHandler.current
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
            append(text = "${stringResource(id = R.string.decline_subscription)} ")
        }

        pushStringAnnotation(tag = "policy", annotation = "https://google.com/policy")
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append(text = stringResource(id = R.string.decline_subscription_description_first))
        }
        pop()

        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
            append(text = " ${stringResource(id = R.string.decline_subscription_and)} ")
        }

        pushStringAnnotation(tag = "terms", annotation = "https://google.com/terms")

        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append(text = stringResource(id = R.string.decline_subscription_description_second))
        }
        pop()
    }

    ClickableText(
        text = annotatedString,
        style = GeometriaTextRegularWithLineHeight16,
        onClick = { offset ->
        annotatedString.getStringAnnotations(tag = "policy", start = offset, end = offset)
            .firstOrNull()?.let { uriHandler.openUri(it.item) }

        annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset)
            .firstOrNull()?.let { uriHandler.openUri(it.item) }
    })
}