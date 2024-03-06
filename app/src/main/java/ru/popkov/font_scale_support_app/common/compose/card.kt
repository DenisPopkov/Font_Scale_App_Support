package ru.popkov.font_scale_support_app.common.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.popkov.font_scale_support_app.R
import ru.popkov.font_scale_support_app.base.SubscriptionsViewModel
import ru.popkov.font_scale_support_app.models.SubscriptionModel
import ru.popkov.font_scale_support_app.models.SubscriptionType
import ru.popkov.font_scale_support_app.screens.compose.AutoResizeText
import ru.popkov.font_scale_support_app.screens.compose.FontSizeRange
import ru.popkov.font_scale_support_app.ui.theme.GeometriaTextBold20
import ru.popkov.font_scale_support_app.ui.theme.GeometriaTextMedium14
import ru.popkov.font_scale_support_app.ui.theme.GeometriaTextRegular12
import ru.popkov.font_scale_support_app.ui.theme.GeometriaTextRegular20
import ru.popkov.font_scale_support_app.ui.theme.RTLSupportAppTheme
import ru.popkov.font_scale_support_app.utils.getSubscriptionAmountByType
import java.text.NumberFormat
import java.util.Locale

@Composable
fun RTLSubscriptionCard(
    modifier: Modifier = Modifier,
    subscriptionData: SubscriptionModel,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {
    val subscriptionAmount = getSubscriptionAmountByType(subscriptionData.subscriptionType)
    val textColor = MaterialTheme.colorScheme.onSecondaryContainer

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(size = 2.dp)
            )
            .border(
                width = 2.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(size = 2.dp)
            )
            .padding(horizontal = 20.dp)
            .padding(bottom = 16.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick.invoke()
            },
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Row {
                Text(
                    text = "${
                        stringResource(
                            id = R.string.subscription_price,
                            subscriptionData.subscriptionPrice
                        )
                    } ",
                    style = GeometriaTextBold20,
                    color = textColor,
                )
                Text(
                    text = "/ ",
                    style = GeometriaTextRegular20,
                    color = textColor,
                )
                Text(
                    text = pluralStringResource(
                        id = R.plurals.month,
                        count = subscriptionAmount,
                        NumberFormat.getInstance(Locale.getDefault()).format(subscriptionAmount),
                    ).trim(),
                    style = GeometriaTextRegular20,
                    color = textColor,
                )
            }
            Box(
                modifier = modifier
                    .width(width = 100.dp)
                    .alpha(alpha = if (subscriptionData.subscriptionType == SubscriptionType.MONTH) 100f else 0f)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(size = 2.dp)
                    )
                    .padding(vertical = 10.dp, horizontal = 15.dp)
                    .align(alignment = Alignment.CenterVertically),
            ) {
                AutoResizeText(
                    text = stringResource(id = R.string.popular_label),
                    style = GeometriaTextRegular12,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSizeRange = FontSizeRange(
                        min = 12.sp,
                        max = 14.sp,
                    ),
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Text(
            modifier = modifier.padding(vertical = 12.dp),
            text = pluralStringResource(
                id = R.plurals.money_withdraw,
                count = subscriptionAmount,
                NumberFormat.getInstance(Locale.getDefault()).format(subscriptionAmount),
            ).trim(),
            style = GeometriaTextMedium14,
            color = MaterialTheme.colorScheme.secondary
        )

        val bulletList =
            subscriptionData.subscriptionOfferBulletList.map { stringResource(id = it) }

        BulletText(
            text = bulletList.joinToString("\n"),
            modifier = Modifier.padding(8.dp),
            overflow = TextOverflow.Ellipsis,
            onTextLayout = {},
        )
    }
}

@Preview(showBackground = true)
@PreviewFontScale
@Composable
private fun RTLSubscriptionCardPreview(
    subscriptionsViewModel: SubscriptionsViewModel = viewModel()
) {
    RTLSupportAppTheme {
        Surface {
            val subscriptions = subscriptionsViewModel.subscriptionsData.collectAsState()
            val subscriptionData = subscriptions.value
            RTLSubscriptionCard(
                subscriptionData = subscriptionData.subscriptionModel.first(),
            )
        }
    }
}