package com.corpus.carousal.navgraph

sealed class Screens (val route: String){
    data object LoginScreen : Screens(route = "LoginScreen")
    data object OTPScreen : Screens(route = "OTPScreen")
    data object MainHomeNavigationScreen : Screens(route = "MainHomeNavScreen")
    data object HomeScreen : Screens(route = "HomeScreen")
    data object AboutScreen : Screens(route = "AboutScreen")
}