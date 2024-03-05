package ru.popkov.font_scale_support_app.navigation

sealed class Screens(val route: String) {
    data object Main : Screens("main_screen")
    data object Compose : Screens("compose_screen")
    data object XML : Screens("xml_screen")
}