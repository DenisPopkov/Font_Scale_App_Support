package ru.popkov.font_scale_support_app.models

enum class SubscriptionType {
    MONTH,
    ANNUAL,
    ;
}

data class SubscriptionModel(
    val subscriptionPrice: Int = 0,
    val subscriptionType: SubscriptionType = SubscriptionType.MONTH,
    val subscriptionOfferBulletList: List<Int>,
)