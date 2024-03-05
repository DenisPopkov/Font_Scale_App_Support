package ru.popkov.font_scale_support_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.popkov.font_scale_support_app.base.MainScreen
import ru.popkov.font_scale_support_app.databinding.ActivityMainBinding
import ru.popkov.font_scale_support_app.screens.compose.ComposeScreen
import ru.popkov.font_scale_support_app.screens.xml.XMLFragment

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Main.route) {
        composable(route = Screens.Main.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screens.XML.route) {
            AndroidViewBinding(ActivityMainBinding::inflate) {
                container.getFragment<XMLFragment>()
            }
        }
        composable(route = Screens.Compose.route) {
            ComposeScreen(navController = navController)
        }
    }
}