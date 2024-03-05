package ru.popkov.font_scale_support_app.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.popkov.font_scale_support_app.R
import ru.popkov.font_scale_support_app.common.compose.CommonButton
import ru.popkov.font_scale_support_app.navigation.Screens

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(color = MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CommonButton(buttonText = stringResource(id = R.string.xml)) {
            navController.navigate(Screens.XML.route)
        }
        CommonButton(
            modifier = modifier.padding(top = 40.dp),
            buttonText = stringResource(id = R.string.compose)
        ) {
            navController.navigate(Screens.Compose.route)
        }
    }
}