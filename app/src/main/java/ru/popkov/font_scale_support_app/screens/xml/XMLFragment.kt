package ru.popkov.font_scale_support_app.screens.xml

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.popkov.font_scale_support_app.base.MainActivity
import ru.popkov.font_scale_support_app.base.SubscriptionsViewModel
import ru.popkov.font_scale_support_app.common.xml.CardItemView
import ru.popkov.font_scale_support_app.databinding.FragmentMainBinding
import ru.popkov.font_scale_support_app.utils.getColorPrimary


private const val GOOGLE_URL = "https://www.google.com"

class XMLFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val subscriptionsViewModel by viewModels<SubscriptionsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        lifecycleScope.launch {
            subscriptionsViewModel.subscriptionsData.collectLatest { data ->
                val selectedCardIndex = data.selectedCardIndex
                val mockData = data.subscriptionModel

                binding.subscriptionsContainer.apply {
                    removeAllViews()
                    mockData.forEachIndexed { index, subscription ->
                        val cardView = CardItemView(context, null)
                        cardView.bindData(
                            subscription = subscription,
                            isSelected = index == selectedCardIndex
                        ) {
                            subscriptionsViewModel.setSelectedCardIndex(index)
                        }
                        addView(cardView)
                    }
                }
            }
        }

        binding.privacyPolicy.apply {
            movementMethod = LinkMovementMethod.getInstance()
            text = buildSpannedString {
                append("${getString(ru.popkov.font_scale_support_app.R.string.decline_subscription)} ")
                inSpans(
                    span = object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_URL))
                            context?.startActivity(intent)
                        }

                        override fun updateDrawState(textPaint: TextPaint) {
                            textPaint.color = getColorPrimary(context)
                        }
                    },
                    builderAction = {
                        append(getString(ru.popkov.font_scale_support_app.R.string.decline_subscription_description_first))
                    }
                )
                append(" ${getString(ru.popkov.font_scale_support_app.R.string.decline_subscription_and)} ")
                inSpans(
                    span = object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_URL))
                            context?.startActivity(intent)
                        }

                        override fun updateDrawState(textPaint: TextPaint) {
                            textPaint.color = getColorPrimary(context)
                        }
                    },
                    builderAction = {
                        append(getString(ru.popkov.font_scale_support_app.R.string.decline_subscription_description_second))
                    }
                )
            }
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            context?.startActivity(intent)
        }

        return binding.root
    }
}