package ru.popkov.font_scale_support_app.common.xml

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import ru.popkov.font_scale_support_app.R
import ru.popkov.font_scale_support_app.models.SubscriptionModel
import ru.popkov.font_scale_support_app.models.SubscriptionType
import ru.popkov.font_scale_support_app.utils.dp
import ru.popkov.font_scale_support_app.utils.getSubscriptionAmountByType
import ru.popkov.font_scale_support_app.utils.toBulletedList
import java.text.NumberFormat
import java.util.Locale

class CardItemView(
    context: Context,
    attrs: AttributeSet?,
) : ConstraintLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.item_card, this).setPadding(0, 0, 0, 12.dp)
    }

    fun bindData(
        subscription: SubscriptionModel,
        isSelected: Boolean,
        onClickListener: () -> Unit
    ) {
        val card = findViewById<ConstraintLayout>(R.id.root)
        val priceLabel = findViewById<TextView>(R.id.priceLabel)
        val monthLabel = findViewById<TextView>(R.id.monthLabel)
        val amountDescription = findViewById<TextView>(R.id.subscriptionSubtitle)
        val popularLabel = findViewById<TextView>(R.id.subscriptionPopularLabel)
        val subscriptionDescription = findViewById<TextView>(R.id.subscriptionDescription)
        val monthAmount = getSubscriptionAmountByType(subscription.subscriptionType)

        priceLabel.text = "${
            context.getString(
                R.string.subscription_price,
                subscription.subscriptionPrice.toString(),
            )
        } "

        monthLabel.text = "/ ${
            context.resources.getQuantityString(
                R.plurals.month,
                monthAmount,
                NumberFormat.getInstance(Locale.getDefault()).format(monthAmount),
            ).trim()
        }"

        amountDescription.text = context.resources.getQuantityString(
            R.plurals.money_withdraw,
            monthAmount,
            monthAmount,
        ).trim()
        val bulletList = subscription.subscriptionOfferBulletList.map { context.getString(it) }
        subscriptionDescription.text = bulletList.toBulletedList(context)
        popularLabel.isInvisible = subscription.subscriptionType != SubscriptionType.MONTH
        card.isSelected = isSelected

        card.setOnClickListener {
            onClickListener.invoke()
        }
    }
}