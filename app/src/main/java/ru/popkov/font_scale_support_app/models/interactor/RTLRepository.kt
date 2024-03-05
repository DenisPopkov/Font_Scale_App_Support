package ru.popkov.font_scale_support_app.models.interactor

import ru.popkov.font_scale_support_app.R
import ru.popkov.font_scale_support_app.models.SubscriptionModel
import ru.popkov.font_scale_support_app.models.SubscriptionType

class RTLRepository {
    fun loadRTLSubscriptions(): List<SubscriptionModel> {
        return gameDetailMock
    }
}

val gameDetailMock = listOf(
    SubscriptionModel(
        subscriptionPrice = 189,
        subscriptionType = SubscriptionType.MONTH,
        subscriptionOfferBulletList = listOf(
            R.string.subscription_description_first_point,
            R.string.subscription_description_second_point,
            R.string.subscription_description_third_point,
        ),
    ),
    SubscriptionModel(
        subscriptionPrice = 1890,
        subscriptionType = SubscriptionType.ANNUAL,
        subscriptionOfferBulletList = listOf(
            R.string.subscription_description_first_point,
            R.string.subscription_description_second_point,
            R.string.subscription_description_third_point,
        ),
    )
)