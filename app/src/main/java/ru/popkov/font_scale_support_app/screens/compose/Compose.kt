package ru.popkov.font_scale_support_app.screens.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.popkov.font_scale_support_app.R
import ru.popkov.font_scale_support_app.base.SubscriptionsViewModel
import ru.popkov.font_scale_support_app.common.compose.CommonButton
import ru.popkov.font_scale_support_app.common.compose.RTLSubscriptionCard
import ru.popkov.font_scale_support_app.common.compose.SubscriptionOffer
import ru.popkov.font_scale_support_app.ui.theme.GeometriaTextBold28
import ru.popkov.font_scale_support_app.ui.theme.GeometriaTextRegular16
import ru.popkov.font_scale_support_app.ui.theme.RTLSupportAppTheme

@Composable
fun ComposeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    subscriptionsViewModel: SubscriptionsViewModel = viewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.surface),
            ) {
                IconButton(
                    modifier = modifier
                        .align(Alignment.TopStart)
                        .padding(top = 12.dp),
                    onClick = { navController.navigateUp() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_left_arrow),
                        contentDescription = "Назад",
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
                Image(
                    modifier = modifier
                        .size(size = 90.dp)
                        .align(Alignment.BottomCenter)
                        .padding(top = 16.dp, bottom = 36.dp),
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Логотип",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                )
            }
        },
    ) { innerPadding ->

        val subscriptions = subscriptionsViewModel.subscriptionsData.collectAsState()
        val subscriptionData = subscriptions.value
        var selectedCardIndex by remember { mutableIntStateOf(value = 0) }

        AnimatedVisibility(visible = subscriptionData.isLoading) {
            CircularProgressIndicator()
        }

        Column(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues = innerPadding),
        ) {
            Text(
                modifier = modifier.padding(top = 16.dp, bottom = 4.dp),
                text = stringResource(id = R.string.label),
                style = GeometriaTextBold28,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Text(
                modifier = modifier.padding(top = 12.dp, bottom = 20.dp),
                text = stringResource(id = R.string.label_description),
                style = GeometriaTextRegular16,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp,
            )

            subscriptionData.subscriptionModel.forEachIndexed { index, data ->
                Column(
                    modifier = modifier.padding(bottom = 14.dp),
                ) {
                    RTLSubscriptionCard(
                        subscriptionData = data,
                        isSelected = index == selectedCardIndex,
                        onClick = { selectedCardIndex = index },
                    )
                }
            }

            SubscriptionOffer()

            Spacer(modifier = modifier.weight(1f))

            CommonButton(
                modifier = modifier.padding(vertical = 36.dp),
                buttonText = stringResource(id = R.string.subscribe_button),
            ) {}
        }
    }
}

@Preview(showBackground = true)
@PreviewFontScale
@Composable
private fun ComposeScreenPreview() {
    RTLSupportAppTheme {
        Surface {
            ComposeScreen(
                modifier = Modifier,
                navController = rememberNavController(),
            )
        }
    }
}