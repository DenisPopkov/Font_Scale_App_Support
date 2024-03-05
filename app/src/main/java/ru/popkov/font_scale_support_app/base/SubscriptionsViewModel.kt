package ru.popkov.font_scale_support_app.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.popkov.font_scale_support_app.models.SubscriptionModelState
import ru.popkov.font_scale_support_app.models.interactor.RTLRepository

class SubscriptionsViewModel(
    private val rtlRepository: RTLRepository = RTLRepository()
) : ViewModel() {

    private val _subscriptionsData = MutableStateFlow(SubscriptionModelState())
    val subscriptionsData: StateFlow<SubscriptionModelState> = _subscriptionsData

    init {
        getSubscriptions()
    }

    fun setSelectedCardIndex(index: Int) {
        _subscriptionsData.value = _subscriptionsData.value.copy(selectedCardIndex = index)
    }

    private fun getSubscriptions() {
        viewModelScope.launch {
            try {
                _subscriptionsData.value = subscriptionsData.value.copy(isLoading = true)
                _subscriptionsData.value = _subscriptionsData.value.copy(
                    subscriptionModel = rtlRepository.loadRTLSubscriptions(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _subscriptionsData.value = subscriptionsData.value.copy(isLoading = false)
            }
        }
    }
}