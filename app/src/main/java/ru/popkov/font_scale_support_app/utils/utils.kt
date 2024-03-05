package ru.popkov.font_scale_support_app.utils

import android.content.Context
import android.content.res.Resources
import android.text.SpannableString
import android.text.style.BulletSpan
import com.google.android.material.color.MaterialColors
import ru.popkov.font_scale_support_app.models.SubscriptionType

const val SINGLE_LINE_MAX_LENGTH = 42

fun getSubscriptionAmountByType(subscriptionType: SubscriptionType) = when (subscriptionType) {
    SubscriptionType.MONTH -> 1
    else -> 12
}

fun getColorOnSecondaryContainer(context: Context): Int {
    return MaterialColors.getColor(
        context,
        com.google.android.material.R.attr.colorOnSecondaryContainer,
        "colorOnSecondaryContainer"
    )
}

fun getColorPrimary(context: Context): Int {
    return MaterialColors.getColor(
        context,
        com.google.android.material.R.attr.colorPrimary,
        "colorPrimary"
    )
}

fun List<String>.toBulletedList(context: Context): CharSequence {
    val bulletColor = getColorOnSecondaryContainer(context)
    return SpannableString(joinToString("\n")).apply {
        this@toBulletedList.foldIndexed(0) { index, acc, span ->
            val end = acc + span.length + if (index != this@toBulletedList.size - 1) 1 else 0
            setSpan(BulletSpan(10.dp, bulletColor, 2.dp), acc, end, 0)
            end
        }
    }
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
